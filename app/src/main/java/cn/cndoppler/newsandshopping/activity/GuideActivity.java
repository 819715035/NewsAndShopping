package cn.cndoppler.newsandshopping.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cndoppler.newsandshopping.R;
import cn.cndoppler.newsandshopping.comment.BaseActivity;
import cn.cndoppler.newsandshopping.utils.DensityUtil;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_start_main)
    Button btnStartMain;
    @BindView(R.id.ll_point_group)
    LinearLayout llPointGroup;
    @BindView(R.id.iv_red_point)
    ImageView ivRedPoint;
    private List<ImageView> imageViews = new ArrayList<>();
    private RelativeLayout.LayoutParams redParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();
        setListener();
    }

    private void initData() {
        int[] image = {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
        for (int i=0;i<image.length;i++){
            //添加背景图片
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image[i]);
            imageViews.add(imageView);
            //添加灰色的点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(10),DensityUtil.dip2px(10));
            params.leftMargin = DensityUtil.dip2px(5);
            params.rightMargin = DensityUtil.dip2px(5);
            point.setLayoutParams(params);
            llPointGroup.addView(point);
            //红色点的params
            redParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(10),DensityUtil.dip2px(10));
            redParams.leftMargin = DensityUtil.dip2px(5);
            ivRedPoint.setLayoutParams(redParams);
        }
        viewpager.setAdapter(new MyAdapter());
    }

    private void setListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 当页面回调了会回调这个方法
             * @param position 当前滑动页面的位置
             * @param positionOffset 页面滑动的百分比
             * @param positionOffsetPixels 滑动的像数
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                redParams.leftMargin = (int) ((position+positionOffset)*DensityUtil.dip2px(20)+DensityUtil.dip2px(5));
                ivRedPoint.setLayoutParams(redParams);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 判断
         * @param view 当前创建的视图
         * @param object 上面instantiateItem返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        /**
         * 销毁页面
         * @param container ViewPager
         * @param position 要销毁页面的位置
         * @param object 要销毁的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /**
         * 作用，getView
         *
         * @param container ViewPager
         * @param position 要创业页面的位置
         * @return 返回和创建当前页面右关系的值
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            container.addView(iv);
            return iv;
        }
    }
}
