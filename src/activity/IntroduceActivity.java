package activity;

import com.zzh.functiondemo.R;

import utils.DisplayUtils;
import utils.SharedUtils;
import utils.ToastUtils;
import adapter.IntroducePagerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import base.BaseActivity;
import config.Constants;



/**
 *
 */
public class IntroduceActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager mVpIntroduce;//ViewPager
    private LinearLayout mLlPointContainer;//索引点容器
    private View mPointSelectView;//选中的点
    private TextView mTvLoginRegister;//登录注册
    private TextView  mTvExperice;//立即体验
    private int mPagerCount;//ViewPager的页数
    private int mPointDistance;//默认索引之间的两个点的距离


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initCurrentView();
        initCurrentData();
        initCurrentEvent();
    }


    private void initCurrentView() {
        mVpIntroduce = (ViewPager)findViewById(R.id.vp_introduce);
        mLlPointContainer = (LinearLayout)findViewById(R.id.ll_point_container);
        mPointSelectView = (View)findViewById(R.id.point_select);
        mTvLoginRegister = (TextView)findViewById(R.id.tv_login_register);
        mTvExperice = (TextView)findViewById(R.id.tv_experience);

        mTvExperice.setOnClickListener(this);
        mTvLoginRegister.setOnClickListener(this);

    }

    private void initCurrentData() {
        IntroducePagerAdapter adapter = new IntroducePagerAdapter(this);
        //获取ViewPager的页数
        mPagerCount = adapter.getCount();

        // 创建背景索引点
        for (int i = 0; i < mPagerCount; i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.shape_introduce_point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtils.dp2px(this, 10), DisplayUtils.dp2px(this, 10));
            if (i > 0) {
                params.leftMargin = DisplayUtils.dp2px(this, 10);
            }
            view.setLayoutParams(params);
            mLlPointContainer.addView(view);
        }
        mVpIntroduce.setAdapter(adapter);

    }

    private void initCurrentEvent() {
        //获取全局的layout树，来获取默认索引两个点之间的距离
        mLlPointContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
               mPointDistance = mLlPointContainer.getChildAt(1).getLeft() - mLlPointContainer.getChildAt(0).getLeft();
                //结束layout监听
                mLlPointContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        //动态修改小红点位置
        mVpIntroduce.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //ViewPager滑动
                //1、获取小红点的参数
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mPointSelectView.getLayoutParams();
                //2、动态修改小红点的距离
                params.leftMargin = (int) (position * mPointDistance + mPointDistance * positionOffset );
                mPointSelectView.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_experience:
               startMainActivity();
                break;
            case R.id.tv_login_register:
                startLoginAndRegister();
                   break;

        }
    }

    private void startMainActivity(){
        startActivity(new Intent(IntroduceActivity.this,MainActivity.class));
        finish();
        SharedUtils.setSpBoolean(IntroduceActivity.this, Constants.IS_FIRST_START_FLAT,false);
    }

    private void startLoginAndRegister(){
        ToastUtils.show(IntroduceActivity.this,"登录/注册");
    }
}
