package com.shepherd.basedemo;

import com.shepherd.basedemo.service.SequenceService;
import com.shepherd.basedemo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = "com.shepherd.basedemo.dao")
@EnableCaching
@EnableScheduling
@Slf4j
public class BaseDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BaseDemoApplication.class, args);
//        SequenceService sequenceService = context.getBean(SequenceService.class);
//        for (int i = 0; i < 1000; i++) {
//            long l = sequenceService.next("test_seq");
//            String seqId = StringUtil.subOrLefPad(String.valueOf(l), 6);//补 0
//            log.info("Sequence Id:{}", seqId);
//        }
    }
    

}
