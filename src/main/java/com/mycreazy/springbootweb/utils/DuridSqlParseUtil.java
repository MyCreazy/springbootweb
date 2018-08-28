package com.mycreazy.springbootweb.utils;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Time: 下午7:32
 * sql解析
 **/
public class DuridSqlParseUtil {
    /**
     * 获取sql内容
     *
     * @param sql
     * @param dbType
     * @return
     */
    public static List<String> getSqlContent(String sql, String dbType) {
        List<String> tableNameList = new ArrayList<>();
        String result = SQLUtils.format(sql, dbType);
        List<SQLStatement> stmtList = null;
        try {
            stmtList = SQLUtils.parseStatements(sql, dbType);
        } catch (Exception ex) {

        }

        if (stmtList != null && stmtList.size() > 0) {
            for (int i = 0; i < stmtList.size(); i++) {
                SQLStatement stmt = stmtList.get(i);
                MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
                stmt.accept(visitor);
                Map<TableStat.Name, TableStat> tableMap = visitor.getTables();
                Set<TableStat.Name> keySet = tableMap.keySet();
                int count = 0;
                for (Iterator<TableStat.Name> iterator = keySet.iterator(); iterator.hasNext(); ) {
                    TableStat.Name key = iterator.next();
                    String keyname = key.getName();
                    ////解析一下
                    String[] tempSplit = keyname.split("\\.");
                    if (tempSplit != null && tempSplit.length == 2) {
                        tableNameList.add(tempSplit[1]);
                    }
                }
            }

        }

        return tableNameList;
    }
}
