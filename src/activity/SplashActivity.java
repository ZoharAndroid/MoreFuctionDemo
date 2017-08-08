package activity;

import utils.ConfigUtils;
import utils.SharedUtils;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zzh.functiondemo.R;

import config.Constants;

/**
 * Created by zzh on 2017/8/2.
 * <p>
 * 引导页:主要功能：
 *  （1）显示Splash界面；
 *  （2）检测当前网络状态;
 *   (3) 检测引导页
 */

public class SplashActivity extends FragmentActivity {

    private static final int HANDLE_MESSAGE = 1;//HANDLE消息标志

    private boolean isFristStart;//第一次启动标志位


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isFristStart){
                //跳转到引导页面
                startIntroduceActivity();
                finish();
            }else {
                //跳转到主页
                startMainActivity();
                finish();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // 启动页
        handler.sendEmptyMessageDelayed(HANDLE_MESSAGE,2000);

        // 获取第一次启动缓存
        isFristStart = SharedUtils.getSpBoolean(this, Constants.IS_FIRST_START_FLAT,true);

        if (ConfigUtils.checkNetworkConnectState(this)){
           // ToastUtils.show(this,"网络连接正常");
        }

        if (ConfigUtils.isGprs(this)){
            Toast.makeText(this, "移动数据", Toast.LENGTH_SHORT).show();
        }

        if (ConfigUtils.isWifi(this)){
            Toast.makeText(this, "WiFi", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 跳转到主页
     */
    private void startMainActivity(){
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到引导页
     */
    private void startIntroduceActivity(){
        Intent intent = new Intent(SplashActivity.this,IntroduceActivity.class);
        startActivity(intent);
    }
}
