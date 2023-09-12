package com.lql.www;

import com.lql.www.service.DuplicateCheckService;
import com.lql.www.service.impl.DuplicateCheckServiceImpl;

import java.io.IOException;
import java.util.Date;

/**
 * 程序入口
 *
 * @author lql 2023/09/11
 */

public class Main {
    public static void main(String[] args) {
        // 检查参数个数
        if (args.length != 3){
            System.out.println("命令行参数个数错误");
            return;
        }

        // 判断参数的合法性
        for (String parameter: args) {
            if (!parameter.endsWith(".txt")){
                System.out.println("参数输入有误，请重新输入");
                return;
            }
        }

        // 初始化service
        DuplicateCheckService duplicateCheckService = new DuplicateCheckServiceImpl();
        try {
            // 调用接口
            double result = duplicateCheckService.duplicateCheck(args[0], args[1], args[2]);
            // 显示结果
            System.out.printf("[%tc] %s 该文件查重率为%.2f \n",new Date(),args[1],result);
        } catch (IOException e) {
            System.out.println("文件打开失败,请检查输入路径");
        }
    }
}
