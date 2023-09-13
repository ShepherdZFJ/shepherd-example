package com.shepherd.common.config;

import com.shepherd.common.bean.Boo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/1 23:21
 */
//@ComponentScan(basePackages = {"com.shepherd.common.bean", "com.shepherd.common.bean1"})
//@ComponentScans({@ComponentScan("com.shepherd.common.bean"), @ComponentScan("com.shepherd.common.bean1")})
//@ComponentScan("com.shepherd.common.bean")
//@ComponentScan("com.shepherd.common.bean1")
@ComponentScan(basePackages = {"com.shepherd.common.config"})
@Configuration
public class MyConfig {

//    @Bean
//    @Conditional(ChineseCondition.class)
//    public Language chinese() {
//        return Language.builder().id(1l).content("华流才是最屌的").build();
//    }
//
//    @Bean
//    @Conditional(EnglishCondition.class)
//    public Language english() {
//        return Language.builder().id(2l).content("english is good").build();
//    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public Boo boo() {
        return new Boo();
    }



    public static void main(String[] args) {
//        System.setProperty("lang", "zh_CN");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        Boo boo = applicationContext.getBean(Boo.class);
        System.out.println("拿到boo对象了：" + boo);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        // 遍历Spring容器中的beanName
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
        applicationContext.close();
//        BeanDefinition beanDefinition = applicationContext.getBeanDefinition("boo");
//        System.out.println(beanDefinition.getDescription());
    }
}
