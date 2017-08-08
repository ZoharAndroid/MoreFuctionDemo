package base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * Created by zzh on 2017/7/24.
 *
 * Activity基类
 */

public class BaseActivity extends FragmentActivity {

    protected Activity context; //当前的Context对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        // 去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }



    /**
     * 获取当前的Context对象
     *
     * @return context对象
     */
    public Activity getContext(){
        return context;
    }

}
