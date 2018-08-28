package com.mycreazy.springbootweb.utils;





import com.alibaba.druid.support.json.JSONUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 日志辅助类
 * Time: 上午10:41
 **/
public class LogUtil {
    /**
     * 时间格式
     */
    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 生成唯一码
     *
     * @param uerName
     * @return
     */
    public static String generateUniqueID(String uerName) {
        String result = "";
        String tempTime = df.format(new Date());
        int randmomnum = (int) ((Math.random() * 9 + 1) * 100000);
        result = tempTime + "_" + uerName + "_" + randmomnum;
        return result;
    }

    /**
     * map转换为json字符串
     *
     * @param jsonData
     * @return
     */
    public static String convertJson(Map<String, Object> jsonData) {
        String result = "";
        try {
           // JSONArray json = JSONArray.fromObject(jsonData);
           result= JSONUtils.toJSONString(jsonData);
        }
        catch (Exception ex)
        {
            result="转换json出错:"+ex.getMessage();
        }

        return result;
    }

    public static String getExceptionAllinformation(Exception ex){
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }
}
