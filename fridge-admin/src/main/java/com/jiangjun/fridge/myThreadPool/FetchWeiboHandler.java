package com.jiangjun.fridge.myThreadPool;

import EDU.oswego.cs.dl.util.concurrent.Callable;
import com.jiangjun.fridge.dto.FridgeArticle;
import com.jiangjun.fridge.service.FridgeArticleService;
import com.jiangjun.fridge.service.FridgeBrandService;
import com.jiangjun.fridge.util.ImageUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangjun on 2016/7/17.
 */
public class FetchWeiboHandler implements Callable {

    private FridgeArticleService fridgeArticleService;

        static String _ROOT_PATH = "/opt/content";
    static String _IMAGE_PATH = "/weibo/";
//    static String _ROOT_PATH = "C:\\Users\\jiangjun\\Documents";
//    static String _IMAGE_PATH = "\\weibo\\";


    private FridgeArticle fridgeArticle;

    public FetchWeiboHandler(FridgeArticle fridgeArticle) {
        this.fridgeArticle = fridgeArticle;
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring/spring.xml", "classpath:conf/spring/spring-mybatis.xml"});
        fridgeArticleService = (FridgeArticleService) context.getBean("fridgeArticleService");
    }

    public Object call() {
        try {
            //抓取图片，保存对象到数据库
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String today = format.format(new Date());
            System.out.println(today);
            StringBuilder sb = new StringBuilder();
            List<String> picUrlList = fridgeArticle.getPicUrlList();
            for (int i = 0; i < picUrlList.size(); i++) {
                String saveUrl = ImageUtil.saveImg(picUrlList.get(i), _ROOT_PATH, _IMAGE_PATH + today + File.separator);
                if (i == (picUrlList.size() - 1)) {
                    sb.append(saveUrl);
                } else {
                    sb.append(saveUrl + ",");
                }
            }
            fridgeArticle.setImages(sb.toString());
            fridgeArticleService.add(fridgeArticle);
            System.out.println("add:" + fridgeArticle.getArticle_title());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
