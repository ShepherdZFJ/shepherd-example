package com.shepherd.common.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/3/27 18:06
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.shepherd.common.config.Student",
        "com.shepherd.common.config.Person"};
    }
}
