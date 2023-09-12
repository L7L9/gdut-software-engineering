package com.lql.www.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.Set;

/**
 * SeparateWorldUtils分词工具类的测试类
 *
 * @author lql 2023/09/12
 */
public class SeparateWordUtilsTest {
    private SeparateWordUtils separateWordUtils = new SeparateWordUtils();

    @Test
    public void testSeparateWord() throws IOException {
        // 读取文件
        String content = FileUtils.getFileText("D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig.txt");
        // 调用分词工具类
        Set<String> set = separateWordUtils.separateWord(content);
        // 打印
        set.forEach(System.out::println);
    }

}
