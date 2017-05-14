package com.xika.httpengine;

import android.content.Context;

import com.xika.http_engine_library.BaseCallBack;

import java.util.Map;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/14
 * Vwesion 1.0
 * Dsscription:  用来添加固定参数的
 */

public abstract class HttpCallBack implements BaseCallBack {
    @Override
    public void onPreExcute(Context context, Map<String, Object> params) {

        onPreExcute();
    }

    public void onPreExcute() {

    }
}
