package com.shepherd.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.shepherd.common.entity.Brand;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/18 22:39
 */
@Mapper
public interface BrandDAO extends BaseMapper<Brand> {
}
