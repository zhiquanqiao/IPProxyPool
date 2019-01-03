package com.kteck.mapper;

import com.kteck.model.Proxys;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ProxysMapper {
    @Delete({
        "delete from proxys",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into proxys (name, urls, ",
        "type, pattern, position, ",
        "params)",
        "values (#{name,jdbcType=VARCHAR}, #{urls,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{pattern,jdbcType=VARCHAR}, #{position,jdbcType=VARCHAR}, ",
        "#{params,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Proxys record);

    @Select({
        "select",
        "id, name, urls, type, pattern, position, params",
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
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR)
    })
    Proxys selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, name, urls, type, pattern, position, params",
        "from proxys"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="urls", property="urls", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="pattern", property="pattern", jdbcType=JdbcType.VARCHAR),
        @Result(column="position", property="position", jdbcType=JdbcType.VARCHAR),
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR)
    })
    List<Proxys> selectAll();

    @Update({
        "update proxys",
        "set name = #{name,jdbcType=VARCHAR},",
          "urls = #{urls,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "pattern = #{pattern,jdbcType=VARCHAR},",
          "position = #{position,jdbcType=VARCHAR},",
          "params = #{params,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Proxys record);
}