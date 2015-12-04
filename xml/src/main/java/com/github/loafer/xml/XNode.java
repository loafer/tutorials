package com.github.loafer.xml;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Date Created  14-3-18
 *
 * @author zjh
 */
public class XNode {
    private XPathParser xpathParser;
    private Node node;
    private String name;
    private Properties attributes;

    public XNode(XPathParser xpathParser, Node node){
        this.xpathParser = xpathParser;
        this.node = node;
        this.name = node.getNodeName();
        this.attributes = parseAttributes(node);
    }

    public XNode selectSingleNode(String expression){
        return xpathParser.selectSingleNode(node, expression);
    }

    public List<XNode> selectNodes(String expression){
        return xpathParser.selectNodes(node, expression);
    }

    public Node getNode(){
        return node;
    }

    public List<XNode> getChildren(){
        List<XNode> childrens = new ArrayList<XNode>();
        NodeList nodeList = node.getChildNodes();
        if(nodeList != null){
            for(int i=0; i<nodeList.getLength(); i++){
                childrens.add(new XNode(xpathParser, nodeList.item(i)));
            }
        }

        return childrens;
    }

    public Boolean getBooleanAttribute(String name, Boolean defaultValue){
        String value = attributes.getProperty(name);
        return value == null ? defaultValue : Boolean.valueOf(value);
    }

    public Integer getIntAttribute(String name, Integer defaultValue){
        String value = attributes.getProperty(name);
        return value == null ? defaultValue : Integer.parseInt(value);
    }

    public String getStringAttribute(String name, String defaultValue){
        String value = attributes.getProperty(name);
        return value == null ? defaultValue : value;
    }

    private Properties parseAttributes(Node node){
        Properties attributes = new Properties();
        NamedNodeMap atrributeNodes = node.getAttributes();
        if(atrributeNodes != null){
            for(int i=0; i< atrributeNodes.getLength(); i++){
                Node atrribute = atrributeNodes.item(i);
                attributes.put(atrribute.getNodeName(), atrribute.getNodeValue());
            }
        }
        return attributes;
    }

}
