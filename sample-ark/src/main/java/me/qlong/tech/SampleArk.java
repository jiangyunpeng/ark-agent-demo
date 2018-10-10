/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package me.qlong.tech;

import com.alipay.sofa.ark.support.startup.SofaArkBootstrap;


/**
 * @author qilong.zql 18/10/10-上午10:33
 */
public class SampleArk {

    public static void main(String[] args) {
        SofaArkBootstrap.launch(args);
        System.out.println("Hi, I'm a simple ark project.");
        try {
            System.out.println("\"me.qlong.tech.SampleArk is loaded by \"" + SampleArk.class.getClassLoader());
            ClassLoader classLoader = SampleArk.class.getClassLoader().loadClass("me.qlong.tech.SampleAgent").getClassLoader();
            System.out.println("\"me.qlong.tech.SampleAgent is loaded by \"" + classLoader);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class me.qlong.tech.SampleAgent not found!!!");
        }
    }

}