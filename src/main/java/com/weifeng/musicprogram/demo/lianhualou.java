package com.weifeng.musicprogram.demo;

import java.util.Scanner;

public class lianhualou {
    public static void main(String[] args) {
        int money = 20;
        int bookPrice = 12;
        int otheroney=0;//剩余的钱
        int pencilPrice = 1;//铅笔
        int eraserPrice = 2;//橡皮
        int colaPrice = 3;//可乐
        int foodPrice = 5;//零食
        Scanner scanner=new Scanner(System.in);
        System.out.println("请务必购买书本，输入0：");
        int start = scanner.nextInt();
        if(start==0){
            // 购买一本书后剩余的金额
            otheroney = money - bookPrice;
            System.out.println("购买一本书后剩余的金额：" + otheroney + "元");
        }else{
            System.out.println("不输入0？ 拜拜");
            System.exit(0);
        }
        while (true){
            System.out.println("请输入序号：");
            int input = scanner.nextInt();
            if(input==1){
                // 可以购买的铅笔数量和剩余的金额
                int max = otheroney / pencilPrice;
                otheroney -= max * pencilPrice;
                System.out.println("可以购买的铅笔数量：" + max + "件");
                System.out.println("购买铅笔后剩余的金额：" + otheroney + "元");
            }
            if(input==2){
                // 可以购买的橡皮数量和剩余的金额
                int max = otheroney / eraserPrice;
                otheroney -= max * eraserPrice;
                System.out.println("可以购买的橡皮数量：" + max + "件");
                System.out.println("购买橡皮后剩余的金额：" + otheroney + "元");
            }
            if(input==3){
                // 可以购买的可乐数量和剩余的金额
                int max = otheroney / colaPrice;
                otheroney -= max * colaPrice;
                System.out.println("可以购买的可乐数量：" + max + "件");
                System.out.println("购买可乐后剩余的金额：" + otheroney + "元");
            }
            if(input==4){
                // 可以购买的零食数量和剩余的金额
                int max = otheroney / foodPrice;
                otheroney -= max * foodPrice;
                System.out.println("可以购买的零食数量：" + max + "件");
                System.out.println("购买零食后剩余的金额：" + otheroney + "元");
            }
            if(otheroney==0){
                System.out.println("没钱了，拜拜");
                break;
            }
        }
    }
}
