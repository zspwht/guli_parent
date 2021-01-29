package com.atguigu.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan("com.atguigu")
public class EduApplication extends SpringBootServletInitializer {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EduApplication.class);
    }

    public static void main(String[] args) {
        ApplicationContext cxt = SpringApplication.run(EduApplication.class,args);
        String[] beanDefinitionNames = cxt.getBeanDefinitionNames();
        //Arrays.sort(beanDefinitionNames);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    /**
     * 获取加载的bean和类类型信息
     * @param args
     * @throws Exception
     */
   /* @Override
    public void run(String... args) throws Exception {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName+" of type:: "+applicationContext.getBean(beanDefinitionName).getClass() );
        }
    }*/
}
