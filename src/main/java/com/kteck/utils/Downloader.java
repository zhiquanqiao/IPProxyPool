package com.kteck.utils;

import java.util.Map;

public interface Downloader {
    String download(HttpMethod method, String url, Map<String,String> data) throws Exception;
}
