/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package me.qlong.tech;


import java.lang.instrument.Instrumentation;

/**
 * @author qilong.zql 18/10/10-上午10:10
 */
public class SampleAgent {
   public static void premain(String args, Instrumentation inst) {
      System.out.println("Hi, I'm a simple agent");
   }
}