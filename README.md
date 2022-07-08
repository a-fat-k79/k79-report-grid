# k79-report-grid
锐浪报表服务器，spring boot实现。

#### 锐浪报表是干什么用的？
Grid++Report是一款报表开发工具，是给程序员用来开发软件中的报表、打印、图表与数据导出的插件。
#### 锐浪报表原理
实现一个完整的报表需要3个东西。报表模板，报表数据，渲染引擎。报表模板是一个通用的报表样式。可以通过加载不同的报表数据，得到不同的报表。报表模板就是一张张的试卷，我们的答案就是报表数据，我们把答案写在模板上面的过程，就是在实现数据的渲染，渲染引擎就是专门干这个的。

#### 使用方法
1.首先需要下载锐浪报表开发者安装包（ http://www.rubylong.cn/gridreport/download.htm ）。

![安装软件](https://a-fat-k79.github.io/IMG/k79-report-grid/%E9%94%90%E6%B5%AA%E5%AE%89%E8%A3%85%E8%BD%AF%E4%BB%B6.jpg)

2.参考”Grid++Report报表模板例子“，使用”Grid++Report 报表设计器“ 设计报表模板。

3.使用本程序渲染数据，生成报表。

![数据渲染过程图](https://a-fat-k79.github.io/IMG/k79-report-grid/%E6%95%B0%E6%8D%AE%E6%B8%B2%E6%9F%93.jpg)

****

#### 目录简介

![](https://a-fat-k79.github.io/IMG/k79-report-grid/%E7%9B%AE%E5%BD%95.jpg)

- bin 目录下面为不同系统的库文件，**需要添加到环境变量中**
- grfDir 模板文件夹，存放模板

- k79-report-grid 程序，运行方法下面讲

- k79-report-grid-0.0.1.jar 打包后的软件（基于springboot的web程序）

#### 如何打包程序

1.使用maven打包，生成k79-report-grid-0.0.1.jar。

![](https://a-fat-k79.github.io/IMG/k79-report-grid/maven%E6%89%93%E5%8C%85.jpg)

2.运行jar文件,访问首页。可以测试自己环境是否配置正确，也可以下载测试用例。

![](https://a-fat-k79.github.io/IMG/k79-report-grid/%E4%B8%BB%E9%A1%B52.jpg)
