package com.example.camera.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.camera.common.ReturnBean;
import com.example.camera.util.HWOcrClientToken;
import org.apache.http.HttpResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class HuaWeiReadPlateNumberImpl implements ReadPlateNumberInterface {
    private static String username="hw73626864";
    private static String password="Aa123456";
    private static String domainName="hw73626864"; //If the current user is not an IAM user, the domainName is the same as the userName.
    private static String regionName="cn-north-4";
    @Override
    public ReturnBean getPlateNumber(String filePath) {
//        String filePath = "C:\\Users\\l\\Desktop\\微信图片_20200103171831.jpg";
//        晋A01563
        ReturnBean rb = new ReturnBean();
        try {
            rb = TokenDemo(filePath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rb;
    }
    public static ReturnBean TokenDemo(String imgPath) throws URISyntaxException, UnsupportedOperationException, IOException {

        ReturnBean rb = new ReturnBean();
        /*
         * Token demo code
         * */
        String httpUri = "/v1.0/ocr/license-plate";

        // Set params except image
        String sideKey = "side";
        String sideValue = "";
        JSONObject params = new JSONObject();
        //params.put(sideKey, sideValue);
        try {
            HWOcrClientToken ocrClient= new HWOcrClientToken(domainName, username, password, regionName);
            HttpResponse response=ocrClient.RequestOcrServiceBase64(httpUri, imgPath, params);
//            System.out.println(response);
            String content = IOUtils.toString(response.getEntity().getContent(), "utf-8");
//            System.out.println(content);
            JSONObject jsonObject = JSONObject.parseObject(content);
            String errorCode = jsonObject.getString("error_code");
            if(StringUtils.isEmpty(errorCode)){
                JSONObject jsonObject1 = jsonObject.getJSONArray("result").getJSONObject(0);
                rb.setData(jsonObject1.get("plate_number").toString());
            }else{
                rb.failure(errorCode,jsonObject.getString("error_msg"));
            }
            return rb;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rb;
    }


    public static void main(String[] args) {
        HuaWeiReadPlateNumberImpl hw = new HuaWeiReadPlateNumberImpl();
        System.out.println(hw.getPlateNumber("C:\\Users\\l\\Desktop\\微信图片_20200103171831.jpg").toString());
    }
}
