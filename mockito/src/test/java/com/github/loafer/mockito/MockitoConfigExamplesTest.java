package com.github.loafer.mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 *
 * 如何使用Mockito配置各种行为的列子
 *
 * @author zjh
 */
public class MockitoConfigExamplesTest {

    /**
     * 配置方法调用返回值--方式1（推荐此方式，可读性强）
     */
    @Test
    @Ignore
    public void configSimpleReturnBehavior_1(){
        List<String> mockedList = mock(List.class);

        when(mockedList.get(0)).thenReturn("first");

        String value = mockedList.get(0);

        verify(mockedList).get(0);
        assertEquals(value, "first");
    }

    /**
     * 配置方法调用返回值--方式2 （不推荐此方式，可读性差，只用与特殊情况）
     */
    @Test
    @Ignore
    public void configSimpleReturnBehavior_2(){
        List<String> mockedList = mock(List.class);

        doReturn("first").when(mockedList).get(0);

        String value = mockedList.get(0);
        assertEquals(value, "first");
    }

    /**
     * 配置方法调用抛出异常
     */
    @Test(expected = RuntimeException.class)
    @Ignore
    public void configThrowExceptionOnMethodCall_thenFail(){
        List<String> mockedList = mock(List.class);

        when(mockedList.get(0)).thenThrow(new RuntimeException());

        mockedList.get(0);
    }

    /**
     * 配置方法连续调用行为
     */
    @Test(expected = RuntimeException.class)
    @Ignore
    public void configBehaviorOfMultipleCalls_thenFail(){
        List<String> mockedList = mock(List.class);

        when(mockedList.add(anyString())).thenReturn(false).thenThrow(new RuntimeException());

        mockedList.add("hello");
        mockedList.add("world");//此调用将抛出异常
    }

    /**
     * 配置监控非mock对象行为
     * see 官方文档 13. Spying on real objects
     */
    @Test
    @Ignore
    public void configBehaviorOfSpy(){
        List<String> list = new ArrayList<>();
        List<String> spy = spy(list);

        //可选步骤，此处可以sub一些方法
        when(spy.size()).thenReturn(100);

        //以下调用都是真实调用ArrayList的add方法
        spy.add("one");
        spy.add("two");

        int size = spy.size();
        assertThat(size, equalTo(100));
    }

    /**
     * 配置模拟对象调用真实对象的方法,而不是stub的方法
     */
    @Test
    @Ignore
    public void configToCallTheRealMethodOnMock(){
        List<String> mockedList = mock(ArrayList.class);

        //当调用size方法时，触发ArrayList的size方法调用
        when(mockedList.size()).thenCallRealMethod();

        int size = mockedList.size();
        assertThat(size, equalTo(0));
    }

    /**
     * 配置模拟方法调用自定义的Answer
     */
    @Test
    public void configMockMethodCallWithCustomAnswer(){
        List<String> mockedList = mock(List.class);

        when(mockedList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + Arrays.asList(args);
            }
        });

        String value = mockedList.get(1);
        assertEquals(value, "called with arguments: [1]");
    }
}
