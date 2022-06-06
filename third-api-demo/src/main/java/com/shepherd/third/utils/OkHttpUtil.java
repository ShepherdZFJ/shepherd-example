package com.shepherd.third.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/6 10:47
 */
@Slf4j
public class OkHttpUtil{

    private static OkHttpClient okHttpClient ;

    static {
         okHttpClient = new OkHttpClient.Builder()
                // 设置连接超时时间
                .connectTimeout(Duration.ofSeconds(30))
                // 设置读超时时间
                .readTimeout(Duration.ofSeconds(60))
                // 设置写超时时间
                .writeTimeout(Duration.ofSeconds(60))
                // 设置完整请求超时时间
                .callTimeout(Duration.ofSeconds(120))
                // 添加一个拦截器
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    return chain.proceed(request);
                })
                // 注册事件监听器
                .eventListener(new EventListener() {
                    @Override
                    public void callEnd(@NotNull Call call) {
                        log.info("----------callEnd--------");
                        super.callEnd(call);
                    }
                })
                .build();

    }

    /**
     * get
     * @param url   请求的url
     * @param param 请求的参数
     * @return
     */
    public static  String get(String url, Map<String, String> param) {
        String responseBody = "";
        StringBuilder sb = new StringBuilder(url);
        if (param!= null && param.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = param.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post exception：{}", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post exception：{}", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * get
     * @param url     请求的url
     * @param param 请求的参数
     * @return
     */
    public static String getForHeader(String url, Map<String, String> param) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (param != null && param.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = param.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .addHeader("key", "value")
                .url(sb.toString())
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post exception：{}", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * Post请求发送JSON数据
     * @param url 请求Url
     * @param jsonParams 请求的JSON
     */
    public static String postJsonParams(String url, String jsonParams) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post exception：{}", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * Post请求发送xml类型数据
     * @param url 请求Url
     * @param xml 请求的xml
     */
    public static String postXmlParams(String url, String xml) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post exception：{}", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }
}

