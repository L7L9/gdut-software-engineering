package com.lql.www.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * SeparateWorldUtils分词工具类的测试类
 *
 * @author lql 2023/09/12
 */
public class SeparateWordUtilsTest {
    private final SeparateWordUtils separateWordUtils = new SeparateWordUtils();

    @Test
    public void testSeparateWord() throws IOException {
        // 读取文件
        String content = FileUtils.getFileText("D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig.txt");
        // 调用分词工具类
        Map<String, Integer> map = separateWordUtils.separateWord(content);
        // 打印
        map.forEach((k,v)-> System.out.printf("[%s]: %d次\n",k,v));
    }

}
