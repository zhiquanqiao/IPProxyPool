package com.kteck.mapper;

import com.kteck.model.ProxyIp;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ProxyIpMapper {
    @Delete({
            "delete from proxy_ip",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into proxy_ip (ip, port, ",
            "types, protocol, ",
            "country, area, updatetime, ",
            "speed, score)",
            "values (#{ip,jdbcType=VARCHAR}, #{port,jdbcType=VARCHAR}, ",
            "#{types,jdbcType=VARCHAR}, #{protocol,jdbcType=VARCHAR}, ",
            "#{country,jdbcType=VARCHAR}, #{area,jdbcType=VARCHAR}, #{updatetime,jdbcType=TIMESTAMP}, ",
            "#{speed,jdbcType=DECIMAL}, #{score,jdbcType=TINYINT})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(ProxyIp record);

    @Select({
            "select",
            "id, ip, port, types, protocol, country, area, updatetime, speed, score",
            "from proxy_ip",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "ip", property = "ip", jdbcType = JdbcType.VARCHAR),
            @Result(column = "port", property = "port", jdbcType = JdbcType.VARCHAR),
            @Result(column = "types", property = "types", jdbcType = JdbcType.VARCHAR),
            @Result(column = "protocol", property = "protocol", jdbcType = JdbcType.VARCHAR),
            @Result(column = "country", property = "country", jdbcType = JdbcType.VARCHAR),
            @Result(column = "area", property = "area", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updatetime", property = "updatetime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "speed", property = "speed", jdbcType = JdbcType.DECIMAL),
            @Result(column = "score", property = "score", jdbcType = JdbcType.TINYINT)
    })
    ProxyIp selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, ip, port, types, protocol, country, area, updatetime, speed, score",
            "from proxy_ip"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "ip", property = "ip", jdbcType = JdbcType.VARCHAR),
            @Result(column = "port", property = "port", jdbcType = JdbcType.VARCHAR),
            @Result(column = "types", property = "types", jdbcType = JdbcType.VARCHAR),
            @Result(column = "protocol", property = "protocol", jdbcType = JdbcType.VARCHAR),
            @Result(column = "country", property = "country", jdbcType = JdbcType.VARCHAR),
            @Result(column = "area", property = "area", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updatetime", property = "updatetime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "speed", property = "speed", jdbcType = JdbcType.DECIMAL),
            @Result(column = "score", property = "score", jdbcType = JdbcType.TINYINT)
    })
    List<ProxyIp> selectAll();

    @Update({
            "update proxy_ip",
            "set ip = #{ip,jdbcType=VARCHAR},",
            "port = #{port,jdbcType=VARCHAR},",
            "types = #{types,jdbcType=VARCHAR},",
            "protocol = #{protocol,jdbcType=VARCHAR},",
            "country = #{country,jdbcType=VARCHAR},",
            "area = #{area,jdbcType=VARCHAR},",
            "updatetime = #{updatetime,jdbcType=TIMESTAMP},",
            "speed = #{speed,jdbcType=DECIMAL},",
            "score = #{score,jdbcType=TINYINT}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProxyIp record);


    @Select({"SELECT count(*) total FROM proxy_ip where ip=#{ip} and port = #{port}"})
    @Results(@Result(column = "total", property = "total", jdbcType = JdbcType.INTEGER))
    int ifExistByIPAndPort(@Param("ip") String ip, @Param("port") String port);

    @Delete({"delete from proxy_ip where score <= 0"})
    void deleteNotAvailAbleIp();
}