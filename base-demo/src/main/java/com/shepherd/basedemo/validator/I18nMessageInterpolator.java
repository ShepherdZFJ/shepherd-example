package com.shepherd.basedemo.validator;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.annotation.Resource;
import javax.validation.MessageInterpolator;
import java.util.Locale;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2025/5/14
 */
public class I18nMessageInterpolator implements MessageInterpolator {

    private final MessageSource messageSource;

    public I18nMessageInterpolator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return messageSource.getMessage(messageTemplate, null, LocaleContextHolder.getLocale());
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return messageSource.getMessage(messageTemplate, null, locale);
    }
}
