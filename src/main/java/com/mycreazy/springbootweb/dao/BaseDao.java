package com.mycreazy.springbootweb.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/8/14
 * Time: 上午10:22
 **/
@Mapper
@Repository
public interface BaseDao {
    @Select("select * from ${tb} where ${whereName} = #{whereValue}  and isdelete=0")
    List<Map<String, Object>> query(@Param("tb") String table, @Param("whereName") String whereName, @Param("whereValue") Object whereValue);

    /**
     * 根据条件查询
     * @param table
     * @return
     */
    @Select("select * from ${tb} where  ${whereCondition}")
    List<Map<String, Object>> queryByConditon(@Param("tb") String table, @Param("whereCondition") String whereCondition);

    /**
     * 查询所有
     * @param table
     * @return
     */
    @Select("select * from ${tb}")
    List<Map<String, Object>> queryAll(@Param("tb") String table);

    @Lang(InsertLanguageDriver.class)
    @Insert("insert into ${tb} (#{columns}) values(#{values})")
    void insert(@Param("tb") String table, @Param("params") Map<String, Object> params);

    /**
     * 更新操作
     * @param table
     * @param updateField
     * @param whereCondition
     */
    @Update("update ${tb} set ${updateField} where ${whereCondition}")
    void updateByWhere(@Param("tb") String table, @Param("updateField") String updateField, @Param("whereCondition") String whereCondition);


    @Lang(UpdateLanguageDriver.class)
    @Update("update ${tb} set (#{params}) where ${whereName} = #{whereValue}")
    void update(@Param("tb") String table, @Param("params") Map<String, Object> params, @Param("whereName") String whereName, @Param("whereValue") Object whereValue);

    /**
     * 删除
     * @param table
     * @param where
     */
    @Update("delete from ${tb} where  ${where}")
    void deleteByWhere(@Param("tb") String table, @Param("where") String where);

    @Delete("delete from ${tb} where ${whereName} = #{whereValue}")
    void delete(@Param("tb") String table, @Param("whereName") String whereName, @Param("whereValue") Object whereValue);
}
