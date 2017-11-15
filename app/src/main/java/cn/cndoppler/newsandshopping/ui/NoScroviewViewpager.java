package cn.cndoppler.newsandshopping.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/11/15 0015.
 */

public class NoScroviewViewpager extends ViewPager {
    public NoScroviewViewpager(Context context) {
        this(context,null);
    }

    public NoScroviewViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
