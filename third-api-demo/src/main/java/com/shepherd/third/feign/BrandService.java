package com.shepherd.third.feign;

import com.shepherd.third.dto.Brand;
import com.shepherd.third.global.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/1 17:56
 */
@FeignClient(name = "brandService", url = "${product-server}", path = "/brand")
public interface BrandService {

    @GetMapping("/category/{categoryId}")
    ResponseVO<List<Brand>> getList(@PathVariable("categoryId") Long categoryId);


}
