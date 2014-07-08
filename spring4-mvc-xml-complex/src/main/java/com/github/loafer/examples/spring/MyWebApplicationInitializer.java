package com.github.loafer.examples.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.ArrayList;
import java.util.List;

/**
 * 按照约定将classpath下config目录中的所有符合*-servlet.xml格式的文件
 * 在web容器启动时自动注册为spring mvc 的dispatcherServlet。
 *
 * 注册规则：
 *  1、被注册的DispatcherServlet的配置文件必须按此方式命名[servlet-name]-servlet.xml。
 *  2、使用符合规则1规定的命名规则文件名，将文件名中分割符'-'之前的部分作为被注册的servlet名称。
 *  3、被注册的servlet默认使用'/servlet-name/*'形式来设置servlet-mapping的'url-pattern'值。
 *
 * @author zhaojh
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer{
    private static final String SERVLET_CONFIG_LOCATION_PATH = "classpath*:config/";
    private static final String SERVLET_CONFIG_FILENAME_REGX = "*-servlet.xml";
    private static final String SERVLET_CONFIG_MATCHER = SERVLET_CONFIG_LOCATION_PATH + SERVLET_CONFIG_FILENAME_REGX;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        List<ServletConfig> servletConfigList = getServletConfigList();
        if(!servletConfigList.isEmpty()){
            for(ServletConfig servletConfig : servletConfigList){
                logger.info(
                        "Dynamic registration servlet: {} , mapping: {}",
                        servletConfig.getServletName(),
                        servletConfig.getServletMapping()
                );

                XmlWebApplicationContext appContext = new XmlWebApplicationContext();
                appContext.setConfigLocation(servletConfig.getServletConfigLocation());

                ServletRegistration.Dynamic registration = servletContext.addServlet(
                        servletConfig.getServletName(),
                        new DispatcherServlet(appContext)
                );

                registration.setLoadOnStartup(1);
                registration.addMapping(servletConfig.getServletMapping());
            }

            logger.info("Dynamic registration finish.");
        }
    }

    private List<ServletConfig> getServletConfigList(){
        List<ServletConfig> configList = new ArrayList<ServletConfig>();
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

        try{
            Resource[] resources = resourceResolver.getResources(SERVLET_CONFIG_MATCHER);

            for(Resource resource : resources){
                String fileName = resource.getFilename();
                String servletName = getServletNameFromFileName(fileName);
                String servletConfigLocation = SERVLET_CONFIG_LOCATION_PATH + fileName;
                configList.add(new ServletConfig(servletName, servletConfigLocation));

                logger.info("found the servlet config file: {}", servletConfigLocation);
            }

            if(null == resources || resources.length == 0) logger.info("没有找到有效资源。");
        }catch(Exception e){
            throw new RuntimeException("Dynamic regist servlet fails. ", e);
        }

        return configList;
    }

    private String getServletNameFromFileName(String fileName){
        return fileName.split("-")[0];
    }

    private class ServletConfig{
        private String servletName;
        private String servletConfigLocation;
        private String servletMapping;

        private ServletConfig(String servletName, String servletConfigLocation) {
            this.servletName = servletName;
            this.servletConfigLocation = servletConfigLocation;
            this.servletMapping = "/" + servletName + "/*";
        }

        public String getServletName() {
            return servletName;
        }

        public String getServletConfigLocation() {
            return servletConfigLocation;
        }

        public String getServletMapping() {
            return servletMapping;
        }

        @Override
        public String toString() {
            return "ServletConfig{" +
                    "servletName='" + servletName + '\'' +
                    ", servletConfigLocation='" + servletConfigLocation + '\'' +
                    ", servletMapping='" + servletMapping + '\'' +
                    '}';
        }
    }
}
