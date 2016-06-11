package com.jiangjun.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 111 on 16/5/26.
 */
public class Test {
    public static void main(String[] args){
        List<String> a = new ArrayList<String>();
        a.add("1");
        List<String> b = new ArrayList<String>();
        b = a;
        b.add("2");
        for(String s:a)
        {
            System.out.println(s);
        }
//        User a = new User();
//        a.setName("aa");
//        User b = new User();
//        b = a;
//        b.setName("bb");
//        System.out.println(b.getName() + ";" + a.getName());
    }
}
