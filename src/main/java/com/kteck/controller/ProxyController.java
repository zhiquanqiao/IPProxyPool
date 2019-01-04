package com.kteck.controller;


import com.kteck.model.ProxyNet;
import com.kteck.model.Proxys;
import com.kteck.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ProxyController {

    @Autowired
    private ProxyService proxyService;

    @RequestMapping("/")
    public ModelAndView getNet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("net");
        List<ProxyNet> nets = proxyService.listNets();
        modelAndView.addObject("nets", nets);
        return modelAndView;
    }

    @RequestMapping(value = "/nets", method = RequestMethod.POST)
    public String addNet(ProxyNet proxyNet) {
        if (proxyNet.getId() != null && proxyNet.getId() > 0) {
            proxyService.updateNet(proxyNet);
        } else {
            proxyService.insertNet(proxyNet);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/nets/delete/{id}", method = RequestMethod.GET)
    public String deleteNet(@PathVariable int id) {
        proxyService.deleteNet(id);
        return "redirect:/";
    }


    @RequestMapping(value = "/ips/{netId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Map<String, Object>>
    getIpsByNetId(@PathVariable int netId,
                  @RequestParam(value = "limits", required = false, defaultValue = "10") int limits) {
        return proxyService.getIpsByNetId(netId,limits);
    }


    @RequestMapping(value = "/proxys", method = RequestMethod.GET)
    public ModelAndView proxys() {
        ModelAndView modelAndView = new ModelAndView();
        List<Proxys> proxys = proxyService.listProxys();
        modelAndView.addObject("proxys", proxys);
        modelAndView.setViewName("proxys");
        return modelAndView;
    }

    @RequestMapping(value = "/proxys/add", method = RequestMethod.POST)
    public String addProxy(Proxys proxy) {
        proxyService.insertProxy(proxy);
        return "redirect:/proxys";
    }

    @RequestMapping(value = "/proxys/delete", method = RequestMethod.POST)
    public String deleteProxy(Integer id) {
        proxyService.deleteProxy(id);
        return "redirect:/proxys";
    }
}
