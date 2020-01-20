package com.example.camera.demo.module;

import com.example.camera.lib.NetSDKLib;
import com.example.camera.lib.ToolKits;

public class TrafficEventModule {

    // 智能订阅句柄
    public static NetSDKLib.LLong m_hAttachHandle = new NetSDKLib.LLong(0);

    /**
     * 订阅实时上传智能分析数据
     *
     * @return
     */
    public static boolean attachIVSEvent(int ChannelId, NetSDKLib.fAnalyzerDataCallBack m_AnalyzerDataCB) {
        /**
         * 说明：
         * 	通道数可以在有登录是返回的信息 m_stDeviceInfo.byChanNum 获取
         *  下列仅订阅了0通道的智能事件.
         */
        int bNeedPicture = 1; // 是否需要图片

        m_hAttachHandle = LoginModule.netsdk.CLIENT_RealLoadPictureEx(LoginModule.m_hLoginHandle, ChannelId, NetSDKLib.EVENT_IVS_ALL,
                bNeedPicture, m_AnalyzerDataCB, null, null);
        if (m_hAttachHandle.longValue() != 0) {
            System.out.println("CLIENT_RealLoadPictureEx Success  ChannelId :    " + ChannelId);
        } else {
            System.err.println("CLIENT_RealLoadPictureEx Failed!" + ToolKits.getErrorCodePrint());
            return false;
        }

        return true;
    }
}
