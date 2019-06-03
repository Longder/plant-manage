package com.longder.plant.util;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BaiduApiUtil {
    @Value("${system.baidu.api_id}")
    private String apiId;
    @Value("${system.baidu.api_key}")
    private String apiKey;
    @Value("${system.baidu.secret_key}")
    private String secretKey;

    /**
     * 检查图片是否是植物
     *
     * @param imageBytes
     * @return true: 是植物 false: 不是植物
     */
    public boolean checkImage(byte[] imageBytes) {
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(apiId, apiKey, secretKey);
        HashMap<String, String> options = new HashMap<String, String>();
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        JSONObject res = client.plantDetect(imageBytes, options);
        JSONArray array = res.getJSONArray("result");
        if(Double.parseDouble(array.getJSONObject(0).get("score").toString())==0){
            return false;
        }else{
            return true;
        }
    }
}
