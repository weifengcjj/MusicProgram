package com.weifeng.musicprogram.demo;

import java.util.Scanner;

public class demo1 {
    public static void main(String[] args) throws InterruptedException {
        // int short long
        // double float
        // String char
        // boolean true false

        int a = 1;
        int b = 2;

        if(a>b){        //条件判断
            boolean c=true;
            System.out.println(c);
        }
        else if(b>a){
            boolean c=true;
            System.out.println(c);
        }
        else{
            boolean c=false;
            System.out.println(c);
        }

//        todo ---------------------------------------------------------------------------------
        // 三元运算符
        //todo 条件判断 ？ 表达式a(成立) : 表达式b(不成立)
        String name = 2>1 ? "成立" : "不成立";
        System.out.println(name);

        boolean c = a > b ? true : false;
        System.out.println(c);


//        todo ---------------------------------------------------------------------------------
        //for  --     while    --    do while
        for(int i=1;i<=5;i++){
//            int i=1 初始语句
//            i<5   判断条件
//            i++   条件控制
//            Thread.sleep(1000);     //系统睡眠1秒
            System.out.println("你好---"+i);  //执行语句
        }
        for(int i=0;i<100;i++){
            //局部变量
            if(i % 2 == 0){
                // !=  ==
                System.out.println("这是偶数："+i);
            }else {
                System.out.println("这是奇数："+i);
            }
        }

        for(int i=20;i>=1;i-=2){
            System.out.println(i);
        }

        for (int i=1;i<=5;i++){          //输出行 i=2 i=3 i=4 i=5
            for (int j=1;j<i;j++){       //输出列 j=1 j=2 j=3 j=4 j=5
                System.out.print("*");   //print    ln是回车
            }
            System.out.println("*");     //行 可以换行
        }


//        todo ---------------------------------------------------------------------------------

//        while  while(条件判断){  执行语句     条件控制  }
        boolean flag=false;
        // 全局变量
        while(flag){        //布尔 默认对比true   先判断，然后再执行语句
            System.out.println("while");
        }

        // do while  必定先执行语句 然后再判断
        do{
            System.out.println("do while");
        }while (flag);



//        todo ---------------------------------------------------------------------------------

//        switch
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入周几：");
        int week=scanner.nextInt(); //输入 星期几
//        if(week==1){
//            System.out.println("星期一");
//        }else if(week==2){
//            System.out.println("星期二");
//        }
        switch (week){
            case 1:
                System.out.println("星期一");
                break;  //跳出循环
            case 2:
                System.out.println("星期二");
                break;  //跳出循环
            case 3:
                System.out.println("星期三");
                break;  //跳出循环
            case 4:
                System.out.println("星期四");
                break;  //跳出循环
            default:
                System.out.println("输入的不是星期几");
                break;
        }
    }
}
