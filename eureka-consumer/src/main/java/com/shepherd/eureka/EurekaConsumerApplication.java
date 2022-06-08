package com.shepherd.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
// @EnableDiscoveryClient 注解，开启 Spring Cloud 的注册发现功能。不过从 Spring Cloud Edgware 版本开始，
// 实际上已经不需要添加 @EnableDiscoveryClient 注解，只需要引入 Spring Cloud 注册发现组件，就会自动开启注册发现的功能
// @EnableDiscoveryClient
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



    @RestController
    @Slf4j
    static class TestController {

        @Resource
        private DiscoveryClient discoveryClient;
        @Resource
        private RestTemplate restTemplate;
        @Resource
        private LoadBalancerClient loadBalancerClient;

        /**
         * 当我们关闭eureka-provider服务之后，我们我有可能还能获取其服务实例信息，只是通过该服务实例调接口会报错
         * 因为 Eureka-Client 是每 30 秒定时轮询获取增量变化的注册表，从而更新本地的服务实例缓存。
         * 因此，这里还是会去请求已关闭的服务提供者。这个一定要注意！！！
         * 当30s之后，服务实例表在本地更新了，那么就没有该服务了
         * @param name
         * @return
         */

        @GetMapping("/consumer")
        public String hello(String name) {
            // <1> 获得服务 `demo-provider` 的一个实例
            // 获取服务 `demo-provider` 对应的实例列表
//            List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider");
//                // 选择第一个
//            ServiceInstance instance = instances.size() > 0 ? instances.get(0) : null;
            ServiceInstance instance = loadBalancerClient.choose("eureka-provider");

            // <2> 发起调用
            if (instance == null) {
                throw new IllegalStateException("获取不到实例");
            }
            String targetUrl = instance.getUri() + "/provider?name=" + name;
            log.info("loadBalancerClient选择的是：{}", targetUrl);
            String response = restTemplate.getForObject(targetUrl, String.class);
            // 返回结果
            return "consumer:" + response;
        }

    }

}
