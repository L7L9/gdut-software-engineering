package com.lql.www.service;

import java.io.IOException;

/**
 * 查重服务接口
 * @author lql 2023/09/11
 */
public interface DuplicateCheckService {
    /**
     * 查重接口
     *
     * @param originFilePath 源文件
     * @param targetFilePath 目标文件
     * @param outputFilePath 输出答案的文件
     * @return double 返回精度为两位小数的精度
     * @throws IOException 抛出异常
     */
    double duplicateCheck(String originFilePath,String targetFilePath,String outputFilePath) throws IOException;
}
