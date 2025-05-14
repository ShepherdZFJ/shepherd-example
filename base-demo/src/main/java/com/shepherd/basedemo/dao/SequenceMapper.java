package com.shepherd.basedemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shepherd.basedemo.entity.Sequence;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2025/1/7 14:19
 */

public interface SequenceMapper extends BaseMapper<Sequence> {

    default Sequence getForUpdate(String sequenceType){
        return selectOne(Wrappers.<Sequence>lambdaQuery().eq(Sequence::getSequenceType,sequenceType).last(" for update"));
    }
}

