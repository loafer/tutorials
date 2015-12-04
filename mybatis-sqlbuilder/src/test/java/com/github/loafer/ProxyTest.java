package com.github.loafer;

import com.github.quick4j.core.repository.mybaits.sqlbuilder.mapper.Mapper;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojh.
 */
public class ProxyTest {
    private Map<String, Class> proxyClasses = new HashMap<String, Class>();

    @Test
    public void testProxy() throws NotFoundException, CannotCompileException {

        System.out.println(getProxyClass("One"));
        System.out.println(getProxyClass("Two"));
        System.out.println(getProxyClass("Three"));
        System.out.println(getProxyClass("One"));
        System.out.println(getProxyClass("One").getSimpleName());
        System.out.println(ClassUtils.getPackageName(getProxyClass("One")));
    }

    @Test
    public void test2() throws NotFoundException, CannotCompileException {
        ClassPool cp = ClassPool.getDefault();
        String classContent = "public class Foo{public String sayHello(){return \"hello\"}}";
        byte[] b = classContent.getBytes();
        String name = "Foo";
        cp.insertClassPath("com.github.quick4j");
        CtClass ctClass = cp.get(name);
        Class clazz = ctClass.toClass();
        System.out.println(clazz);

    }

    private Class getProxyClass(String proxyName) throws NotFoundException, CannotCompileException {
        if(proxyClasses.containsKey(proxyName)){
            return proxyClasses.get(proxyName);
        }

        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.github.quick4j.core.repository.mybaits.sqlbuilder.mapper.Mapper");
        String className = String.format("com.github.quick4j.mapper.%sMapper", proxyName);
        CtClass ctInterface = pool.makeInterface(className, ctClass);
        Class proxyClass = ctInterface.toClass();
        proxyClasses.put(proxyName, proxyClass);
        return proxyClass;
    }



}
