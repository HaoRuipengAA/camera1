package com.example.camera.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.camera.common.ReturnBean;
import com.example.camera.util.HttpUtil;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaiDuReadPlateNumberImpl implements ReadPlateNumberInterface {

    @Override
    public ReturnBean getPlateNumber(String filePath) {
        ReturnBean rb = new ReturnBean();
//        "C:\\Users\\l\\Desktop\\微信图片_20200103171831.jpg"
        String base64 = HttpUtil.fileToBase64(filePath);

        String token = getToken();//有效期一个月
        String getCarNumberUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate?access_token="+token;
        Map<String,Object> map1 = new HashMap<>();
        map1.put("image",base64);
        map1.put("multi_detect",false);
        String result = HttpUtil.doPostJson(getCarNumberUrl,map1);
        JSONObject returnMsg = JSONObject.parseObject(result);
        String errorCode = returnMsg.getString("error_code");
        if(StringUtils.isEmpty(errorCode)){
            JSONObject carNumberMsg = returnMsg.getJSONObject("words_result");
            rb.setData(carNumberMsg.get("number"));
        }else{
            rb.failure(errorCode,returnMsg.getString("error_msg"));
        }

        return rb;
    }


    public String getToken() {
        String clientId = "akAtsOCiVEIQVfDVPtF16DKB";
        String clientSecret = "rw9P4FrnogKnmKuhNrEfGYspagbUlfUW";
        String string = HttpUtil.doPostJson("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id="+clientId+"&client_secret="+clientSecret,new HashMap<>());
        JSONObject returnMsg = JSONObject.parseObject(string);
        System.out.println(returnMsg);
        String token = returnMsg.getString("access_token");
        return token;
    }

    public static void main(String[] args) {
        BaiDuReadPlateNumberImpl a = new BaiDuReadPlateNumberImpl();
        System.out.println(a.getPlateNumber("C:\\Users\\l\\Desktop\\微信图片_20200103171831.jpg").toString());
    }
}
