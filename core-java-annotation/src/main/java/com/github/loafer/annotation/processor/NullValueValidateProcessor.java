package com.github.loafer.annotation.processor;

import com.github.loafer.annotation.declaration.NullValueValidate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by zhaojh on 2014/6/9.
 */
public class NullValueValidateProcessor {
    public static void process(Object obj){
        for(Field field: obj.getClass().getDeclaredFields()){
            try {
                if(field.isAnnotationPresent(NullValueValidate.class)){
                    NullValueValidate nullValueValidate = field.getAnnotation(NullValueValidate.class);
                    System.out.println("Processing the field: " + nullValueValidate.fieldName());

                    field.setAccessible(true);

                    if(field.get(obj) == null){
                        throw new NullPointerException("The value of the field " + field.toString() + " can't be NULL.");
                    }else{
                        System.out.println("Value of the Object: " + field.get(obj));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
