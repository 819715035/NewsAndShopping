package cn.cndoppler.newsandshopping.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import cn.cndoppler.newsandshopping.R;
import cn.cndoppler.newsandshopping.comment.ActivityManager;
import cn.cndoppler.newsandshopping.fragment.ContentFragment;
import cn.cndoppler.newsandshopping.fragment.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private final String LEFTMENU_TAG = "leftmenu_tag";
    private final String MAIN_CONTENT_TAG = "contentmenu_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // 添加Activity到堆栈
        ActivityManager.getInstance().add(this);
        setContentView(R.layout.activity_main);
        initSlidingMenu();
        //初始化Fragment
        initFragment();
    }

    private void initFragment() {
        //1.得到FragmentManger
        fragmentManager = getSupportFragmentManager();
        //2.开启事务
        transaction = fragmentManager.beginTransaction();
        //3.替换
        transaction.replace(R.id.fl_main_content,new ContentFragment(), MAIN_CONTENT_TAG);//主页
        transaction.replace(R.id.fl_leftmenu,new LeftMenuFragment(),LEFTMENU_TAG);//左边菜单
        //4.提交
        transaction.commit();
    }

    private void initSlidingMenu() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        //2.设置左侧菜单
        setBehindContentView(R.layout.fragment_left_menu);
        //设置菜单
        SlidingMenu slidingMenu = getSlidingMenu();
       // slidingMenu.setSecondaryMenu(R.layout.fragment_left_menu);//设置右侧菜单
        //6.设置主页占据的宽度
        slidingMenu.setBehindOffset((int) (width*0.625f));
        //4.设置显示的模式：左侧菜单+主页，左侧菜单+主页面+右侧菜单；主页面+右侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT);
        //5.设置滑动模式：滑动边缘，全屏滑动，不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
