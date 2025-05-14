package com.shepherd.basedemo.validator;

import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2025/5/9
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private Class<? extends CheckEnumValue> clz;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        clz = constraintAnnotation.linkEnum();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        if (!clz.isEnum()) {
            return true;
        }
        CheckEnumValue[] enumConstants = clz.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            return true;
        }
        CheckEnumValue enumConstant = enumConstants[0];
        List enumValue = enumConstant.getEnumValue();
        if (CollectionUtils.isEmpty(enumValue)) {
            return true;
        }
        if (enumValue.contains(value)) {
            return true;
        }
        return false;
    }
}
