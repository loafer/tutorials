package com.github.loafer.mockito;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.List;

/**
 * 演示如何使用Mockito的Verify
 *
 * @author zhaojh
 */
public class MockitoVerifyExamplesTest {
    /**
     * 验证某个方法的调用次数
     */
    @Test
    @Ignore
    public void verifyingInteractionWithTheSpecificMethod(){
        //创建一个mock对象
        List<String> mockedList = mock(List.class);

        //使用mock对象触发一个交互
        mockedList.clear();

        //验证方法clear()是否被调用
        verify(mockedList).clear();
        //验证方法size()是否被调用
        //verify(mockedList).size();//因为没有调用过size()，所以会验证失败
    }

    /**
     * 验证某个方法的调用次数
     */
    @Test
    @Ignore
    public void verifyingNumberOfInteraction(){
        List<String> mockedList = mock(List.class);

        mockedList.clear();
        mockedList.clear();
        mockedList.size();

        //验证方法size被执行过1次
        verify(mockedList).size();
        //验证方法size被执行过1次
        verify(mockedList, times(1)).size();
        //验证方法size至少被被执行过1次
        verify(mockedList, atLeastOnce()).size();

        //验证方法clear被执行过2次
        verify(mockedList, times(2)).clear();
        //验证方法clear至少被执行过1次
        verify(mockedList, atLeastOnce()).clear();
        //验证方法clear至少被执行过2次
        verify(mockedList, atLeast(2)).clear();
        //验证方法clear最多被执行过2次
        verify(mockedList, atMost(2)).clear();
    }

    /**
     * 验证mock对象没有发生过方法调用
     */
    @Test
    @Ignore
    public void verifyingNoInteractionWithTheWholeMock(){
        List<String> mockedList = mock(List.class);

        //验证mockedList没有发生过交互
        verifyZeroInteractions(mockedList);
    }

    /**
     * 验证某个方法没被执行过
     */
    @Test
    @Ignore
    public void verifyingNoInteractionWithTheSpecificMethod(){
        List mockedList = mock(List.class);

        mockedList.size();

        //验证方法clear没有被执行过，以下两种方式等同。
        //注意never()相当与times(0)的别名
        verify(mockedList, times(0)).clear();
        verify(mockedList, never()).clear();
    }

    /**
     * 检查是否存在多余方法调用，
     * 也可理解为检查是否存在未验证的方法调用
     */
    @Test
    @Ignore
    public void  findingRedundantInvocations_thenFail(){
        List<String> mockedList = mock(List.class);

        mockedList.size();
        mockedList.clear();

        //验证方法size是否被调用
        verify(mockedList).size();
        //检查是否存在未验证的方法调用，存在则验证失败
        verifyNoMoreInteractions(mockedList); //验证失败，因为没有验证方法clear调用
    }

    /**
     * 验证实行顺序
     */
    @Test
    public void verifyingOrderOfInvocation(){
        //验证单个mock对象方法的执行顺序
        List<String> mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.size();
        mockedList.clear();

        //注意：如果以上调用顺序没有按照下面的验证顺序执行，
        //则验证失败
        InOrder inOrder1 = inOrder(mockedList);
        inOrder1.verify(mockedList).add("one");
        inOrder1.verify(mockedList).size();
        inOrder1.verify(mockedList).clear();


        //验证多个mock对象的调用顺序
        List<String> firstMock = mock(List.class);
        List<String> secondMock = mock(List.class);

        firstMock.add("was called first");
        secondMock.add("was called second");

        //注意：如果以上调用顺序没有按照下面的验证顺序执行，
        //则验证失败
        InOrder inOrder2 = inOrder(firstMock, secondMock);
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");
    }

    /**
     * 验证某个方法调用时是否使用了指定的参数
     */
    @Test
    @Ignore
    public void verifyingInteractionWithExactArgument_thenFail(){
        List<String> mockedList = mock(List.class);

        mockedList.add("one");

        //验证方法add调用时传入的参数是“one”
        verify(mockedList).add("one"); //验证成功
        //验证方法add调用时传入的参数是“two”
        verify(mockedList).add("two"); //验证失败
    }

    /**
     * 使用Argument Matcher参数验证方式调用（目的：提高参数灵活度）
     * 注意：当被验证方法具有多个参数时，
     * 在验证方法调用时，其中有一个参数使用了 Argument Matcher，
     * 则其他也必须使用 Argument Matcher。
     * see http://docs.mockito.googlecode.com/hg/latest/org/mockito/Matchers.html
     */
    @Test
    @Ignore
    public void verifyingInteractionWithArgumentMatchers(){
        List<String> mockedStringList = mock(List.class);
        List<Integer> mockedIntegerList = mock(List.class);

        mockedStringList.add("one");
        mockedIntegerList.add(999);

        //验证mockedStringList的方法add被调用，并且参数类型是String
        verify(mockedStringList).add(anyString());
        //验证mockedIntegerList的方法add被调用，并且参数类型是Integer
        verify(mockedIntegerList).add(anyInt());
    }

    /**
     * 使用ArgumentCaptor检查方法调用参数
     */
    @Test
    @Ignore
    public void verifyingInteractionWithArgumentCapture(){
        List<String> mockedList = mock(List.class);

        mockedList.addAll(Lists.<String>newArrayList("someElement"));
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //检查方法addAll是否被调用
        verify(mockedList).addAll(argumentCaptor.capture());
        //检查方法addAll调用时的参数中是否含有"someElement"
        List<String> capturedArgument = argumentCaptor.getValue();
        assertThat(capturedArgument, hasItem("someElement"));
    }
}
