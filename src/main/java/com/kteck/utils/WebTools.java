package com.kteck.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author qiaozhiquan
 */
public class WebTools {

    public static String KEY_STATUS_CODE = "statusCode";
    public static String KEY_CONTENT = "content";


    /**
     * http request get
     *
     * @param url target url
     * @return
     */
    public static Map<String, Object> get(String url) throws Exception {

        Map<String, Object> result = null;
        CloseableHttpClient httpClient = getCloseableHttpClient();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            httpGet.setHeader("User-Agent", Headers.randomHeader());  //set UserAgent
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = buildResultMap(response, entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }

            if (httpClient != null) {
                httpClient.close();
            }
        }
        return result;
    }

    /**
     * set proxy By Client
     *
     * @return
     */
    private static CloseableHttpClient getCloseableHttpClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient;
    }

    /**
     * buildResultMap
     *
     * @param response
     * @param entity
     * @return
     * @throws IOException
     */
    private static Map<String, Object> buildResultMap(CloseableHttpResponse response, HttpEntity entity) throws
            IOException {
        Map<String, Object> result;
        result = new HashMap<>(2);
        result.put(KEY_STATUS_CODE, response.getStatusLine().getStatusCode());  //status code
        if (entity != null) {
            result.put(KEY_CONTENT, EntityUtils.toString(entity, "UTF-8")); //message content
        }
        return result;
    }


    /**
     * http post data
     *
     * @param url
     * @param data
     * @return
     */
    public static Map<String, Object> post(String url, Map<String, String> data) throws IOException {

        Map<String, Object> result = null;
        CloseableHttpClient httpClient = getCloseableHttpClient();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            httpPost.setHeader("User-Agent", Headers.randomHeader());

            List<NameValuePair> nvps = null;
            if ((nvps = buildNameValuePair(data)) != null)
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));  //set data

            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = buildResultMap(response, entity);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return result;
    }

    /**
     * build param data
     */
    private static List<NameValuePair> buildNameValuePair(Map<String, String> data) {
        List<NameValuePair> result = null;

        if (data != null && data.size() > 0) {
            result = new ArrayList<>();
            Set<String> entrySet = data.keySet();
            Iterator<String> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                NameValuePair nameValuePair = new BasicNameValuePair(key, data.get(key));
                result.add(nameValuePair);
            }
        }

        return result;
    }

    public static Map<String, Object> get(String url, Map<String, String> proxyMap) throws IOException {
        HttpHost proxy = new HttpHost(proxyMap.get("ip"),
                Integer.parseInt(proxyMap.get("port")));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", Headers.randomHeader());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        Map result = buildResultMap(response, entity);
        response.close();
        httpClient.close();
        return result;
    }
}
