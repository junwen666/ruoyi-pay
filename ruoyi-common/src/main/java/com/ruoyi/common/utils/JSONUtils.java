package com.ruoyi.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class JSONUtils {

    public static String toJSON(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static void parse() throws Exception {
        JSONReader reader = new JSONReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\test.json")));
        reader.startObject();
        while (reader.hasNext()) {
            String key = reader.readString();
            if (key.equals("data")) {
                JSONArray array = reader.readObject(JSONArray.class);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String part = obj.getString("part");
                    System.out.println(part);
                }
            } else {
                reader.readObject();
            }
        }
        reader.endObject();
        reader.close();
        reader = null;
    }

    public static void main(String[] args) throws Exception {
        parse();
    }
}
