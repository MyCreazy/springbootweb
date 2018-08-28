package com.mycreazy.springbootweb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式辅助类
 * Time: 下午2:52
 **/
public class RegexUtil {
    /**
     * 去掉空格等
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll(" ");
        }
        return dest;
    }

    /**
     * 判断是否试跑是否成功
     * @param excuteStr
     * @param regexStr
     * @return
     */
    public static boolean judgeSuccess(String excuteStr,String regexStr)
    {
        boolean result=false;
        excuteStr=replaceBlank(excuteStr);
        Pattern p = Pattern.compile(regexStr,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(excuteStr);
        result=m.matches();
        return  result;
    }

    /**
     * 获取包含指定字符的字符串
     * @param str
     * @param symbolStr
     * @return
     */
    public static String getSpecialSymbolChar(String str,String symbolStr) {
        String result = "";
        String[] tempArray=str.split("\\s+");
        if(tempArray!=null)
        {
            for(int i=0;i<tempArray.length;i++)
            {
                String tempstr=tempArray[i];
                if(tempstr.contains(symbolStr))
                {
                     String[] tableArray= tempstr.split("\\.");
                     if(tableArray!=null&&tableArray.length==2) {
                         result = tableArray[1];
                     }
                    break;
                }
            }
        }

        return result;
    }
}
