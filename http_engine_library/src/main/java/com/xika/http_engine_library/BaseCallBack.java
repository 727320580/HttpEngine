package com.xika.http_engine_library;

import android.content.Context;

import java.util.Map;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/13
 * Vwesion 1.0
 * Dsscription:  执行网络请求完全之后的 接口回调
 */

public interface BaseCallBack {

    /**
     * 固定参数的添加
     */
    void onPreExcute(Context context, Map<String, Object> params);

    /**
     * 执行错误的返回接口
     *
     * @param e
     */
    void onError(Exception e);


    /**
     * 执行成功的返回接口
     *
     * @param result
     */
    void onSucess(String result);

    /**
     * 执行空的接口回调
     */
    BaseCallBack DEFAULT_CALL_BACK = new BaseCallBack() {
        @Override
        public void onPreExcute(Context context, Map<String, Object> params) {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSucess(String result) {

        }
    };
}
