package com.shepherd.third.utils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/6 16:53
 */

@Slf4j
public class HttpClientUtil {
    /**
     * httpclient基础配置信息
     */
    private static final RequestConfig requestConfig = RequestConfig.custom()
            // 设置连接超时时间(单位毫秒)
            .setConnectTimeout(2000)
            // 设置请求超时时间(单位毫秒)
            .setConnectionRequestTimeout(2000)
            // socket读写超时时间(单位毫秒)
            .setSocketTimeout(1000)
            // 设置是否允许重定向(默认为true)
            .setRedirectsEnabled(true)
            //是否启用内容压缩，默认true
            .setContentCompressionEnabled(true)
            .build();
    /**
     * 获得Http客户端
     */
    private static final CloseableHttpClient HTTP_CLIENT = HttpClientBuilder.create()
            .setRetryHandler(new DefaultHttpRequestRetryHandler()) //失败重试，默认3次
            .build();

//    /**
//     * 异步Http客户端
//     */
//    private static final CloseableHttpAsyncClient HTTP_ASYNC_CLIENT = HttpAsyncClients.custom()
//            .setDefaultRequestConfig(requestConfig)
//            .build();


    /**
     * @desc 异步请求
     * @param httpRequestBase
     */
//    private static void executeAsync(HttpRequestBase httpRequestBase) {
//        HTTP_ASYNC_CLIENT.start();
//        HTTP_ASYNC_CLIENT.execute(httpRequestBase, new FutureCallback<HttpResponse>() {
//            @SneakyThrows
//            @Override
//            public void completed(HttpResponse httpResponse) {
//                log.info("thread id is : {}" ,Thread.currentThread().getId());
//
//                StringBuffer stringBuffer = new StringBuffer();
//                for (Header header : httpRequestBase.getAllHeaders()) {
//                    stringBuffer.append(header.toString()).append(",");
//                }
//                log.info("请求头信息: {}", stringBuffer.toString());
//
//
//                String responseResult = null;
//                HttpEntity responseEntity = httpResponse.getEntity();
//                log.info("响应状态为:{}", httpResponse.getStatusLine());
//                if (responseEntity != null) {
//                    responseResult = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
//                    log.info("响应内容为:{}",responseResult);
//
//                }
//
//                stringBuffer = new StringBuffer();
//                for (Header header : httpResponse.getAllHeaders()) {
//                    stringBuffer.append(header.toString()).append(",");
//                }
//                log.info("响应头信息: {}", stringBuffer.toString()));
//
//
//            }
//
//            @Override
//            public void failed(Exception e) {
//                log.info("thread id is : {}",Thread.currentThread().getId());
//                log.error("Exception responseResult：{}", e);
//                e.printStackTrace();
//            }
//
//            @Override
//            public void cancelled() {
//                log.info(httpRequestBase.getRequestLine() + " cancelled");
//            }
//        });
//    }

    /**
     * @desc String请求
     * @param httpRequestBase
     * @return String
     */
    private static String execute(HttpRequestBase httpRequestBase) {
        log.info("请求地址: {},请求类型: {}", httpRequestBase.getURI().toString(), httpRequestBase.getMethod());

        StringBuffer stringBuffer = new StringBuffer();
        for (Header header : httpRequestBase.getAllHeaders()) {
            stringBuffer.append(header.toString()).append(",");
        }
        log.info("请求头信息: {}", stringBuffer.toString());


        log.info("请求参数: {}", httpRequestBase.getURI().getQuery());

        String responseResult = null;
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 将上面的配置信息 运用到这个Get请求里
            httpRequestBase.setConfig(requestConfig);
            long t1 = System.nanoTime();//请求发起的时间
            response = HTTP_CLIENT.execute(httpRequestBase);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            log.info("响应状态为:{}",response.getStatusLine());
            long t2 = System.nanoTime();//收到响应的时间
            if (responseEntity != null) {

                responseResult = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);

                log.info("响应内容为:{}",responseResult);

            }

            stringBuffer = new StringBuffer();
            for (Header header : response.getAllHeaders()) {
                stringBuffer.append(header.toString()).append(",");
            }
            log.info("响应头信息: {}", stringBuffer.toString());

            log.info("执行时间: {}", (t2 - t1));

        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("Exception responseResult：{}", e.getMessage());
                e.printStackTrace();
            }

        }
        return responseResult;

    }

    /**
     * @desc byte[]请求
     * @param httpRequestBase
     * @return byte[]
     */
    private static byte[] executeBytes(HttpRequestBase httpRequestBase) {
        log.info("请求地址: {},请求类型: {}", httpRequestBase.getURI().toString(),httpRequestBase.getMethod());
        StringBuffer stringBuffer = new StringBuffer();
        for (Header header : httpRequestBase.getAllHeaders()) {
            stringBuffer.append(header.toString()).append(",");
        }
        log.info("请求头信息: {}", stringBuffer.toString());


        log.info("请求参数: {}", httpRequestBase.getURI().getQuery());

        byte[] bytes = null;
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 将上面的配置信息 运用到这个Get请求里
            httpRequestBase.setConfig(requestConfig);
            long t1 = System.nanoTime();//请求发起的时间
            response = HTTP_CLIENT.execute(httpRequestBase);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            log.info("响应状态为:{}", response.getStatusLine());
            long t2 = System.nanoTime();//收到响应的时间
            if (responseEntity != null) {
                bytes = EntityUtils.toByteArray(responseEntity);

                //判断是否需要解压，即服务器返回是否经过了gzip压缩--start
                Header responseHeader = response.getFirstHeader("Content-Encoding");
                if (responseHeader != null && responseHeader.getValue().contains("gzip")) {
                    GZIPInputStream gzipInputStream = null;
                    ByteArrayOutputStream out = null;
                    try {
                        gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(bytes));
                        out = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int offset = -1;
                        while ((offset = gzipInputStream.read(buffer)) != -1) {
                            out.write(buffer, 0, offset);
                        }
                        bytes = out.toByteArray();

                    } catch (IOException e) {
                        log.error("Exception responseResult：{}", e.getMessage());
                        e.printStackTrace();
                    } finally {
                        try {
                            gzipInputStream.close();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //判断是否需要解压，即服务器返回是否经过了gzip压缩--end

                log.info("响应byte长度:{}", bytes.length);
            }

            stringBuffer = new StringBuffer();
            for (Header header : response.getAllHeaders()) {
                stringBuffer.append(header.toString()).append(",");
            }
            log.info("响应头信息: {}", stringBuffer.toString());

            log.info("执行时间: {}", (t2 - t1));

        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return bytes;

    }

    /**
     * @desc get请求
     * @param url
     * @return String

     */
    public static String get(String url) {
        return get(url, new HashMap<>());
    }

    /**
     * @desc get请求
     * @param url, params
     * @return String
     */
    public static String get(String url, Map<String, Object> params) {
        HttpGet httpGet = null;
        List<NameValuePair> list = new ArrayList<>();
        for (String key : params.keySet()) {
            list.add(new BasicNameValuePair(key, params.get(key).toString()));
        }

        // 由客户端执行(发送)Get请求
        try {
            URI uri = new URIBuilder(url).addParameters(list).build();
            // 创建Get请求
            httpGet = new HttpGet(uri);

        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        }
        return execute(httpGet);
    }

    /**
     * @desc get请求
     * @param url, params
     * @return byte[]
     */
    public static byte[] getBytes(String url, Map<String, Object> params) {
        HttpGet httpGet = null;
        List<NameValuePair> list = new ArrayList<>();
        for (String key : params.keySet()) {
            list.add(new BasicNameValuePair(key, params.get(key).toString()));
        }

        // 由客户端执行(发送)Get请求
        try {
            URI uri = new URIBuilder(url).addParameters(list).build();
            // 创建Get请求
            httpGet = new HttpGet(uri);

        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        }
        return executeBytes(httpGet);
    }

    /**
     * @desc post请求
     * @param url
     * @return String
     */
    public static String post(String url) {
        return post(url, new HashMap<>());
    }
    /**
     * @desc post请求
     * @param url, params
     * @return String
     */
    public static String post(String url, Map<String, Object> params) {
        HttpPost httpPost = null;
        List<NameValuePair> list = new ArrayList<>();
        for (String key : params.keySet()) {
            list.add(new BasicNameValuePair(key, params.get(key).toString()));
        }

        try {
            URI uri = new URIBuilder(url).addParameters(list).build();
            httpPost = new HttpPost(uri);
        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        }

        return execute(httpPost);
    }

    /**
     * @desc post请求
     * @param url, params
     * @return byte[]
     */
    public static byte[] postBytes(String url, Map<String, Object> params) {
        HttpPost httpPost = null;
        List<NameValuePair> list = new ArrayList<>();
        for (String key : params.keySet()) {
            list.add(new BasicNameValuePair(key, params.get(key).toString()));
        }

        try {
            URI uri = new URIBuilder(url).addParameters(list).build();
            httpPost = new HttpPost(uri);
        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        }

        return executeBytes(httpPost);
    }

    /**
     * @desc post请求
     * @param url, json
     * @return String
     */
    public static String postJson(String url, String json) {
        return postJson(url, json, false);
    }

    /**
     * @desc post请求
     * @param url, json, gzip
     * @return String
     */
    public static String postJson(String url, String json, boolean gzip) {
        HttpPost httpPost = null;
        try {
            URI uri = new URIBuilder(url).build();
            httpPost = new HttpPost(uri);

            // post请求是将参数放在请求体里面传过去的,这里将entity放入post请求体中

            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

            if (gzip) {
                httpPost.setHeader("Content-Encoding", "gzip");
                ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
                originalContent.write(json.getBytes(StandardCharsets.UTF_8));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
                originalContent.writeTo(gzipOut);
                gzipOut.finish();
                httpPost.setEntity(new ByteArrayEntity(baos
                        .toByteArray(), ContentType.create("text/plain", "utf-8")));
            } else {
                StringEntity entity = new StringEntity(json, "UTF-8");
                httpPost.setEntity(entity);

            }

        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        }
        return execute(httpPost);
    }

    /**
     * @desc post请求byte流
     * @param url, bytes
     * @return String
     */
    public static String postInputBytes(String url, byte[] bytes) {
        return postInputBytes(url, bytes, false);
    }

    /**
     * @desc post请求byte流
     * @param url, bytes, gzip
     * @return String
     */
    public static String postInputBytes(String url, byte[] bytes, boolean gzip) {
        HttpPost httpPost = null;
        try {
            URI uri = new URIBuilder(url).build();
            httpPost = new HttpPost(uri);

            // post请求是将参数放在请求体里面传过去的,这里将entity放入post请求体中
            if (gzip) {
                httpPost.setHeader("Content-Encoding", "gzip");
                ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
                originalContent.write(bytes);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
                originalContent.writeTo(gzipOut);
                gzipOut.finish();
                httpPost.setEntity(new ByteArrayEntity(baos
                        .toByteArray(), ContentType.create("text/plain", "utf-8")));
            } else {
                ByteArrayEntity entity = new ByteArrayEntity(bytes, ContentType.create("text/plain", "utf-8"));
                httpPost.setEntity(entity);
            }

        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        }

        return execute(httpPost);
    }

    /**
     * @desc post请求流
     * @param url, is
     * @return String
     */
    public static String postInputStream(String url, InputStream is) {
        return postInputStream(url, is, false);
    }

    /**
     * @desc post请求流
     * @param url, is, gzip
     * @return String
     * @author liangliang
     * @date 2019/3/11 13:23
     */
    public static String postInputStream(String url, InputStream is, boolean gzip) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int ch;
        byte[] bytes = null;
        try {
            while ((ch = is.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, ch);
            }
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        }
        return postInputBytes(url, bytes, gzip);
    }

    /**
     * @desc post请求文件
     * @param url, files
     * @return String
     */
    public static String postFile(String url, File[] files) {
        return postFile(url, new HashMap<>(), files);
    }


    /**
     * @desc post请求文件
     * @param url, params, files
     * @return String
     */
    public static String postFile(String url, Map<String, Object> params, File[] files) {
        HttpPost httpPost = null;
        try {
            URI uri = new URIBuilder(url).build();
            httpPost = new HttpPost(uri);

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            String filesKey = "files";
            for (File file : files) {
                //multipartEntityBuilder.addPart(filesKey,new FileBody(file)); //与下面的语句作用相同
                //multipartEntityBuilder.addBinaryBody(filesKey, file);

                // 防止服务端收到的文件名乱码。 我们这里可以先将文件名URLEncode，然后服务端拿到文件名时在URLDecode。就能避免乱码问题。
                // 文件名其实是放在请求头的Content-Disposition里面进行传输的，如其值为form-data; name="files"; filename="头像.jpg"
                multipartEntityBuilder.addBinaryBody(filesKey, file, ContentType.DEFAULT_BINARY, URLEncoder.encode(file.getName(), "utf-8"));

            }

            // 其它参数(注:自定义contentType，设置UTF-8是为了防止服务端拿到的参数出现乱码)
            ContentType contentType = ContentType.create("text/plain", StandardCharsets.UTF_8);
            for (String key : params.keySet()) {
                multipartEntityBuilder.addTextBody(key, params.get(key).toString(), contentType);
            }
            HttpEntity entity = multipartEntityBuilder.build();

            // post请求是将参数放在请求体里面传过去的,这里将entity放入post请求体中
            httpPost.setEntity(entity);

        } catch (Exception e) {
            log.error("Exception responseResult：{}", e.getMessage());
            e.printStackTrace();
        }

        return execute(httpPost);
    }


}

