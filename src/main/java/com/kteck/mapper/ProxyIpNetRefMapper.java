package com.kteck.mapper;

import com.kteck.model.ProxyIpNetRef;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

public interface ProxyIpNetRefMapper {
    @Delete({
            "delete from proxy_ip_net_ref",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into proxy_ip_net_ref (ip_id, net_id, ",
            "score, speed)",
            "values (#{ipId,jdbcType=INTEGER}, #{netId,jdbcType=INTEGER}, ",
            "#{score,jdbcType=TINYINT}, #{speed,jdbcType=DECIMAL})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(ProxyIpNetRef record);

    @Select({
            "select",
            "id, ip_id, net_id, score, speed",
            "from proxy_ip_net_ref",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "ip_id", property = "ipId", jdbcType = JdbcType.INTEGER),
            @Result(column = "net_id", property = "netId", jdbcType = JdbcType.INTEGER),
            @Result(column = "score", property = "score", jdbcType = JdbcType.TINYINT),
            @Result(column = "speed", property = "speed", jdbcType = JdbcType.DECIMAL)
    })
    ProxyIpNetRef selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, ip_id, net_id, score, speed",
            "from proxy_ip_net_ref"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "ip_id", property = "ipId", jdbcType = JdbcType.INTEGER),
            @Result(column = "net_id", property = "netId", jdbcType = JdbcType.INTEGER),
            @Result(column = "score", property = "score", jdbcType = JdbcType.TINYINT),
            @Result(column = "speed", property = "speed", jdbcType = JdbcType.DECIMAL)
    })
    List<ProxyIpNetRef> selectAll();

    @Update({
            "update proxy_ip_net_ref",
            "set ip_id = #{ipId,jdbcType=INTEGER},",
            "net_id = #{netId,jdbcType=INTEGER},",
            "score = #{score,jdbcType=TINYINT},",
            "speed = #{speed,jdbcType=DECIMAL}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProxyIpNetRef record);

    @Select({"SELECT \n" +
            "    ip.id,\n" +
            "    ip.ip,\n" +
            "    ip.port,\n" +
            "    ip.types,\n" +
            "    ip.protocol,\n" +
            "    ip.country,\n" +
            "    ip.area,\n" +
            "    ip.updatetime,\n" +
            "    ref.score,\n" +
            "    ref.speed\n" +
            "FROM\n" +
            "    proxy_ip_net_ref ref,\n" +
            "    proxy_ip ip\n" +
            "WHERE\n" +
            "    ref.net_id = #{netId} AND ref.ip_id = ip.id \n" +
            "    order by ref.score desc\n" +
            "LIMIT ${limits}"
    })
    List<Map<String, Object>> getIpsByNetId(@Param("netId") int netId, @Param("limits") int limits);

    @Select({"SELECT \n" +
            "    COUNT(id) total\n" +
            "FROM\n" +
            "    (SELECT \n" +
            "        i.id, COUNT(inf.id) total\n" +
            "    FROM\n" +
            "        proxy_net i\n" +
            "    LEFT JOIN proxy_ip_net_ref inf ON i.id = inf.net_id \n" +
            "    GROUP BY i.id\n" +
            "    HAVING total < 10) a"})
    @Results({@Result(column = "total", property = "total", jdbcType = JdbcType.INTEGER)})
    int validateNeedCrawl();

    @Select({"select count(*) total from proxy_ip_net_ref where ip_id = #{ipId,jdbcType=INTEGER} and net_id = #{netId,jdbcType=INTEGER}"})
    @Results({@Result(column = "total", property = "total", jdbcType = JdbcType.INTEGER)})
    int validateIfExist(@Param("ipId") int ipId, @Param("netId") int netId);

    @Delete({"delete from proxy_ip_net_ref where ip_id = #{ipId,jdbcType=INTEGER}"})
    int deleteByIpId(@Param("ipId") int ipId);

    @Delete({"delete from proxy_ip_net_ref where net_id = #{netId,jdbcType=INTEGER}"})
    int deleteByNetId(@Param("netId") int netId);

    @Delete({"delete from proxy_ip_net_ref where score <=0 "})
    void deleteNotAvailableIpNetRef();
    @Select({"SELECT \n" +
            "    ip.id,\n" +
            "    ip.ip,\n" +
            "    ip.port,\n" +
            "    ip.types,\n" +
            "    ip.protocol,\n" +
            "    ip.country,\n" +
            "    ip.area,\n" +
            "    ip.updatetime,\n" +
            "    ref.score,\n" +
            "    ref.speed\n" +
            "FROM\n" +
            "    proxy_ip_net_ref ref,\n" +
            "    proxy_ip ip,\n" +
            "    proxy_net net\n" +
            "WHERE\n" +
            "    net.url = #{url,jdbcType=VARCHAR}\n" +
            "        AND ref.ip_id = ip.id\n" +
            "        AND ref.net_id = net.id and ip.types like '%高匿%' \n" +
            "ORDER BY ref.score DESC"})
    List<Map<String, String>> getIpsByUrl(@Param("url") String url);
}