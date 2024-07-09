package com.shepherd.basedemo.advice;

import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/6/13 14:45
 */
@Data
public class BizException extends RuntimeException {

    private Integer code;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(ResponseStatusEnum responseStatusEnum) {
        super(responseStatusEnum.getMsg());
        this.code = responseStatusEnum.getCode();
    }

}