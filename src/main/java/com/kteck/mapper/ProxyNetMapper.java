package com.kteck.mapper;

import com.kteck.model.ProxyNet;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ProxyNetMapper {
    @Delete({
        "delete from proxy_net",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into proxy_net (name, url)",
        "values (#{name,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ProxyNet record);

    @Select({
        "select",
        "id, name, url",
        "from proxy_net",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR)
    })
    ProxyNet selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, name, url",
        "from proxy_net"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR)
    })
    List<ProxyNet> selectAll();

    @Update({
        "update proxy_net",
        "set name = #{name,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProxyNet record);
}