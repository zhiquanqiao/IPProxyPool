package com.kteck.crawl;

import com.alibaba.fastjson.JSONObject;
import com.kteck.model.ProxyIp;
import com.kteck.model.Proxys;
import com.kteck.utils.*;
import org.seimicrawler.xpath.JXNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

//import utils.*;

public class Worker implements Callable {
    private static final int DEEP_LEVEL_FIRST = 1;
    private static final int DEEP_LEVEL_SECOND = 2;

    private final Proxys proxys;
    private final List<Map<String, String>> proxyList;

    public Worker(Proxys proxys, List<Map<String, String>> proxyList) {
        this.proxys = proxys;
        this.proxyList = proxyList;
    }

    @Override
    public List<ProxyIp> call() {
        List<String> urls = buildUrls(proxys.getUrls(), proxys.getParams());
        List<ProxyIp> results = new ArrayList<>();
        Downloader downloader = new HtmlDownloader();
        for (String url : urls) {
            if (proxyList == null || proxyList.size() == 0) {
                try {
                    crawWithProxy(results, downloader, url, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            } else {
                for (Map<String, String> ipProxy : proxyList) {
                    boolean crawlFlag = false;
                    try {
                        crawlFlag = crawWithProxy(results, downloader, url, ipProxy);
                    } catch (Exception e) {
                        continue;
                    }
                    if (crawlFlag) break;
                }
            }
        }
        return results;
    }

    private boolean crawWithProxy(List<ProxyIp> results, Downloader downloader, String url, Map<String, String> ipProxy) throws Exception {
        boolean result = false;
        String html = downloader.download(HttpMethod.GET, url, null, ipProxy);
        Parser parser = null;
        if (proxys.getType().equals("xpath")) {
            parser = new XPathParser();
            List<JXNode> jxNodes = (List<JXNode>) parser.parse(proxys.getPattern(), html);
            JSONObject jsonObject = JSONObject.parseObject(proxys.getPosition());
            for (JXNode jxNode : jxNodes) {
                ProxyIp proxyIp = new ProxyIp();
                try {
                    proxyIp.setIp(String.valueOf(parser.parse(jsonObject.getString("ip"), jxNode)));
                    proxyIp.setPort(String.valueOf(parser.parse(jsonObject.getString("port"), jxNode)));
                    proxyIp.setTypes(String.valueOf(parser.parse(jsonObject.getString("type"), jxNode)));
                    proxyIp.setProtocol(String.valueOf(parser.parse(jsonObject.getString("protocol"), jxNode)));
                    proxyIp.setUpdatetime(new Date());
                    results.add(proxyIp);

                } catch (Exception e) {
                    continue;
                }
            }
            result = true;
        }
        return result;
    }

    /**
     * build urls
     *
     * @return
     */
    public static List<String> buildUrls(String url, String param) {
        List<String> result = new ArrayList<>();
        String[] params = param.split("],\\[");
        int count = appearNumber(url, "%s");
        if (count == params.length) {

            if (params.length == DEEP_LEVEL_FIRST) {

                String paramItems[] = params[0].replaceFirst("\\[", "").replaceFirst("\\]", "").split(",");
                if (isNumbericalArray(paramItems)) {
                    int start = Integer.parseInt(paramItems[0]);
                    int end = Integer.parseInt(paramItems[1]);

                    for (; start <= end; start++) {
                        result.add(String.format(url, start + ""));
                    }
                } else {
                    for (String item : paramItems)
                        result.add(String.format(url, item));
                }

            } else if (params.length == DEEP_LEVEL_SECOND) {

                String deepFirstParamItems[] = params[0].replaceFirst("\\[", "").split(",");
                if (isNumbericalArray(deepFirstParamItems)) {
                    int startFirst = Integer.parseInt(deepFirstParamItems[0]);
                    int endFirst = Integer.parseInt(deepFirstParamItems[1]);

                    for (; startFirst <= endFirst; startFirst++) {
                        String levelSecond = url.replaceFirst("%s", startFirst + "");
                        deelDeepSecondUrl(result, params[1], levelSecond);
                    }
                } else {
                    for (String item : deepFirstParamItems) {
                        String levelSecond = url.replaceFirst("%s", item);
                        deelDeepSecondUrl(result, params[1], levelSecond);
                    }
                }
            } else {
                throw new RuntimeException("params level to deep");
            }

        } else {
            throw new RuntimeException("url ,params  not match");
        }
        return result;
    }

    private static void deelDeepSecondUrl(List<String> result, String param, String levelSecond) {
        String deepSecondParamItems[] = param.replaceFirst("\\]", "").split(",");
        if (isNumbericalArray(deepSecondParamItems)) {
            int startSecond = Integer.parseInt(deepSecondParamItems[0]);
            int endSecond = Integer.parseInt(deepSecondParamItems[1]);
            for (; startSecond <= endSecond; startSecond++)
                result.add(levelSecond.replaceFirst("%s", startSecond + ""));
        } else {
            for (String secondLevelParam : deepSecondParamItems)
                result.add(levelSecond.replaceFirst("%s", secondLevelParam));
        }
    }

    private static boolean isNumbericalArray(String[] params) {
        boolean result = true;

        try {
            if (params.length == 2) {
                Integer.parseInt(params[0]);
                Integer.parseInt(params[1]);
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }

        return result;
    }


    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        String url = "http://www.xicidaili.com/%s/%s";
        String param = "[nn,nt,wn,wt],[1,8]";

        List<String> urls = buildUrls(url, param);
        for (String urlsss : urls) {
            System.out.println(urlsss);
        }
    }
}

