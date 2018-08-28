package com.mycreazy.springbootweb.dao;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/8/14
 * Time: 上午10:24
 **/
public class InsertLanguageDriver extends XMLLanguageDriver implements LanguageDriver {
    private final Pattern pattern = Pattern.compile("\\(#\\{(columns)\\}\\)");
    private final Pattern pattern2 = Pattern.compile("\\(#\\{(values)\\}\\)");

    @Override
    public SqlSource createSqlSource(Configuration configuration,
                                     String script, Class<?> parameterType) {

        Matcher matcher = pattern.matcher(script);
        if (matcher.find()) {
            script = matcher.replaceAll("(<foreach collection=\"params\" item=\"__value\" index=\"__key\" separator=\",\" >\\${__key}</foreach>)");
        }

        matcher = pattern2.matcher(script);
        if (matcher.find()) {
            script = matcher.replaceAll("(<foreach collection=\"params\" item=\"__value\" index=\"__key\" separator=\",\" >#{__value}</foreach>)");
        }

        script = "<script>" + script + "</script>";
        return super.createSqlSource(configuration, script, parameterType);
    }
}
