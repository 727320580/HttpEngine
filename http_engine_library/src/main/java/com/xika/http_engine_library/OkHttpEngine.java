package com.xika.http_engine_library;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/13
 * Vwesion 1.0
 * Dsscription:  Http框架网络引擎
 */

public class OkHttpEngine implements BaseEngine {
    private static OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void get(final Context context, String baseUrl, Map<String, Object> hashMap, final BaseCallBack callBack) {

        String url = HttpUtils.getUrl(baseUrl, hashMap);
        Log.i("请求地址:", url);

        final Request request = new Request.Builder()
                .url(url)
                .get()
                .tag(context)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                callBack.onSucess(result);
                Log.i("get返回数据", result);
            }
        });
    }

    @Override
    public void post(Context context, String baseUrl, Map<String, Object> hashMap, final BaseCallBack callBack) {
        String url = HttpUtils.getUrl(baseUrl, hashMap);
        Log.i("请求地址:", url);

        Request request = new Request.Builder()
                .url(baseUrl)
                .post(appendBady(hashMap))
//              .post(RequestBody.create(MediaType.parse("UTF-8"), getParams(hashMap)))
                .tag(context)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("post请求结果", result);
                callBack.onSucess(result);
            }
        });
    }

    /**
     * 创建RequstBady
     *
     * @param hashMap
     * @return
     */
    private RequestBody appendBady(Map<String, Object> hashMap) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        addParams(builder, hashMap);
        return builder.build();
    }

    /**
     * 请求体添加文件
     *
     * @param builder
     * @param params
     */
    private void addParams(MultipartBody.Builder builder, Map<String, Object> params) {
        if (null != params || !params.isEmpty()) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                Object value = entry.getValue();
                // 判断是否是文件
                if (value instanceof File) {
                    File file = (File) value;
                    builder.addFormDataPart(entry.getKey(), file.getAbsolutePath(),
                            RequestBody.create(MediaType.parse(getFileType(file.getAbsolutePath())), file));
                } else if (value instanceof List) {
                    List<File> list = (List<File>) value;
                    for (int j = 0; j < list.size(); j++) {
                        File file = list.get(j);
                        builder.addFormDataPart(entry.getKey() + j, file.getAbsolutePath(),
                                RequestBody.create(MediaType.parse(getFileType(file.getAbsolutePath())), file));
                    }
                } else {
                    builder.addFormDataPart(entry.getKey(), entry.getValue() + "");
                }
            }
        }
    }

    /**
     * 文件内容类型的区别multipart-form-data与application-octet-str (Http 传输格式中文件的类型)
     *
     * @param path
     * @return
     */
    private String getFileType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentType = fileNameMap.getContentTypeFor(path);
        if (null == contentType) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

//    private String getParams(Map<String, Object> params) {
//        StringBuffer str = new StringBuffer();
//        if (null != params || !params.isEmpty()) {
//            for (Map.Entry<String, Object> entry : params.entrySet()) {
//                str.append(entry.getKey() + "=" + entry.getValue() + "&");
//            }
//        }
//        str.deleteCharAt(str.length() - 1);
//        return str.toString();
//    }
}
