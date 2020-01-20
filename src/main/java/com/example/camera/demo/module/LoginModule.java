package com.example.camera.demo.module;

import com.example.camera.lib.NetSDKLib;
import com.example.camera.lib.NetSDKLib.*;
import com.example.camera.lib.ToolKits;
import com.sun.jna.ptr.IntByReference;
import org.springframework.stereotype.Component;

@Component
public class LoginModule {

    public static NetSDKLib netsdk = NetSDKLib.NETSDK_INSTANCE;

    // 设备信息
    public static NetSDKLib.NET_DEVICEINFO_Ex m_stDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();

    // 登陆句柄
    public static LLong m_hLoginHandle = new LLong(0);


    /**
     * 登陆
     * @param m_strIp
     * @param m_nPort
     * @param m_strUser
     * @param m_strPassword
     * @return
     */
    public static boolean login(String m_strIp, int m_nPort, String m_strUser, String m_strPassword) {
        IntByReference nError = new IntByReference(0);
        m_hLoginHandle = netsdk.CLIENT_LoginEx2(m_strIp, m_nPort, m_strUser, m_strPassword, 0, null, m_stDeviceInfo, nError);
        if (m_hLoginHandle.longValue() == 0) {
            System.err.printf("Login Device[%s] Port[%d]Failed. %s\n", m_strIp, m_nPort, ToolKits.getErrorCodePrint());
        } else {
            System.out.println("Login Success [ " + m_strIp + " ]");
        }

        return m_hLoginHandle.longValue() == 0 ? false : true;
    }

    /**
     * 登出
     * @return
     */
    public static boolean logout() {
        if (m_hLoginHandle.longValue() == 0) {
            return false;
        }

        boolean bRet = netsdk.CLIENT_Logout(m_hLoginHandle);
        if (bRet) {
            m_hLoginHandle.setValue(0);
        }
        if (m_hLoginHandle.longValue() == 0) {
            System.out.println("logout success!");
        } else {
            System.out.println("logout failed!");
        }

        return bRet;
    }

    /**
     *
     * @param disConnect
     * @return
     */
    public static boolean init(NetSDKLib.fDisConnect disConnect) {
        boolean bInit = netsdk.CLIENT_Init(disConnect, null);
        if (!bInit) {
            System.out.println("Initialize SDK failed");
            return false;
        }
        return true;
    }
}
