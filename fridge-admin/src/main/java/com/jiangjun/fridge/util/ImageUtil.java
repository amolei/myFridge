package com.jiangjun.fridge.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
 * Created by jiangjun on 2016/7/14.
 */
public class ImageUtil {

    public static String saveImg(String url,String rootPath, String imgPath){
        String path = "";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
//        HttpHost httpHost = new HttpHost("10.19.110.31", 8080);

        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = closeableHttpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200) {
                HttpEntity entity = response.getEntity();
                Header contentType = entity.getContentType();
                // 从content-type判断图片的格式
                String type = getImageTypeByContentType(contentType.getValue());
                byte[] result = EntityUtils.toByteArray(entity);
                if ("".equals(type)) {
                    // 从byte[]中获取图片格式
                    type = getImageTypeByByteArray(result);
                    if ("".equals(type)) {
                        type = getImageType(url);
                        if ("".equals(type)) {
                            type = "jpg";
                        }
                    }
                }
                String fileName = url.hashCode() + "." + type;
                String foler = rootPath + imgPath;
                String savePath = rootPath + imgPath + fileName;
                File folderFile = new File(foler);
                if(!folderFile.exists()){
                    folderFile.mkdirs();
                }
                path = imgPath +fileName;
                saveFile(result, savePath);
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
        return path;
    }

    public static String getImageTypeByContentType(String contentType) {
        // TODO Auto-generated method stub
        if (contentType.indexOf(";") > -1) {
            contentType = contentType.split(";")[0];
        }
        String type = "";
		/* gif格式图片 */
        /**
         * gif图片压缩的时候会生成多个文件
         */
        if ("gif".equalsIgnoreCase(contentType)
                || "image/gif".equalsIgnoreCase(contentType)) {
            type = "gif";
        }
		/* jpeg格式图片 */
        else if ("jpeg".equalsIgnoreCase(contentType)
                || "image/jpeg".equalsIgnoreCase(contentType)) {
            type = "jpeg";
        }
		/* jpg格式图片 */
        else if ("jpg".equalsIgnoreCase(contentType)
                || "image/jpg".equalsIgnoreCase(contentType)) {
            type = "jpg";
        }
		/* bmp格式图片 */
        else if ("bmp".equalsIgnoreCase(contentType)
                || "image/bmp".equalsIgnoreCase(contentType)) {
            type = "bmp";
        } else if ("wbmp".equalsIgnoreCase(contentType)
                || "image/wbmp".equalsIgnoreCase(contentType)) {
            type = "wbmp";
        }
		/* png格式图片 */
        else if ("png".equalsIgnoreCase(contentType)
                || "image/png".equalsIgnoreCase(contentType)) {
            type = "png";
        }
		/* ico格式图片 */
        else if ("ico".equalsIgnoreCase(contentType)
                || "image/ico".equalsIgnoreCase(contentType)) {
            type = "ico";
        }
        return type;
    }

    public static String getImageTypeByByteArray(byte[] array) {
        String type = "";
        byte b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
        /**
         * 判断是否为gif
         */
        if (array.length > 2) {
            b0 = array[0];
            b1 = array[1];
            b2 = array[2];
            if (b0 == 'G' && b1 == 'I' && b2 == 'F') {
                type = "gif";
            }
        }
        /**
         * 判断是否为png
         */
        if (array.length > 3) {
            b1 = array[1];
            b2 = array[2];
            b3 = array[3];
            if (b1 == 'P' && b2 == 'N' && b3 == 'G') {
                type = "png";
            }
        }

        /**
         * 判断是否为jpg
         */
        if (array.length > 9) {
            b6 = array[6];
            b7 = array[7];
            b8 = array[8];
            b9 = array[9];
            if (b6 == 'J' && b7 == 'F' && b8 == 'I' && b9 == 'F') {
                type = "jpeg";
            }
        }
        return type;
    }

    public static String getImageType(String url) {
        // TODO Auto-generated method stub
        String type = "";
        url = url.toLowerCase();
        if (url.endsWith("png")) {
            type = "png";
        } else if (url.endsWith("jpeg")) {
            type = "jpeg";
        } else if (url.endsWith("jpg")) {
            type = "jpg";
        } else if (url.endsWith("gif")) {
            type = "gif";
        } else if (url.endsWith("ico")) {
            type = "ico";
        } else if (url.endsWith("bmp")) {
            type = "bmp";
        } else if (url.endsWith("wbmp")) {
            type = "wbmp";
        }
        return type;
    }

    public static void saveFile(byte[] bytes, String path) {
        // TODO Auto-generated method stub
        InputStream in = null;
        FileOutputStream fs = null;
        try {
            in = new ByteArrayInputStream(bytes);
            fs = new FileOutputStream(path);
            byte[] buffer = new byte[1204];
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            fs.flush();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (null != fs)
                    fs.close();
                if (null != in)
                    in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
