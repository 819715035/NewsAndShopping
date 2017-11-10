package cn.cndoppler.newsandshopping.activity;

import android.os.Bundle;

import cn.cndoppler.newsandshopping.R;
import cn.cndoppler.newsandshopping.comment.BaseActivity;
import cn.cndoppler.newsandshopping.utils.LogUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.e("main");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
