package com.kteck.utils;

import java.util.Map;

/**
 * @author qiaozhiquan
 * @see WebTools
 */
public class HtmlDownloader implements Downloader {

    @Override
    public String download(HttpMethod method, String url, Map<String, String> data, Map<String, String> proxy) throws Exception {
        return String.valueOf((method == HttpMethod.GET ? WebTools.get(url, proxy) : WebTools.post(url, data, proxy))
                .getOrDefault(WebTools.KEY_CONTENT, ""));
    }
}
