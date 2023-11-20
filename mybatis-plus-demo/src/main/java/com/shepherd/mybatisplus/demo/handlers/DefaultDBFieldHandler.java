package com.shepherd.mybatisplus.demo.handlers;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/11/16 18:01
 */

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.shepherd.mybatisplus.demo.entity.BaseDO;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * 公共字段属性值自动填充
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();

            Date current = new Date();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(baseDO.getCreateTime())) {
                baseDO.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(baseDO.getUpdateTime())) {
                baseDO.setUpdateTime(current);
            }

            baseDO.setCreateBy(1001L);
            baseDO.setUpdateBy(1002L);

//            // 根据登录上下文信息设置创建人和更新人
//            LoginUser currentUser = RequestUserHolder.getCurrentUser();
//            // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
//            if (Objects.nonNull(currentUser) && Objects.isNull(baseDO.getCreator())) {
//                baseDO.setCreator(currentUser.getId());
//            }
//            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
//            if (Objects.nonNull(currentUser) && Objects.isNull(baseDO.getUpdater())) {
//                baseDO.setUpdater(currentUser.getId());
//            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        Object modifier = getFieldValByName("updateBy", metaObject);
        if (Objects.isNull(modifier)) {
            setFieldValByName("updateBy", 1002L, metaObject);
        }

//        LoginUser currentUser = RequestUserHolder.getCurrentUser();
//        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
//        if (Objects.nonNull(currentUser) && Objects.isNull(modifier)) {
//            setFieldValByName("updater", currentUser.getId(), metaObject);
//        }

    }
}
