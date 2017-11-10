package cn.cndoppler.newsandshopping.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cndoppler.newsandshopping.R;
import cn.cndoppler.newsandshopping.comment.ActivityManager;
import cn.cndoppler.newsandshopping.comment.BaseActivity;
import cn.cndoppler.newsandshopping.comment.BaseApplication;
import cn.cndoppler.newsandshopping.utils.LogUtils;
import cn.cndoppler.newsandshopping.utils.SPUtils;

import static android.R.attr.duration;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.rl_splahs_root)
    RelativeLayout rlSplahsRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //渐变动画，缩放动画，旋转动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        //alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        //渐变动画，缩放动画，旋转动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
       // scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        //渐变动画，缩放动画，旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        //添加三个动画没有先后顺序,便于同时播放动画
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setDuration(2000);
        rlSplahsRoot.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (SPUtils.getValue("toMian",false)){
                    openActivity(MainActivity.class);
                }else{
                    openActivity(GuideActivity.class);
                }
                //关闭此页面
                removeCurrentActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
