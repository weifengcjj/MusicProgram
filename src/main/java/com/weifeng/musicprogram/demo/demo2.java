package com.weifeng.musicprogram.demo;

public class demo2 {
    public static void main(String[] args) {
        String name = "你好，friends";     //创建变量
        //             0 12 3 4 5
        // 字符串 截取
        name = name.substring(1,4); // 从 0 开始 截取三位
        //todo 下标 是从 0 开始
        System.out.println(name);

        String name1 = "你好，朋友们";
        System.out.println(name1.endsWith("朋友们"));
        System.out.println(name1.startsWith("你"));

        //todo ---------------------------------------------------------
        String a="abcd";
        String b="abcd";

        System.out.println( a==b);       //比较的是变量的值

        String str = new String("abcd");  //创建 字符串对象
        String str1 = new String("abcd");
        System.out.println(str==str1);  //比较的是变量的地址 地址是内存中的
        System.out.println("equals----"+str.equals(str1));

        // todo -------------------------------------------------
        String find= "hello";
        System.out.println(find.charAt(1));     //charAt 字符串查找
        System.out.println(find.length()); // length 长度
        for (int i = 0; i < find.length(); i++) {
            System.out.println(find.charAt(i));
        }

        //todo ---------------------------------------------------
        String  fin1 = "hellolll";
        System.out.println(fin1.indexOf('l'));      // indexOf 找到第一次出现字母的 下标

        System.out.println(fin1.indexOf('l',4));    //从(起始位置)指定位置开始找 找到第一次出现字母的 下标

        //todo ---------------------------------------------------
        int num = 88988;
        //todo 类型转换
        String num1 = String.valueOf(num);      //String.valueOf 转换成字符串类型
        System.out.println(num);
        System.out.println(num1.substring(2,3));

        int num2 = Integer.valueOf(num1);       //Integer.valueOf 转换成int类型
        System.out.println(num2-1);

        //todo ------------------------一维数组-------------------------------
        // 1
        int[] list = new int[4];      //todo 数组  数据类型[] 变量名 = new 数据类型[长度];
        list[0]=1;
        list[1]=19;
        list[2]=20;
        list[3]=2;
        System.out.println(list.length);
//        list[3]=18; //溢出

        // 0 1 2 3 4 5 6 7 8 9
        // 0 0 0 0 0 0 0 1 2 12
//        System.out.println(list[0]+"-"+list[1]+"-"+list[2]);
        for (int i = 0; i < list.length; i++) {        //遍历数组
            System.out.println(list[i]);
        }
        /*
        * 多行注释
        * */

        //2
        String[] list2 = {"张三","李四","王二麻子","赵五"}; //todo 数组  数据类型[] 变量名 = {"数据"};
        System.out.println(list2[3]);

        //3
        int max=0;
        for (int i = 0; i < list.length; i++) {        //遍历数组
            if(list[i]>max){
                max=list[i];
            }
        }
        System.out.println("最大值："+max);

        //todo ------------------------二维数组-------------------------------
        int[][] list3 = new int[4][3];      //行  ----   列
        list3[0][0]=1;
        list3[0][1]=2;
        System.out.println(list3.length);
        for (int i = 0; i < list3.length; i++) {
            for (int j = 0; j < list3.length-1; j++) {
                System.out.print(list3[i][j]);
            }
            System.out.println();
        }
    }
}
