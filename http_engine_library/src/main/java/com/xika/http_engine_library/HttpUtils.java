package com.xika.http_engine_library;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/13
 * Vwesion 1.0
 * Dsscription:  网络请求操作工具类 ;
 */

public class HttpUtils {

    private Context mContext;
    // 网络请求基本地址
    private String mURL;
    // 请求体中的HaspMap对象
    private Map<String, Object> mHashMap;
    // 自定义引擎需要的网络引擎;(默认给okhttp网络引擎)
    private BaseEngine mHttpEngine = new OkHttpEngine();
    // 网络请求get 方式(默认请求方式值)
    private int HTTP_TYPE = HTTP_GET_TYPE;
    // get请求方式值
    private final static int HTTP_GET_TYPE = 0x0001;
    // post请求方式值
    private final static int HTTP_POST_TYPE = 0x0011;


    protected HttpUtils(Context context) {
        this.mContext = context;
        mHashMap = new HashMap<>();
    }

    /**
     * 初始化自定义网络引擎
     *
     * @param context
     * @return
     */
    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    /**
     * 初始化网络请求BaseUrl
     *
     * @param url
     * @return
     */
    public HttpUtils url(String url) {
        this.mURL = url;
        return this;
    }

    /**
     * 逐个添加对象数据
     *
     * @param key
     * @param value
     * @return
     */
    public HttpUtils addParam(String key, Object value) {
        mHashMap.put(key, value);
        return this;
    }

    /**
     * 添加map对象
     *
     * @param hashMap
     * @return
     */
    public HttpUtils addParams(Map<String, Object> hashMap) {
        mHashMap.putAll(hashMap);
        return this;
    }

    /**
     * 设置get网络请求方式
     */
    public HttpUtils get() {
        this.HTTP_TYPE = HTTP_GET_TYPE;
        return this;
    }

    /**
     * 设置post网络请求方式
     */
    public HttpUtils post() {
        this.HTTP_TYPE = HTTP_POST_TYPE;
        return this;
    }

    /**
     * 修改网络请求引擎
     *
     * @param engine
     */
    public HttpUtils exEngine(BaseEngine engine) {
        this.mHttpEngine = engine;
        return this;
    }

    /**
     * 设置网络请求回执接口
     *
     * @param callBack
     */
    public void excute(BaseCallBack callBack) {

        callBack.onPreExcute(mContext, mHashMap);

        if (null == callBack) {
            callBack = BaseCallBack.DEFAULT_CALL_BACK;
        }

        if (HTTP_TYPE == HTTP_GET_TYPE) {
            get(mURL, mHashMap, callBack);
        }

        if (HTTP_TYPE == HTTP_POST_TYPE) {
            post(mURL, mHashMap, callBack);
        }

    }

    // 创建一个空的执行方法
    public void excute() {
        excute(null);
    }


    private void get(String url, Map<String, Object> hashMap, BaseCallBack callBack) {
        mHttpEngine.get(mContext, url, hashMap, callBack);
    }

    private void post(String url, Map<String, Object> hashMap, BaseCallBack callBack) {
        mHttpEngine.post(mContext, url, hashMap, callBack);
    }

    /**
     * 获取地址的内容
     *
     * @param url
     * @param hashMap
     * @return
     */
    public static String getUrl(String url, Map<String, Object> hashMap) {
        StringBuffer s = new StringBuffer(url);
        if (null == hashMap) {
            return url;
        }
        if (!url.contains("?")) {
            s.append("?");
        } else {
            if (!url.endsWith("?")) {
                s.append('&');
            }
        }
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            s.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

}
