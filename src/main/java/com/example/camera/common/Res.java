package com.example.camera.common;

import com.example.camera.lib.NetSDKLib;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Res {
    private ResourceBundle bundle;

    public String getCapturePicture() {
        return bundle.getString("CAPTURE_PICTURE");
    }

    public String getDisConnectReconnecting() {
        return bundle.getString("DISCONNECT_RECONNECTING");
    }

    public String getOnline() {
        return bundle.getString("ONLINE");
    }

    private Res() {
        switchLanguage(LanguageType.Chinese);
    }

    public static enum LanguageType {
        English,
        Chinese
    }

    private static class StringBundleHolder {
        private static Res instance = new Res();
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public static Res string() {
        return StringBundleHolder.instance;
    }

    /**
     * \if ENGLISH_LANG
     * Switch between Chinese and English
     * \else
     * 中英文切换
     * \endif
     */
    public void switchLanguage(LanguageType type) {
        switch (type) {
            case Chinese:
                bundle = ResourceBundle.getBundle("res", new Locale("zh", "CN"));
                break;
            case English:
                bundle = ResourceBundle.getBundle("res", new Locale("en", "US"));
                break;
            default:
                break;
        }
    }

    public String getEventName() {
        return bundle.getString("EVENT_NAME");
    }

    /*
     * 车辆大小对照表
     */
    public String getTrafficSize(int nVehicleSize) {
        String vehicleClass = "";
        for (int i = 0; i < 5; i++) {
            if (((byte) nVehicleSize & (1 << i)) > 0) {
                switch (i) {
                    case 0:
                        vehicleClass = bundle.getString("LIGHT_DUTY");
                        break;
                    case 1:
                        vehicleClass = bundle.getString("MEDIUM");
                        break;
                    case 2:
                        vehicleClass = bundle.getString("OVER_SIZE");
                        break;
                    case 3:
                        vehicleClass = bundle.getString("MINI_SIZE");
                        break;
                    case 4:
                        vehicleClass = bundle.getString("LARGE_SIZE");
                        break;
                }
            }
        }

        return vehicleClass;
    }

    public String getErrorMessage() {
        return bundle.getString("ERROR_MESSAGE");
    }

    /*
     * 获取事件名称
     */
    public String getEventName(int type) {
        String name = "";
        switch (type) {
            case NetSDKLib.EVENT_IVS_TRAFFICJUNCTION:  ///< 交通路口事件
                name = bundle.getString("EVENT_IVS_TRAFFICJUNCTION");
                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_RUNREDLIGHT: ///< 闯红灯事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_RUNREDLIGHT");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_OVERLINE: ///< 压车道线事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_OVERLINE");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_RETROGRADE: ///< 逆行事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_RETROGRADE");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_TURNLEFT: ///< 违章左转
//                name = bundle.getString("EVENT_IVS_TRAFFIC_TURNLEFT");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_TURNRIGHT: ///< 违章右转
//                name = bundle.getString("EVENT_IVS_TRAFFIC_TURNRIGHT");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_UTURN: ///< 违章掉头
//                name = bundle.getString("EVENT_IVS_TRAFFIC_UTURN");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_OVERSPEED: ///< 超速
//                name = bundle.getString("EVENT_IVS_TRAFFIC_OVERSPEED");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_UNDERSPEED: ///< 低速
//                name = bundle.getString("EVENT_IVS_TRAFFIC_UNDERSPEED");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_PARKING: ///< 违章停车
//                name = bundle.getString("EVENT_IVS_TRAFFIC_PARKING");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_WRONGROUTE: ///< 不按车道行驶
//                name = bundle.getString("EVENT_IVS_TRAFFIC_WRONGROUTE");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_CROSSLANE: ///< 违章变道
//                name = bundle.getString("EVENT_IVS_TRAFFIC_CROSSLANE");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_OVERYELLOWLINE: ///< 压黄线
//                name = bundle.getString("EVENT_IVS_TRAFFIC_OVERYELLOWLINE");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_YELLOWPLATEINLANE: ///< 黄牌车占道事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_YELLOWPLATEINLANE");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_PEDESTRAINPRIORITY: ///< 斑马线行人优先事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_PEDESTRAINPRIORITY");
//                break;
            case NetSDKLib.EVENT_IVS_TRAFFIC_MANUALSNAP: ///< 交通手动抓拍事件
                name = bundle.getString("EVENT_IVS_TRAFFIC_MANUALSNAP");
                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_VEHICLEINROUTE: ///< 有车占道事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_VEHICLEINROUTE");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_VEHICLEINBUSROUTE: ///< 占用公交车道事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_VEHICLEINBUSROUTE");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_BACKING: ///< 违章倒车事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_BACKING");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_PARKINGSPACEPARKING: ///< 车位有车事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_PARKINGSPACEPARKING");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_PARKINGSPACENOPARKING: ///< 车位无车事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_PARKINGSPACENOPARKING");
//                break;
//            case NetSDKLib.EVENT_IVS_TRAFFIC_WITHOUT_SAFEBELT: ///< 交通未系安全带事件
//                name = bundle.getString("EVENT_IVS_TRAFFIC_WITHOUT_SAFEBELT");
//                break;
            default:
                break;
        }

        return name;
    }

    public String getChannel() {
        return bundle.getString("CHANNEL");
    }

    public String getLoginFailed() {
        return bundle.getString("LOGIN_FAILED");
    }
}
