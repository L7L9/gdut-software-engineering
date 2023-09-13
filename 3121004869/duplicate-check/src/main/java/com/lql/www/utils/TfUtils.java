package com.lql.www.utils;

import java.util.Map;

/**
 * TF工具类：实现简易的TF算法，用于计算余弦相似度
 *
 * @author lql 2023/09/12
 */
public class TfUtils {
    /**
     * 计算余弦相似度
     *
     * @param word1 文本1的分词结果
     * @param word2 文本2的分词结果
     * @return double 返回余弦相似度
     */
    public double calculateCosineSimilarity(Map<String,Integer> word1,Map<String,Integer> word2){
        // 计算内积
        double dotProduct = 0.0;
        for (Map.Entry<String, Integer> entry : word1.entrySet()) {
            int freq1 = entry.getValue();
            int freq2 = word2.getOrDefault(entry.getKey(), 0);
            dotProduct += freq1 * freq2;
        }

        // 计算范数
        double norm1 = calculateNorm(word1);
        double norm2 = calculateNorm(word2);

        if(norm1 == 0.0 || norm2 == 0.0){
            return 0.0;
        }
        // 计算余弦相似度
        return dotProduct / (norm1 * norm2);
    }

    /**
     * 计算范数
     *
     * @param wordTf 词频映射
     * @return double 返回范数
     */
    private double calculateNorm(Map<String,Integer> wordTf){
        double norm = 0.0;
        for (double freq : wordTf.values()) {
            norm += Math.pow(freq, 2);
        }
        return Math.sqrt(norm);
    }
}
