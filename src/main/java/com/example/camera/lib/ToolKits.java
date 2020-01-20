package com.example.camera.lib;

import com.example.camera.common.ErrorCode;
import com.example.camera.demo.module.LoginModule;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.io.UnsupportedEncodingException;

public class ToolKits {

    /**
     * 获取接口错误码和错误信息，用于打印
     *
     * @return
     */
    public static String getErrorCodePrint() {
        return "\n{error code: (0x80000000|" + (LoginModule.netsdk.CLIENT_GetLastError() & 0x7fffffff) + ").参考  NetSDKLib.java }"
                + " - {error info:" + ErrorCode.getErrorCode(LoginModule.netsdk.CLIENT_GetLastError()) + "}\n";
    }

    /***************************************************************************************************
     *                          				工具方法       	 										   *
     ***************************************************************************************************/
    public static void GetPointerData(Pointer pNativeData, Structure pJavaStu) {
        GetPointerDataToStruct(pNativeData, 0, pJavaStu);
    }

    public static void GetPointerDataToStruct(Pointer pNativeData, long OffsetOfpNativeData, Structure pJavaStu) {
        pJavaStu.write();
        Pointer pJavaMem = pJavaStu.getPointer();
        pJavaMem.write(0, pNativeData.getByteArray(OffsetOfpNativeData, pJavaStu.size()), 0,
                pJavaStu.size());
        pJavaStu.read();
    }

    // 将Pointer值转为byte[]
    public static String GetPointerDataToByteArr(Pointer pointer) {
        String str = "";
        if (pointer == null) {
            return str;
        }

        int length = 0;
        byte[] bufferPlace = new byte[1];

        for (int i = 0; i < 2048; i++) {
            pointer.read(i, bufferPlace, 0, 1);
            if (bufferPlace[0] == '\0') {
                length = i;
                break;
            }
        }

        if (length > 0) {
            byte[] buffer = new byte[length];
            pointer.read(0, buffer, 0, length);
            try {
                str = new String(buffer, "GBK").trim();
            } catch (UnsupportedEncodingException e) {
                return str;
            }
        }

        return str;
    }

    /**
     * 登录设备设备错误状态, 用于界面显示
     */
    public static String getErrorCodeShow() {
        return ErrorCode.getErrorCode(LoginModule.netsdk.CLIENT_GetLastError());
    }
}
