package com.xika.http_engine_library;

import android.content.Context;

import java.util.Map;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/13
 * Vwesion 1.0
 * Dsscription:  是要实现的网络接口 (真正的网络操作接口类)
 */

public interface BaseEngine {

    /**
     * 执行post请求方法体
     *
     * @param context
     * @param url
     * @param hashMap
     */
    void post(Context context, String url, Map<String, Object> hashMap, BaseCallBack callBack);


    /**
     * 执行get请求的方法体
     *
     * @param context
     * @param url
     * @param hashMap
     */
    void get(Context context, String url, Map<String, Object> hashMap, BaseCallBack callBack);

}
