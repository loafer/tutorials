package com.github.loafer.examples.context.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 动态加载国际化文件，并支持classpath前缀和Ant-style路径风格。
 *
 * @author zjh
 */
public class MyResourceBundleMessageSource extends ResourceBundleMessageSource {
    private static final String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
    private static final String CLASSPATH_URL_PREFIX = "classpath:";
    private static final String RESOURCE_FILE_SUFFIX = ".properties";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setBasename(String basename) {
        if(basename.startsWith(CLASSPATH_URL_PREFIX) ||
                basename.startsWith(CLASSPATH_ALL_URL_PREFIX)){
            logger.info("Dynamic load resource bundles.");
            ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

            try{
                Resource[] resources = null;
                if(basename.endsWith(RESOURCE_FILE_SUFFIX)){
                    resources = resourceResolver.getResources(basename);
                }else{
                    resources = resourceResolver.getResources(basename + RESOURCE_FILE_SUFFIX);
                }

                String[] basenames = new String[resources.length];
                for(int i=0, length=resources.length; i<length; i++){
                    basenames[i] = getResourceBasename(resources[i].getFile().getAbsolutePath());
                    logger.info("found resource bundles: {}", basenames[i]);
                }

                logger.info("Find finish.");
                setBasenames(basenames);
            }catch (Exception e){
                throw new RuntimeException("Dynamic load resource bundles fails.", e);
            }
        }else{
            logger.info("Static load resource bundles.");
            super.setBasename(basename);
        }
    }


    private String getResourceBasename(String fullname){
        String basename = fullname.substring(0, fullname.lastIndexOf("."));

        if(basename.indexOf(".jar!/") > -1){
            return basename.substring(basename.indexOf(".jar!/")+6, basename.length());
        }

        if(basename.indexOf("classes/") > -1){
            return basename.substring(basename.indexOf("classes/")+8, basename.length());
        }

        return fullname;
    }
}
