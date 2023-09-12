package com.lql.www.service.impl;

import com.lql.www.service.DuplicateCheckService;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
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
        // 调用duplicateCheckService接口
        for (String target:targetFiles) {
            double result = duplicateCheckService.duplicateCheck(origin,target,output);
            System.out.printf("[%tc] %s 该文件查重率为%.2f \n",new Date(),target,result);
        }
    }
}
