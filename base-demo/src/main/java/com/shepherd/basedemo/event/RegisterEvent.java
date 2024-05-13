package com.shepherd.basedemo.event;

import com.shepherd.basedemo.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 15:57
 *
 * 事件
 */
@Getter
public class RegisterEvent extends ApplicationEvent {
    private User user;

    public RegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
