package com.github.loafer.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Date Created  14-3-18
 *
 * @author zjh
 */
public class XPathParser {
    private Document document;
    private XPath xpath;

    public XPathParser(String xml){
        commonConstructor();
        this.document = createDocument(new InputSource(new StringReader(xml)));
    }

    public XPathParser(InputStream inputStream){
        commonConstructor();
        this.document = createDocument(new InputSource(inputStream));
    }

    public XPathParser(Reader reader){
        commonConstructor();
        this.document = createDocument(new InputSource(reader));
    }

    public XNode selectSingleNode(String expression){
        return selectSingleNode(document, expression);
    }

    public XNode selectSingleNode(Object root, String expression){
        Node node = (Node) evaluate(root, expression, XPathConstants.NODE);
        if (node == null){
            return  null;
        }
        return new XNode(this, node);
    }

    public List<XNode> selectNodes(String expression){
        return selectNodes(document, expression);
    }

    public List<XNode> selectNodes(Object root, String expression){
        List<XNode> xnodes = new ArrayList<XNode>();
        NodeList nodeList = (NodeList) evaluate(root, expression, XPathConstants.NODESET);
        if(nodeList != null){
            for(int i=0; i<nodeList.getLength(); i++){
                xnodes.add(new XNode(this, nodeList.item(i)));
            }
        }
        return xnodes;
    }

    private Object evaluate(Object root, String expression, QName returnType){
        try {
            return xpath.evaluate(expression, root, returnType);
        } catch (XPathExpressionException e) {
            throw new RuntimeException("Error evaluating XPath. Cause: " + e, e);
        }
    }

    private Document createDocument(InputSource inputSource){
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            return builder.parse(inputSource);
        } catch (Exception e) {
            throw new RuntimeException("Error creating document instance.  Cause: " + e, e);
        }
    }

    private void commonConstructor(){
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }
}
