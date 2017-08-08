package base;

import com.zzh.functiondemo.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * Created by zzh on 2017/8/1.
 * 标题栏基类Activity：这个文件，这个文件相当于Actionbar的初始化和设置；
 *
 * 1、用法：
 *    注意在使用这个基类的时候，要在继承这个类的布局文件添加:<include layout="@layout/layout_action_bar"/>,
 *    然后在Activity中onCreate()方法中：e.g.initActionBarTitle("主页");
 *
 * 2、已经继承了点击事件，重写onClick方法即可；
 */
public class BaseActionBarActivity extends BaseActivity implements View.OnClickListener{

    protected TextView mTvActionBarTitle;//标题
    protected ImageView mIvActionBarBack;//返回
    protected RelativeLayout mRlActionBarRoot;//Actionbar根布局

    /**
     * 初始化ActionBar中布局文件
     * @param title
     */
    protected void initActionBarTitle(CharSequence title){
        initView();
        mTvActionBarTitle.setText(title);
        mIvActionBarBack.setOnClickListener(this);
    }

    /**
     * 设置标题栏
     *
     * @param title
     */
    protected void setActionBarTitle(CharSequence title){
        mTvActionBarTitle.setText(title);
    }

    /**
     * 初始布局控件
     */
    protected  void initView() {
        mTvActionBarTitle = (TextView) findViewById(R.id.tv_action_bar_title);
        mIvActionBarBack = (ImageView) findViewById(R.id.iv_action_bar_back);
        mRlActionBarRoot =(RelativeLayout) findViewById(R.id.rl_action_bar_root);
    }


    /**
     * 设置ActionBar的背景颜色：默认为红色：e94140
     * @param color
     */
    protected void setActionBarBackground(int color){
        mRlActionBarRoot.setBackgroundColor(color);
    }

    /**
     * ActionBar的点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_action_bar_back:
                finish();
                break;
        }
    }




}
