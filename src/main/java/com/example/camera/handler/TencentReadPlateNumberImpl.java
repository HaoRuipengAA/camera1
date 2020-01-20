package com.example.camera.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.camera.common.ReturnBean;
import com.example.camera.util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

public class TencentReadPlateNumberImpl implements ReadPlateNumberInterface {

    private static String url = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_plateocr";
    private static String filePath = "C:\\Users\\l\\Desktop\\微信图片_20200103171831.jpg";
    private static int appId = 2127344393;
    private static String appKey = "DOAoWQwfYByeb7df";


    @Override
    public ReturnBean getPlateNumber(String filePath) {
        ReturnBean rb = new ReturnBean();
        String nonceStr = "fa577ce340859f9fe";
        String image64 = HttpUtil.fileToBase64(filePath);
        long timeStamp = System.currentTimeMillis() / 1000;
        Map<String, Object> map = new HashMap<>();
        map.put("time_stamp", timeStamp);
        map.put("nonce_str", nonceStr);
        map.put("image", image64);
        map.put("app_id", appId);

        StringBuffer s = new StringBuffer();
        Set<String> set1 = sort(map);
        for (String str : set1) {
            try {
                s.append(str + "=" + URLEncoder.encode(map.get(str).toString(), "UTF-8") + "&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        String sign = null;
        String s1 = s.append("app_key=" + appKey).toString();
        sign = md5(s1).toUpperCase();
        map.put("sign", sign);
        String ss = HttpUtil.doPostJson(url, map);
        if (ss.contains("<html>")) {
            rb.failure("0001","获取失败");
        } else {
            JSONObject result = JSONObject.parseObject(ss);
            if (result.get("ret").toString().equals("0")) {
                //成功
                String plateNumber = result.getJSONObject("data").getJSONArray("item_list").getJSONObject(0).get("itemstring").toString();
                rb.setData(plateNumber);
            } else {
                rb.failure(result.getString("ret"),result.getString("msg"));
            }
        }
        return rb;
    }



    //map按照key升序排序
    public static Set<String> sort(Map<String, Object> map) {
        Map<String, Object> map1 = new HashMap<>();
        List<String> list = new ArrayList(map.keySet());

        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object a, Object b) {
                return a.toString().toLowerCase().compareTo(b.toString().toLowerCase());
            }
        });
        return new TreeSet<>(list);
    }

    //md5运算
    public static String md5(String beforestr) {
        byte[] unencodedPassword = beforestr.getBytes();
        MessageDigest md = null;
        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            return beforestr;
        }
        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        TencentReadPlateNumberImpl tc = new TencentReadPlateNumberImpl();
        System.out.println(tc.getPlateNumber(filePath).toString());
    }
}
