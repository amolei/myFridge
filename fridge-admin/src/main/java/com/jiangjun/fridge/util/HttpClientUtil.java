package com.jiangjun.fridge.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/14.
 */
public class HttpClientUtil {

    public static String get(String url) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
//        HttpHost httpHost = new HttpHost("10.19.110.31", 8080);

        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = closeableHttpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    String result = EntityUtils.toString(entity);
                    return result;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String post(String url) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.DEFAULT);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(entity);

            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if(httpEntity != null){
//                System.out.println("resource:" + EntityUtils.toString(httpEntity, "UTF-8"));
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
