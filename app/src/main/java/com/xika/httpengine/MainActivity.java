package com.xika.httpengine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xika.http_engine_library.BaseCallBack;
import com.xika.http_engine_library.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button test = (Button) findViewById(R.id.http_test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {
        String url = "http://61.164.47.179:8888/jiagc_test/sys/proc/jiagcProxy.jsp";
        Map<String, Object> params = new HashMap<>();
        params.put("procName", "PROC_APP_USER_LOGIN");
        params.put("in_yonghCode", "admin");
        params.put("in_yonghPwd", "1234");
        HttpUtils.with(mContext)
                .url(url)
                .get()
                .addParams(params)
                .excute(new HttpCallBack() {
                    @Override
                    public void onError(Exception e) {

                    }

                    @Override
                    public void onSucess(String result) {
                        Log.i("TGA", result);
                    }
                });
    }
}
