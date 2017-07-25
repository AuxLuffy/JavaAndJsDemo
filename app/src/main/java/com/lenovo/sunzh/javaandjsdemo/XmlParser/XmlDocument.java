package com.lenovo.sunzh.javaandjsdemo.XmlParser;

/**
 * Created by sunzh on 2017/7/25.
 */

public interface XmlDocument {
    /**
     * 建立XML文档
     * @param fileName 文件全路径名
     */
    void createXml(String fileName);

    /**
     * 解析XML文档
     * @param fileName 文件全路径名
     */
    void parserXml(String fileName);
}
