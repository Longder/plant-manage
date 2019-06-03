package com.longder.plant;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

public class BaiduApiTest extends BaseTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "16337570";
    public static final String API_KEY = "4TzDzz7v5SXWNH4tGE7lzmpO";
    public static final String SECRET_KEY = "AUelnrENCC0rVRWfnllOqIgSTMBziN89";


    @Test
    public void apiTest(){
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        HashMap<String, String> options = new HashMap<String, String>();
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        String path = "/Users/longder/zebra.jpeg";
        JSONObject res = client.plantDetect(path, options);
        System.out.println(res.toString(2));
    }
}
