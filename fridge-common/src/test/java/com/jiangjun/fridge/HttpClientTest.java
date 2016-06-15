package com.jiangjun.fridge;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiangjun on 16/6/15.
 */
public class HttpClientTest {

    @Test
    public void httpClientTest() throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeStart=sdf.parse("2016-09-20 12:30:45").getTime();
        System.out.println(timeStart);
        Date date = new Date(timeStart);
        System.out.println(sdf.format(date));
    }
}
