package com.github.loafer.xml;

import com.github.loafer.io.Resources;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Date Created  14-3-20
 *
 * @author zjh
 */
public class XPathParserUnitTest {
    private XPathParser xpathParser;

    @Before
    public void before() throws IOException {
        xpathParser = new XPathParser(Resources.getResourceAsReader("example.xml"));
    }

    public void after(){

    }

    @Test
    public void testSelectSingleNode(){
        Assert.assertNotNull(xpathParser.selectSingleNode("datagrid/repository"));
    }

    @Test
    public void testSelectNodes(){
        Assert.assertEquals("failuer - number not same", 2, xpathParser.selectNodes("datagrid/headers/row/header").size());
    }

    @Test
    public void testXNodeAttribute(){
        XNode xnode = xpathParser.selectSingleNode("datagrid/repository");
        Assert.assertEquals("failure - String not same", "repository", xnode.getStringAttribute("bean", null));
        Assert.assertEquals("failure - String not same", "com.github.loafer.mybaits.mappers.UserMapper.selectPaging", xnode.getStringAttribute("statment", null));
    }
}
