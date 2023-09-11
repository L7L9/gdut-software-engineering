package com.lql.www.utils;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.util.HashSet;
import java.util.Set;

/**
 * 分词工具类
 *
 * @author lql 2023/09/11
 */
public class SeparateWordUtils {
    private final JiebaSegmenter segmenter = new JiebaSegmenter();

    /**
     * @param text 输入文本
     * @return {@link Set}<{@link String}> 返回单词集合
     */
    public Set<String> separateWord(String text){
        Set<String> set = new HashSet<>();
        if(!set.addAll(segmenter.sentenceProcess(text))){
            System.out.println("分词失败");
        }
        return set;
    }
}
