package com.github.loafer.annotation;

import com.github.loafer.annotation.declaration.NullValueValidate;
import com.github.loafer.annotation.processor.NullValueValidateProcessor;

/**
 * Created by zhaojh on 2014/6/9.
 */
public class AnnotationExample {
    @NullValueValidate(fieldName = "testVar1")
    private String testVar1;

    @NullValueValidate(fieldName = "testVar2")
    private String testVar2 = "hello world";

    public AnnotationExample(){
        NullValueValidateProcessor.process(this);
    }

    public static void main(String[] args){
        AnnotationExample annotationExample = new AnnotationExample();
    }
}
