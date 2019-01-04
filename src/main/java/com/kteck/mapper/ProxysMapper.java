package com.kteck.mapper;

import com.kteck.model.Proxys;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ProxysMapper {
    @Delete({
        "delete from proxys",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into proxys (name, urls, ",
        "type, pattern, position, ",
        "params, test_url)",
        "values (#{name,jdbcType=VARCHAR}, #{urls,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{pattern,jdbcType=VARCHAR}, #{position,jdbcType=VARCHAR}, ",
        "#{params,jdbcType=VARCHAR}, #{testUrl,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Proxys record);

    @Select({
        "select",
        "id, name, urls, type, pattern, position, params, test_url",
        "from proxys",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="urls", property="urls", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern", property="pattern", jdbcType=JdbcType.VARCHAR),
        @Result(column="position", property="position", jdbcType=JdbcType.VARCHAR),
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR),
        @Result(column="test_url", property="testUrl", jdbcType=JdbcType.VARCHAR)
    })
    Proxys selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, name, urls, type, pattern, position, params, test_url",
        "from proxys"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="urls", property="urls", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern", property="pattern", jdbcType=JdbcType.VARCHAR),
        @Result(column="position", property="position", jdbcType=JdbcType.VARCHAR),
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR),
        @Result(column="test_url", property="testUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<Proxys> selectAll();

    @Update({
        "update proxys",
        "set name = #{name,jdbcType=VARCHAR},",
          "urls = #{urls,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "pattern = #{pattern,jdbcType=VARCHAR},",
          "position = #{position,jdbcType=VARCHAR},",
          "params = #{params,jdbcType=VARCHAR},",
          "test_url = #{testUrl,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Proxys record);
}