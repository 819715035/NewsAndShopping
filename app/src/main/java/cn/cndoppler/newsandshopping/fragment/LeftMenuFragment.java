package cn.cndoppler.newsandshopping.fragment;

import android.view.View;

import cn.cndoppler.newsandshopping.R;
import cn.cndoppler.newsandshopping.comment.BaseFragment;

public class LeftMenuFragment extends BaseFragment {


    private View view;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_left_menu,null);
        return view;
    }

    @Override
    public void initData() {

    }
}
