package com.lql.www.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * TF工具类对应的测试类
 * @author lql 2023/09/13
 */
public class TfUtilsTest {
    private final TfUtils tfUtils = new TfUtils();

    private final SeparateWordUtils separateWordUtils = new SeparateWordUtils();

    @Test
    public void testCalculateCosineSimilarity() throws IOException {
        // 读取文件
        String content1 = FileUtils.getFileText("D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig.txt");
        // 读取文件
        String content2 = FileUtils.getFileText("D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_add.txt");

        Map<String, Integer> map1 = separateWordUtils.separateWord(content1);
        Map<String, Integer> map2 = separateWordUtils.separateWord(content2);
        // 调用
        double v = tfUtils.calculateCosineSimilarity(map1, map2);
        System.out.printf("结果为%.2f\n",v);
    }
}
