package com.kteck;

import com.kteck.mapper.ProxyIpNetRefMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootConfiguration
//@ContextConfiguration
public class ProxyIpNetRefMapperTest {

    @Autowired
    private ProxyIpNetRefMapper proxyIpNetRefMapper;

    @Test
    public void testIfExistByIPAndPort() {
        String port = "9999";
        String ip = "119.101.115.140";
        int n = proxyIpNetRefMapper.validateNeedCrawl();
        Assert.assertEquals(n == 0, true);

    }

    @Test
    public void testValidateIfExist() {
        String port = "9999";
        String ip = "119.101.115.140";
        int n = proxyIpNetRefMapper.validateIfExist(1, 2);
        Assert.assertEquals(n == 0, true);

    }


    @Test
    public void testDeleteByNetId() {
        int n = proxyIpNetRefMapper.deleteByNetId(9);
        Assert.assertEquals( n > 0, true);
    }

    @Test
    public void voidGetIpsByNetId(){
        List<Map<String,Object>> list = proxyIpNetRefMapper.getIpsByNetId(7,10);
        Assert.assertEquals(list.size(),10);
     }
}
