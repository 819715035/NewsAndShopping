package cn.cndoppler.newsandshopping.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.cndoppler.newsandshopping.bean.BasePager;

/**
 * Created by Administrator on 2017/11/15 0015.
 */

public class ContentFragmentAdapter extends PagerAdapter {

    private List<BasePager> pagers;

    public ContentFragmentAdapter(List<BasePager> pagers) {
        this.pagers = pagers;
    }

    @Override
    public int getCount() {
        return pagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager pager = pagers.get(position);//各个页面的实例
        View rootView = pager.rootView;//各个子页面
        //调用各个页面的initData()
        //pager.initData();//初始化数据
        container.addView(rootView);
        return pager.rootView;
    }
}
