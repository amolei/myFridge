package com.jiangjun.fridge.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiangjun.fridge.dao.IFridgeBrandDao;
import com.jiangjun.fridge.dto.FridgeBrandDto;
import com.jiangjun.fridge.service.FoodKindService;
import com.jiangjun.fridge.service.FridgeBrandService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjun on 16/6/25.
 */
public class FridgeBrandsUtil {

    private FridgeBrandService fridgeBrandService;

    static String path = "http://list.jd.com/list.html?cat=737,794,878&md=1&my=list_brand";

    public FridgeBrandsUtil(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        fridgeBrandService = (FridgeBrandService) context.getBean("fridgeBrandService");
    }

    public void parseFridgeBrands(String result){
        JSONObject json = JSONObject.parseObject(result);
        JSONArray array = json.getJSONArray("brands");
        for(int i=0;i<array.size();i++){
            JSONObject object = array.getJSONObject(i);
            String fridge_name = object.getString("name");
            String fridge_pinyin = object.getString("pinyin");
            String fridge_log = object.getString("logo");
            FridgeBrandDto fridgeBrandDto = new FridgeBrandDto();
            fridgeBrandDto.setFridge_name(fridge_name);
            fridgeBrandDto.setFridge_pinyin(fridge_pinyin);
            fridgeBrandDto.setFridge_logo(fridge_log);
            fridgeBrandService.addFridgeBrand(fridgeBrandDto);
        }
    }

    public void get(String url) {
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
                    parseFridgeBrands(result);
//                    System.out.println(EntityUtils.toString(entity));
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
    }

    public void post(String url) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        HttpHost httpHost = new HttpHost("10.19.110.31", 8080);
        httpPost.setConfig(RequestConfig.DEFAULT);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("searchText", "英语"));
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(entity);

            HttpResponse httpResponse = closeableHttpClient.execute(httpHost, httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if(httpEntity != null){
//                System.out.println("resource:" + EntityUtils.toString(httpEntity, "UTF-8"));
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                parseFridgeBrands(result);
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
    }

    public static void main(String[] args){
        FridgeBrandsUtil util = new FridgeBrandsUtil();
        util.get(path);
    }
}
