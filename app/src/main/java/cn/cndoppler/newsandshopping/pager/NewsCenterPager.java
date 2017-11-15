package cn.cndoppler.newsandshopping.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.cndoppler.newsandshopping.bean.BasePager;
import cn.cndoppler.newsandshopping.bean.NewsCenterPagerBean2;
import cn.cndoppler.newsandshopping.utils.Constants;
import cn.cndoppler.newsandshopping.utils.LogUtils;

/**
 * 作者：尚硅谷-杨光福 on 2016/8/15 09:53
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：新闻中心
 */
public class NewsCenterPager extends BasePager {


    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.e("新闻中心数据被初始化了..");
        ib_menu.setVisibility(View.VISIBLE);
        //1.设置标题
        tv_title.setText("新闻中心");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("新闻中心内容");
        //得到缓存数据

        //联网请求数据
        getDataFromNet();
    }

    /**
     * 使用xUtils3联网请求数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(Constants.NEWSCENTER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processData(result);
                //设置适配器
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.e("使用xUtils3联网请求失败==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.e("使用xUtils3-onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtils.e("使用xUtils3-onFinished");
            }
        });
    }

    /**
     * 解析json数据和显示数据
     *
     * @param json
     */
    private void processData(String json) {

        LogUtils.e("得到数据-------------------------==" + json);
//        NewsCenterPagerBean bean = parsedJson(json);
        NewsCenterPagerBean2 bean = parsedJson2(json);
//        String title = bean.getData().get(0).getChildren().get(1).getTitle();


//        LogUtil.e("使用Gson解析json数据成功-title==" + title);
        String title2 = bean.getData().get(0).getChildren().get(1).getTitle();
        LogUtils.e("使用Gson解析json数据成功NewsCenterPagerBean2-title2-------------------------==" + title2);
        /*//给左侧菜单传递数据
        data = bean.getData();

        MainActivity mainActivity = (MainActivity) context;
        //得到左侧菜单
        LeftmenuFragment leftmenuFragment = mainActivity.getLeftmenuFragment();

        //添加详情页面
        detaiBasePagers = new ArrayList<>();
        detaiBasePagers.add(new NewsMenuDetailPager(context,data.get(0)));//新闻详情页面
        detaiBasePagers.add(new TopicMenuDetailPager(context,data.get(0)));//专题详情页面
        detaiBasePagers.add(new PhotosMenuDetailPager(context,data.get(2)));//图组详情页面
        detaiBasePagers.add(new InteracMenuDetailPager(context,data.get(2)));//互动详情页面

        //把数据传递给左侧菜单
        leftmenuFragment.setData(data);*/
    }


    /**
     * 解析json数据：1,使用系统的API解析json；2,使用第三方框架解析json数据，例如Gson,fastjson
     *
     * @param json
     * @return
     */
    private NewsCenterPagerBean2 parsedJson2(String json) {
//        Gson gson = new Gson();
//        NewsCenterPagerBean bean = gson.fromJson(json,NewsCenterPagerBean.class);
        return new Gson().fromJson(json, NewsCenterPagerBean2.class);
    }
}
