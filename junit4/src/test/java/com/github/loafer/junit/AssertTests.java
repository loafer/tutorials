package com.github.loafer.junit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Date Created  14-3-17
 *
 * @author zjh
 */
public class AssertTests {

    @Test
    public void testAssertArrayEquals(){
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        Assert.assertArrayEquals("failure - byte arrays not same", expected, actual);
    }

    @Test
    @Ignore
    public void testAssertEquals(){
        Assert.assertEquals("failure - strings not same", 51, 52);
    }
}
