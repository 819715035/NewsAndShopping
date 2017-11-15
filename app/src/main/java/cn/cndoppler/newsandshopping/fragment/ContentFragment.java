package cn.cndoppler.newsandshopping.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.cndoppler.newsandshopping.R;
import cn.cndoppler.newsandshopping.activity.MainActivity;
import cn.cndoppler.newsandshopping.adapter.ContentFragmentAdapter;
import cn.cndoppler.newsandshopping.bean.BasePager;
import cn.cndoppler.newsandshopping.comment.BaseFragment;
import cn.cndoppler.newsandshopping.pager.GovaffairPager;
import cn.cndoppler.newsandshopping.pager.HomePager;
import cn.cndoppler.newsandshopping.pager.NewsCenterPager;
import cn.cndoppler.newsandshopping.pager.SettingPager;
import cn.cndoppler.newsandshopping.pager.SmartServicePager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.viewpager)
    private ViewPager viewpager;
    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    private List<BasePager> basePagers;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_content,null);
        //1.把视图注入到框架中，让ContentFragment.this和View关联起来
        x.view().inject(ContentFragment.this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //初始化五个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));//主页面
        basePagers.add(new NewsCenterPager(context));//新闻中心页面
        basePagers.add(new SmartServicePager(context));//智慧服务页面
        basePagers.add(new GovaffairPager(context));//政要指南页面
        basePagers.add(new SettingPager(context));//设置中心面
        viewpager.setAdapter(new ContentFragmentAdapter(basePagers));
        //设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中首页
        rg_main.check(R.id.rb_home);
        //监听某个页面被选中，初始对应的页面的数据
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        basePagers.get(0).initData();
        //设置模式SlidingMenu不可以滑动
        isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
    }

    private void isEnableSlidingMenu(int touchmodeFullscreen) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmodeFullscreen);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        /**
         *
         * @param group RadioGroup
         * @param checkedId 被选中的RadioButton的id
         */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home://主页radioButton的id
                    //设置成false切换时不显示动画效果
                    viewpager.setCurrentItem(0,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter://新闻中心radioButton的id
                    viewpager.setCurrentItem(1,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice://智慧服务radioButton的id
                    viewpager.setCurrentItem(2,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair://政要指南的RadioButton的id
                    viewpager.setCurrentItem(3,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting://设置中心RadioButton的id
                    viewpager.setCurrentItem(4,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }

        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中的时候回调这个方法
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
//            BasePager basePager = basePagers.get(position);
            //调用被选中的页面的initData方法
            basePagers.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
