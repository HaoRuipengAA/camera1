package com.example.camera.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Properties;

public class PropertiesUtil {

    static Properties config = null;

    private static Properties properties = new Properties();

    public PropertiesUtil() {
        super();
        config = getPropUtil();
    }

    public static Properties getPropUtil() {
        config = new Properties();
        InputStream is = null;
        try {
            //ln("初始化config对象!");
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
                    "plcConfig.properties");
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭资源
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return config;
    }

    /**
     * 根据key获取value
     *
     * @param key 配置文件的key
     * @return 返回的值
     */
    public static String get(String key) {
        if (config == null) {
            config = getPropUtil();
        }
        String value = config.getProperty(key);
        // 编码转换，从ISO8859-1转向指定编码

        try {
            if (value != null) {
                value = new String(value.getBytes("ISO8859-1"), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return value;
    }


    public static void init() {
        if (properties.size() == 0) {
            InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("plcConfig.properties");
            try {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过配置文件的key，读取value在plc中的偏移量
     *
     * @param key key
     * @return 偏移量数组
     */
    public static int[] getOffset(String key) {
        init();
        int[] offset = new int[2];
        BigDecimal bigDecimal = new BigDecimal(get(key).split(",")[1]);
        //byte偏移量
        offset[0] = bigDecimal.intValue();
        //bit偏移量
        offset[1] = bigDecimal.multiply(new BigDecimal(10)).intValue() % 10;
        return offset;
    }
}
