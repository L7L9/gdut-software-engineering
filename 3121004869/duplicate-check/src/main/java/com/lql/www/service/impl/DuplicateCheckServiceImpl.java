package com.lql.www.service.impl;

import com.lql.www.service.DuplicateCheckService;
import com.lql.www.utils.FileUtils;
import com.lql.www.utils.SeparateWordUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * 查重服务的实现类
 * @author lql 2023/09/11
 */
public class DuplicateCheckServiceImpl implements DuplicateCheckService {
    @Override
    public double duplicateCheck(String originFilePath, String targetFilePath, String outputFilePath) throws IOException {
        // 初始化分词工具类
        SeparateWordUtils separateWordUtils = new SeparateWordUtils();

        // 1.读取源文件内容
        String originText = FileUtils.getFileText(originFilePath);
        // 2.读取目标文件内容
        String targetText = FileUtils.getFileText(targetFilePath);

        // 使用分词工具分为两个集合
        Set<String> originWords = separateWordUtils.separateWord(originText);
        Set<String> targetWords = separateWordUtils.separateWord(targetText);

        // 计算交集数量
        int intersectionSize = 0;
        for (String word : originWords) {
            if (targetWords.contains(word)) {
                intersectionSize++;
            }
        }

        // 计算并集数量
        int unionSize = originWords.size() + targetWords.size() - intersectionSize;
        // 结果为并集数和交集数之间的比率，越接近1越相似
        double result = (double) intersectionSize / unionSize;

        // 3.将结果输出到文件中
        String resultStr = String.format("[%tc] %s 该文件查重率为%.2f \n",new Date(),targetFilePath,result);
        FileUtils.writeToFile(outputFilePath,resultStr);

        return result;
    }
}
