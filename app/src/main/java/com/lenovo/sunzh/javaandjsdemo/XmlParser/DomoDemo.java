package com.lenovo.sunzh.javaandjsdemo.XmlParser;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by sunzh on 2017/7/25.
 */

public class DomoDemo implements XmlDocument {

    private Document document;
    private String fileName;

    public void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createXml(String fileName) {
        Element root = this.document.createElement("employees");
        this.document.appendChild(root);
        Element employee = this.document.createElement("employee");
        Element name = this.document.createElement("name");
        name.appendChild(this.document.createTextNode("xxx"));
        employee.appendChild(name);
        Element sex = this.document.createElement("sex");
        sex.appendChild(this.document.createTextNode("m"));
        employee.appendChild(sex);
        Element age = this.document.createElement("age");
        age.appendChild(this.document.createTextNode("30"));
        employee.appendChild(age);
        root.appendChild(employee);
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
//            transformer.setOutputProperties(OutputKeys.ENCODING, "gb2312");
//            transformer.setOutputProperties(OutputKeys.INDENT,"yes");
            PrintWriter pw = new PrintWriter(fileName);
            StreamResult result = new StreamResult(pw);
            transformer.transform(source,result);
            Log.e("TAG", "生成成功");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parserXml(String fileName) {

    }
}
