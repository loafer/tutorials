package com.github.loafer.annotation;

import com.github.loafer.annotation.declaration.ClassLeveAnnotation;
import com.github.loafer.annotation.declaration.MethodLeveAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by zhaojh on 2014/6/9.
 */
@ClassLeveAnnotation
public class AnAnnotatedClass {

    @Override
    @MethodLeveAnnotation
    public String toString() {
        return "Overriden toString method";
    }

    public static void main(String[] args){
        AnAnnotatedClass annotatedClass = new AnAnnotatedClass();

        for(Annotation annotation: annotatedClass.getClass().getAnnotations()){
            System.out.println("Annotations on class: " + annotation);
        }

        for(Method method : annotatedClass.getClass().getMethods()){
            if(method.isAnnotationPresent(MethodLeveAnnotation.class)){
                for(Annotation annotation: method.getDeclaredAnnotations()){
                    System.out.println("Annotations on method: " + annotation);
                }
            }
        }

    }
}
