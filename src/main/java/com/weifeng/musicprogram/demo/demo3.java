package com.weifeng.musicprogram.demo;

import java.util.Scanner;

public class demo3 {
    public static void main(String[] args) {
        int[] line=new int[10];
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入九组数据：");
        for (int i = 0; i < 10; i++) {
            System.out.println("请输入第 "+ i +" 个成绩：");
            line[i]=scanner.nextInt();
        }
        System.out.println("平均分为："+getMean(line));
    }
    public static double getMean(int[] a){
        double sum=0;
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
            sum=sum+a[i];
        }
        return sum/a.length;
    }
}
