package com.lql.www.service.impl;

import com.lql.www.service.DuplicateCheckService;
import com.lql.www.utils.FileUtils;
import com.lql.www.utils.SeparateWordUtils;
import com.lql.www.utils.TfUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 查重服务的实现类
 * @author lql 2023/09/11
 */
public class DuplicateCheckServiceImpl implements DuplicateCheckService {
    /**
     * 余弦相似度的权重
     */
    private final static double COS_WEIGHT = 0.6;

    /**
     * jaccard相似度的权重
     */
    private final static double JACCARD_WEIGHT = 0.4;


    @Override
    public double duplicateCheck(String originFilePath, String targetFilePath, String outputFilePath) throws IOException {
        // 初始化分词工具类
        SeparateWordUtils separateWordUtils = new SeparateWordUtils();
        // 初始化TF工具类
        TfUtils tfUtils = new TfUtils();

        // 1.读取源文件内容
        String originText = FileUtils.getFileText(originFilePath);
        // 2.读取目标文件内容
        String targetText = FileUtils.getFileText(targetFilePath);

        // 使用分词工具分为两个map,数值为单词在文本中出现的频率
        Map<String,Integer> originWords = separateWordUtils.separateWord(originText);
        Map<String,Integer> targetWords = separateWordUtils.separateWord(targetText);

        // 计算余弦相似度
        double cos = tfUtils.calculateCosineSimilarity(originWords, targetWords);

        // 计算交集数量
        int intersectionSize = 0;
        Set<String> originKeySet = originWords.keySet();
        Set<String> targetKeySet = targetWords.keySet();
        // 分别获取map中的keySet
        for (String word : originKeySet) {
            if (targetKeySet.contains(word)) {
                intersectionSize++;
            }
        }

        // 计算并集数量
        int unionSize = originKeySet.size() + targetKeySet.size() - intersectionSize;
        // 结果为并集数和交集数之间的比率，越接近1越相似
        double jaccard = (double) intersectionSize / unionSize;

        // 根据权重计算最终结果
        double finalResult = cos * COS_WEIGHT + jaccard * JACCARD_WEIGHT;

        // 3.将结果输出到文件中
        String resultStr = String.format("[%tc] %s 该文件查重率为%.2f \n",new Date(),targetFilePath,finalResult);
        FileUtils.writeToFile(outputFilePath,resultStr);

        return finalResult;
    }
}
