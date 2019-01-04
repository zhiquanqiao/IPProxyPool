package com.kteck.service;

import com.kteck.mapper.ProxyIpMapper;
import com.kteck.mapper.ProxyIpNetRefMapper;
import com.kteck.mapper.ProxyNetMapper;
import com.kteck.mapper.ProxysMapper;
import com.kteck.model.ProxyIp;
import com.kteck.model.ProxyIpNetRef;
import com.kteck.model.ProxyNet;
import com.kteck.model.Proxys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ProxyService {

    @Autowired
    private ProxyIpMapper proxyIpMapper;
    @Autowired
    private ProxyIpNetRefMapper proxyIpNetRefMapper;
    @Autowired
    private ProxyNetMapper proxyNetMapper;
    @Autowired
    private ProxysMapper proxysMapper;
    @Value("${score.each}")
    private byte eachScore;
    @Transactional
    public boolean insertNet(ProxyNet proxyNet) {
        proxyNetMapper.insert(proxyNet);
        List<ProxyIp> proxyIpList = proxyIpMapper.selectAll();
        if (proxyIpList != null && proxyIpList.size() > 0) {
            for (Iterator<ProxyIp> iterator = proxyIpList.iterator(); iterator.hasNext(); ) {

                ProxyIp proxyIp = iterator.next();
                if (proxyIpNetRefMapper.validateIfExist(proxyIp.getId(), proxyNet.getId()) == 0) {
                    buildProxyIpNetRef(proxyIp, proxyNet);
                }
            }
        }
        return true;
    }

    public boolean updateNet(ProxyNet proxyNet) {
        return proxyNetMapper.updateByPrimaryKey(proxyNet) == 1;
    }

    @Transactional
    public void deleteNet(int id) {
        proxyIpNetRefMapper.deleteByNetId(id);
        proxyNetMapper.deleteByPrimaryKey(id);
    }

    public List<ProxyNet> listNets() {
        return proxyNetMapper.selectAll();
    }

    public List<Map<String, Object>> getIpsByNetId(int netId, int limits) {
        return proxyIpNetRefMapper.getIpsByNetId(netId, limits);
    }

    public List<Proxys> listProxys() {
        return proxysMapper.selectAll();
    }

    public boolean insertProxy(Proxys proxy) {
        return proxysMapper.insert(proxy) > 0;
    }

    @Transactional
    public void deleteProxy(int id) {
        proxysMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public boolean insertProxyIp(ProxyIp proxyIp) {

        if (proxyIpMapper.ifExistByIPAndPort(proxyIp.getIp(), proxyIp.getPort()) == 0) {
            proxyIpMapper.insert(proxyIp);
            List<ProxyNet> proxyNetList = proxyNetMapper.selectAll();

            if (proxyNetList != null && proxyNetList.size() > 0) {
                for (Iterator<ProxyNet> iterator = proxyNetList.iterator(); iterator.hasNext(); ) {
                    ProxyNet proxyNet = iterator.next();
                    if (proxyIpNetRefMapper.validateIfExist(proxyIp.getId(), proxyNet.getId()) == 0) {
                        buildProxyIpNetRef(proxyIp, proxyNet);
                    }
                }
            }
        }
        return true;
    }

    private void buildProxyIpNetRef(ProxyIp proxyIp, ProxyNet proxyNet) {
        ProxyIpNetRef proxyIpNetRef = new ProxyIpNetRef();
        proxyIpNetRef.setIpId(proxyIp.getId());
        proxyIpNetRef.setNetId(proxyNet.getId());
        proxyIpNetRef.setScore(eachScore);
        proxyIpNetRefMapper.insert(proxyIpNetRef);
    }


    public int validateNeedCrawl() {
        return proxyIpNetRefMapper.validateNeedCrawl();
    }

    public List<Proxys> selectAllProxys() {
        return proxysMapper.selectAll();
    }

    public List<ProxyIpNetRef> selectAllProxyIpNetRef() {
        return proxyIpNetRefMapper.selectAll();
    }

    public ProxyIp getProxyIpById(Integer id) {
        return proxyIpMapper.selectByPrimaryKey(id);
    }

    public ProxyNet getProxyNetById(Integer netId) {
        return proxyNetMapper.selectByPrimaryKey(netId);
    }

    public void updateProxyIp(ProxyIp proxyIp) {
        proxyIpMapper.updateByPrimaryKey(proxyIp);
    }

    public void updateProxyIpNetRef(ProxyIpNetRef proxyIpNetRef) {
        proxyIpNetRefMapper.updateByPrimaryKey(proxyIpNetRef);
    }

    @Transactional
    public void deleteNotAvailableIp() {
        proxyIpNetRefMapper.deleteNotAvailableIpNetRef();
        proxyIpMapper.deleteNotAvailAbleIp();
    }


    public List<Map<String,String>> getIpsByUrl(String url){
        return proxyIpNetRefMapper.getIpsByUrl(url);
    }
}
