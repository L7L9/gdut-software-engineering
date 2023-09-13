# 软工作业2：个人项目-论文查重

> **`github链接`：[github](https://github.com/L7L9/gdut-software-engineering)**

| **这个作业属于哪个课程** | [计科21级12班 - 广东工业大学 ](https://edu.cnblogs.com/campus/gdgy/CSGrade21-12) |
| :----------------------: | :----------------------------------------------------------: |
|    这个作业要求在哪里    | [作业要求](https://edu.cnblogs.com/campus/gdgy/CSGrade21-12/homework/13014) |
|      这个作业的目标      |            完成个人项目-论文查重；学会写单元测试             |

# 一、PSP表格

|                PSP2.1                 |   Personal Software Process Stages    | 预估耗时（分钟） | 实际耗时（分钟） |
| :-----------------------------------: | :-----------------------------------: | :--------------: | :--------------: |
|               Planning                |                 计划                  |        60        |        30        |
|               Estimate                |       估计这个任务需要多少时间        |        30        |        60        |
|              Development              |                 开发                  |       300        |       420        |
|               Analysis                |       需求分析 (包括学习新技术)       |        60        |        30        |
|              Design Spec              |             生成设计文档              |        60        |        60        |
|             Design Review             |               设计复审                |        15        |        10        |
|            Coding Standard            | 代码规范 (为目前的开发制定合适的规范) |        15        |        10        |
|                Design                 |               具体设计                |        30        |        60        |
|                Coding                 |               具体编码                |        30        |        60        |
|              Code Review              |               代码复审                |        15        |        10        |
|                 Test                  | 测试（自我测试，修改代码，提交修改）  |        15        |        60        |
|               Reporting               |                 报告                  |        60        |       120        |
|              Test Report              |               测试报告                |        30        |        60        |
|           Size Measurement            |              计算工作量               |        10        |        10        |
| Postmortem & Process Improvement Plan |     事后总结, 并提出过程改进计划      |        10        |        20        |
|                                       |                 合计                  |       740        |       1020       |

# 二、设计与开发

> 编码规范：阿里巴巴开发规范插件

## 2.1 开发环境

* 操作系统：`windows`
* 编程语言：`jdk1.8`

## 2.2 开发工具

* maven包管理工具
* ide：`IDEA2022.3.1`
* 性能分析工具：Jprofiler11.1.4

## 2.3 项目依赖

* junit测试框架
* jieba-java中文分词库

## 2.4 算法设计说明

1. 采用**余弦相似度**与**jaccard相似度**判断文章相似度
2. 基于tf算法的余弦相似度：
   1. 分词后计算每个词的词频（将其视为一个向量）
   2. 计算向量内积
   3. 计算对应文本分词词频数组的范数
   4. 最后用向量内积除以范数的乘积得到结果
3. jaccard相似度：
   1. 分词后使用set集合接收结果
   2. 计算两个文本的交集和并集
   3. 最后用交集数量除以并集数量得到结果
4. 根据权重计算最终结果：
   1. 项目中，我设置余弦相似度的权重为0.6；jaccard相似度的权重为0.4
   2. `最终结果 = 余弦相似度 * 0.6 + jaccard相似度 * 0.4`

## 2.5 接口设计与实现过程

![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913111236438-893646048.png)

1. Main类作为程序接口，有以下功能：

   * 接收命令行参数并检查合法性
   * 调用`DuplicateCheckService`接口

2. service包有以下内容：

   * 定义`DuplicationChcekService`接口

   * 定义`DuplicationCheckServiceImpl`类实现`DuplicationChcekService`接口

     > 实现内容包括：
     >
     > * 调用FileUtils**读取文本内容**和**输出结果到本文中**
     > * 调用SeparateWordUtils对内容**进行分词**以及计算词频
     > * 计算Jaccard相似度
     > * **调用TfUtils计算余弦相似度**
     > * 最后**根据两个相似度的权重计算最终结果**

3. utils包有以下内容：

   * 定义`FileUtils`类，用于读取文件文本和输出内容到文本
   * 定义`SeparateWordUtils`类，读取`stop_words.txt`用于过滤特殊符号和标点符号，并且用于分词
   * 定义`TfUtils`类，用于计算余弦相似度

4. rescources的内容：设置`stop_words.txt`管理要分词时要过滤的内容

# 三、测试与性能分析

## 3.1 测试

1. `DuplicationCheckService`测试

   * 代码如下：

     ```java
         @Test
         public void duplicationCheckTest() throws IOException {
             String origin = "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig.txt";
             String output = "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\result.txt";
     
             String[] targetFiles = {
                     "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_add.txt",
                     "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_del.txt",
                     "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_dis_1.txt",
                     "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_dis_10.txt",
                     "D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig_0.8_dis_15.txt"
             };
     
             // 调用duplicateCheckService接口
             for (String target:targetFiles) {
                 double result = duplicateCheckService.duplicateCheck(origin,target,output);
                 System.out.printf("[%tc] %s 该文件查重率为%.2f \n",new Date(),target,result);
             }
         }
     ```

   * 覆盖率如下：

       ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913131901231-1426458808.png)

   * 测试结果如下：

     ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913131808741-1678022415.png)

2. `SeparateWordUtils`测试

   * 测试代码如下：

     ```java
     @Test
         public void testSeparateWord() throws IOException {
             // 读取文件
             String content = FileUtils.getFileText("D:\\school-3\\软件工程\\gdut-software-engineering\\3121004869\\duplicate-check\\src\\test\\resources\\orig.txt");
             // 调用分词工具类
             Map<String, Integer> map = separateWordUtils.separateWord(content);
             // 打印
             map.forEach((k,v)-> System.out.printf("[%s]: %d次\n",k,v));
         }
     ```

   * 覆盖率如下：

     ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913132143893-1957272502.png)

   * 测试结果如下：

     ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913132207879-2065618087.png)

3. `TfUtils`测试

   * 测试代码如下：

     ```java
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
     ```

   * 覆盖率如下：

     ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913132258046-373078484.png)

   * 测试结果如下：

     ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913132314398-44635456.png)

## 3.2 性能分析

1. Override

   ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913130318246-2077298165.png)

2. Memory

   ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913130346669-447978295.png)

   > 可以看出，Double类以及HashMap类占用的比较多，这是由于分词时加载过滤词集合以及进行余弦相似度计算时产生的

   改进思路：加载过滤词集合时，只加载一次到内存中，而不是每次要分词的时候都加载一次

# 四、异常说明

1. 命令行参数个数错误

   ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913132933091-859359127.png)

2. 命令行参数合法性错误

   ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913132946848-540916981.png)

3. 文件读取输出错误

   ![](https://img2023.cnblogs.com/blog/3274666/202309/3274666-20230913133012766-1626410684.png)

