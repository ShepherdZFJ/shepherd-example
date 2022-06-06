package com.shepherd.third.retrofit;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.shepherd.third.dto.Brand;
import com.shepherd.third.global.ResponseVO;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/2 18:44
 */
@RetrofitClient(baseUrl = "${product-server}")
public interface RetrofitBrandService {
    @GET("brand/category/{categoryId}")
    ResponseVO<List<Brand>> getList(@Path("categoryId") Long categoryId);

    @POST("brand")
    ResponseVO addBrand(@Body Brand brand);


}
