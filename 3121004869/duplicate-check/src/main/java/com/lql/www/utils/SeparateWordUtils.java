package com.lql.www.utils;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分词工具类
 *
 * @author lql 2023/09/11
 */
public class SeparateWordUtils {
    /**
     * 分词库
     */
    private final JiebaSegmenter segmenter = new JiebaSegmenter();

    /**
     * 常用词以及标点符号的数组
     */
    private List<String> normalWordList;

    {
        // 初始化常用词数组
        normalWordList = new LinkedList<>();
        // 读取常用词及标点符号的文件
        InputStream is = this.getClass().getResourceAsStream("/stop_words.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            // 将读取的内容添加到集合中
            while ((line = reader.readLine()) != null) {
                normalWordList.add(line);
            }
        } catch (IOException e) {
            System.out.println("error: 读取文件出错");
        }
    }


    /**
     * @param text 输入文本
     * @return {@link Set}<{@link String}> 返回单词集合
     */
    public Set<String> separateWord(String text){
        Set<String> set = new HashSet<>();
        // 分词
        List<String> result = segmenter.sentenceProcess(text);
        // 过滤掉标点符号
        result = result.stream().map(String::trim).filter(o -> !normalWordList.contains(o)).collect(Collectors.toList());
        if(!set.addAll(result)){
            System.out.println("分词失败");
        }
        return set;
    }
}
