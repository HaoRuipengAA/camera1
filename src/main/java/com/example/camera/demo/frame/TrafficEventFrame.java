package com.example.camera.demo.frame;

import com.example.camera.util.PropertiesUtil;
import com.example.camera.common.Res;
import com.example.camera.demo.module.LoginModule;
import com.example.camera.demo.module.TrafficEventModule;
import com.example.camera.handler.DaHuaHandler;
import com.example.camera.handler.ReadPlateNumberInterface;
import com.example.camera.lib.NetSDKLib;
import com.example.camera.lib.ToolKits;
import com.example.camera.util.SpringUtil;
import com.sun.jna.CallbackThreadInitializer;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TrafficEventFrame {

    private static boolean loginstatus = false;//登陆状态
    private static DisConnect disConnect = new DisConnect();
    private AnalyzerDataCB m_AnalyzerDataCB = new AnalyzerDataCB();
    private static boolean bInit = false;//初始化参数

    @Autowired
    private DaHuaHandler daHuaHandler;

    // 登录
    public void login() {
        if (!loginstatus) {
            //防止连续多次登陆
            loginstatus = !loginstatus;
            LoginModule.init(disConnect);
            String ip = PropertiesUtil.get("ip");
            int port = Integer.parseInt(PropertiesUtil.get("port"));
            String username = PropertiesUtil.get("username");
            String password = PropertiesUtil.get("password");
            Native.setCallbackThreadInitializer(m_AnalyzerDataCB,
                    new CallbackThreadInitializer(false, false, "traffic callback thread"));
            loginstatus = LoginModule.login(ip, port, username, password);
            TrafficEventModule.attachIVSEvent(0,
                    m_AnalyzerDataCB);
        }
    }

    //logout
    public void logout() {
        LoginModule.logout();
        loginstatus = false;
    }

    private static class DisConnect implements NetSDKLib.fDisConnect {
        @Override
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
            System.out.printf("Device[%s] Port[%d] DisConnect!\n", pchDVRIP, nDVRPort);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                }
            });
        }
    }

    /**
     * 智能抓图
     */
    public void manualsnap() {
        manualSnapPicture(0);
    }

    public static boolean manualSnapPicture(int chn) {


        NetSDKLib.MANUAL_SNAP_PARAMETER snapParam = new NetSDKLib.MANUAL_SNAP_PARAMETER();
        snapParam.nChannel = chn;
        String sequence = "11111"; // 抓图序列号，必须用数组拷贝
        System.arraycopy(sequence.getBytes(), 0, snapParam.bySequence, 0, sequence.getBytes().length);

        snapParam.write();
        boolean bRet = LoginModule.netsdk.CLIENT_ControlDeviceEx(LoginModule.m_hLoginHandle, NetSDKLib.CtrlType.CTRLTYPE_MANUAL_SNAP, snapParam.getPointer(), null, 5000);
        if (!bRet) {
            System.err.println("Failed to manual snap, last error " + ToolKits.getErrorCodePrint());
            System.err.println("您可能未登陆");
            return false;
        } else {
            System.out.println("Seccessed to manual snap");
        }
        snapParam.read();
        return true;
    }

    /**
     * 智能报警事件回调
     */

    public class AnalyzerDataCB implements NetSDKLib.fAnalyzerDataCallBack {
        String filepath;
        @Override
        public int invoke(NetSDKLib.LLong lAnalyzerHandle, int dwAlarmType,
                          Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize,
                          Pointer dwUser, int nSequence, Pointer reserved) {
            if (lAnalyzerHandle.longValue() == 0) {
                return -1;
            }

            if (dwAlarmType == NetSDKLib.EVENT_IVS_TRAFFICJUNCTION
                    || dwAlarmType == NetSDKLib.EVENT_IVS_TRAFFIC_MANUALSNAP
            ) {
                // 获取识别对象 车身对象 事件发生时间 车道号等信息
                GetStuObject(dwAlarmType, pAlarmInfo);

                // 保存图片，获取图片缓存
                filepath = savePlatePic(pBuffer, dwBufSize, trafficInfo);

                //处理车牌信息
                daHuaHandler.handler(trafficInfo.m_EventName,trafficInfo.m_PlateNumber,filepath);
                System.out.println("file save path:" + filepath);
            }
            return 0;
        }

        private class TRAFFIC_INFO {
            private String m_EventName;              // 事件名称
            private String m_PlateNumber;          // 车牌号
            private String m_PlateType;               // 车牌类型
            private String m_PlateColor;              // 车牌颜色
            private String m_VehicleColor;              // 车身颜色
            private String m_VehicleType;          // 车身类型
            private String m_VehicleSize;              // 车辆大小
            private String m_FileCount;                  // 文件总数
            private String m_FileIndex;                  // 文件编号
            private String m_GroupID;                  // 组ID
            private String m_IllegalPlace;              // 违法地点
            private String m_LaneNumber;              // 通道号
            private NetSDKLib.NET_TIME_EX m_Utc;      // 事件时间
            private int m_bPicEnble;                  // 车牌对应信息，BOOL类型
            private int m_OffSet;                      // 车牌偏移量
            private int m_FileLength;                 // 文件大小
            private NetSDKLib.DH_RECT m_BoundingBox;  // 包围盒
        }

        private final TRAFFIC_INFO trafficInfo = new TRAFFIC_INFO();

        // 获取识别对象 车身对象 事件发生时间 车道号等信息
        private void GetStuObject(int dwAlarmType, Pointer pAlarmInfo) {
            if (pAlarmInfo == null) {
                return;
            }

            switch (dwAlarmType) {
                case NetSDKLib.EVENT_IVS_TRAFFICJUNCTION: ///< 交通卡口事件
                {
                    NetSDKLib.DEV_EVENT_TRAFFICJUNCTION_INFO msg = new NetSDKLib.DEV_EVENT_TRAFFICJUNCTION_INFO();
                    ToolKits.GetPointerData(pAlarmInfo, msg);

                    trafficInfo.m_EventName = Res.string().getEventName(NetSDKLib.EVENT_IVS_TRAFFICJUNCTION);
                    try {
                        trafficInfo.m_PlateNumber = new String(msg.stuObject.szText, "GBK").trim();
                        if(StringUtils.isEmpty(trafficInfo.m_PlateNumber)){
                            ReadPlateNumberInterface util = (ReadPlateNumberInterface) SpringUtil.getBean(PropertiesUtil.get("readPlateNumberBean"));
                            trafficInfo.m_PlateNumber = util.getPlateNumber(filepath).getData().toString();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    trafficInfo.m_PlateType = new String(msg.stTrafficCar.szPlateType).trim();
//                    trafficInfo.m_FileCount = String.valueOf(msg.stuFileInfo.bCount);
//                    trafficInfo.m_FileIndex = String.valueOf(msg.stuFileInfo.bIndex);
//                    trafficInfo.m_GroupID = String.valueOf(msg.stuFileInfo.nGroupId);
//                    trafficInfo.m_IllegalPlace = ToolKits.GetPointerDataToByteArr(msg.stTrafficCar.szDeviceAddress);
//                    trafficInfo.m_LaneNumber = String.valueOf(msg.nLane);
//                    trafficInfo.m_PlateColor = new String(msg.stTrafficCar.szPlateColor).trim();
//                    trafficInfo.m_VehicleColor = new String(msg.stTrafficCar.szVehicleColor).trim();
//                    trafficInfo.m_VehicleType = new String(msg.stuVehicle.szObjectSubType).trim();
//                    trafficInfo.m_VehicleSize = Res.string().getTrafficSize(msg.stTrafficCar.nVehicleSize);
//                    trafficInfo.m_Utc = msg.UTC;
//                    trafficInfo.m_bPicEnble = msg.stuObject.bPicEnble;
//                    trafficInfo.m_OffSet = msg.stuObject.stPicInfo.dwOffSet;
//                    trafficInfo.m_FileLength = msg.stuObject.stPicInfo.dwFileLenth;
//                    trafficInfo.m_BoundingBox = msg.stuObject.BoundingBox;

                    break;
                }
                case NetSDKLib.EVENT_IVS_TRAFFIC_MANUALSNAP: ///< 交通手动抓拍事件
                {
                    NetSDKLib.DEV_EVENT_TRAFFIC_MANUALSNAP_INFO msg = new NetSDKLib.DEV_EVENT_TRAFFIC_MANUALSNAP_INFO();
                    ToolKits.GetPointerData(pAlarmInfo, msg);

                    trafficInfo.m_EventName = Res.string().getEventName(NetSDKLib.EVENT_IVS_TRAFFIC_MANUALSNAP);
                    try {
                        trafficInfo.m_PlateNumber = new String(msg.stuObject.szText, "GBK").trim();
                        if(StringUtils.isEmpty(trafficInfo.m_PlateNumber)){
                            ReadPlateNumberInterface util = (ReadPlateNumberInterface) SpringUtil.getBean(PropertiesUtil.get("readPlateNumberBean"));
                            trafficInfo.m_PlateNumber = util.getPlateNumber(filepath).getData().toString();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    trafficInfo.m_PlateType = new String(msg.stTrafficCar.szPlateType).trim();
//                    trafficInfo.m_FileCount = String.valueOf(msg.stuFileInfo.bCount);
//                    trafficInfo.m_FileIndex = String.valueOf(msg.stuFileInfo.bIndex);
//                    trafficInfo.m_GroupID = String.valueOf(msg.stuFileInfo.nGroupId);
//                    trafficInfo.m_IllegalPlace = ToolKits.GetPointerDataToByteArr(msg.stTrafficCar.szDeviceAddress);
//                    trafficInfo.m_LaneNumber = String.valueOf(msg.nLane);
//                    trafficInfo.m_PlateColor = new String(msg.stTrafficCar.szPlateColor).trim();
//                    trafficInfo.m_VehicleColor = new String(msg.stTrafficCar.szVehicleColor).trim();
//                    trafficInfo.m_VehicleType = new String(msg.stuVehicle.szObjectSubType).trim();
//                    trafficInfo.m_VehicleSize = Res.string().getTrafficSize(msg.stTrafficCar.nVehicleSize);
//                    trafficInfo.m_Utc = msg.UTC;
//                    trafficInfo.m_bPicEnble = msg.stuObject.bPicEnble;
//                    trafficInfo.m_OffSet = msg.stuObject.stPicInfo.dwOffSet;
//                    trafficInfo.m_FileLength = msg.stuObject.stPicInfo.dwFileLenth;
//                    trafficInfo.m_BoundingBox = msg.stuObject.BoundingBox;
                    break;
                }
                default:
                    break;
            }
        }


        private BufferedImage snapImage = null;

        public String savePlatePic(Pointer pBuffer, int dwBufferSize, TRAFFIC_INFO trafficInfo) {
            String bigPicture; // 大图

            byte[] buffer = pBuffer.getByteArray(0, dwBufferSize);
            ByteArrayInputStream byteArrInput = new ByteArrayInputStream(buffer);

            bigPicture = PropertiesUtil.get("savepath")
                    + new SimpleDateFormat("yyyyMMdd").format(new Date())
                    + "" + "/" + "Big_" + trafficInfo.m_Utc.toStringTitle() + "_" +
                    trafficInfo.m_FileCount + "-" + trafficInfo.m_FileIndex + "-" + trafficInfo.m_GroupID + ".jpg";
            File file = new File(bigPicture);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                snapImage = ImageIO.read(byteArrInput);
                if (snapImage == null) {
                    return "";
                }
                ImageIO.write(snapImage, "jpg", new File(bigPicture));
            } catch (IOException e2) {
                e2.printStackTrace();
            }

            if (bigPicture == null || bigPicture.equals("")) {
                return "";
            }
            return bigPicture;
        }
    }


}
