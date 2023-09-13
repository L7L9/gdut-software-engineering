package com.lql.www.service.impl;

import com.lql.www.service.DuplicateCheckService;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * DuplicationCheckService对应的测试类
 *
 * @author lql 2023/09/11
 */
public class DuplicationCheckServiceTest {
    private DuplicateCheckService duplicateCheckService = new DuplicateCheckServiceImpl();

    @Test
    public void duplicationCheckTest() throws IOException {
        String origin = "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig.txt";
        String output = "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\result.txt";

        String[] targetFiles = {
                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_add.txt",
                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_del.txt",
                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_dis_1.txt",
                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_dis_10.txt",
                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_dis_15.txt"
        };
//        String origin = "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin.txt";
//        String output = "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\result.txt";
//
//        String[] targetFiles = {
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_1.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_2.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_3.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_4.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_5.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_6.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_7.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_8.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_9.txt",
//                "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\another_test\\origin_10.txt"
//        };

        // 调用duplicateCheckService接口
        for (String target:targetFiles) {
            double result = duplicateCheckService.duplicateCheck(origin,target,output);
            System.out.printf("[%tc] %s 该文件查重率为%.2f \n",new Date(),target,result);
        }
    }
}
