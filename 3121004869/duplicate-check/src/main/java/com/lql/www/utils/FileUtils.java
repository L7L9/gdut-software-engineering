package com.lql.www.utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 文件操作的工具类
 *
 * @author lql 2023/09/11
 */
public class FileUtils {
    /**
     * 获取文件文本
     *
     * @param path 文件目录
     * @return {@link String} 返回文件内容
     */
    public static String getFileText(String path) throws IOException {
        StringBuilder builder = new StringBuilder();

        // 读取文件
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        // 将读取的内容一行一行的append到builder
        while(line != null){
            builder.append(line);
            line = reader.readLine();
        }

        return builder.toString();
    }

    /**
     * 向文件中写入文本
     *
     * @param path 文件路径
     * @param content 写入的文本内容
     * @throws IOException 抛出打开文件失败的异常
     */
    public static void writeToFile(String path,String content) throws IOException{
        // 创建FileOutputStream对象，如果文件不存在会自动创建
        FileOutputStream fos = new FileOutputStream(path,true);
        // 将内容转换为字节数组
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        // 写入内容到文件
        fos.write(bytes);
        // 关闭文件流
        fos.close();
    }
}
