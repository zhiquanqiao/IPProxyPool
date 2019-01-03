package com.kteck;
import com.kteck.mapper.ProxyIpMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
//@SpringBootConfiguration
//@ContextConfiguration
public class ProxyIpMapperTest {

    @Autowired
    private ProxyIpMapper proxyIpMapper;

    @Test
    public void testIfExistByIPAndPort() {
        String port = "9999";
        String ip = "119.101.115.140";
        Assert.assertEquals(proxyIpMapper.ifExistByIPAndPort(ip, port), 1);

    }
}
