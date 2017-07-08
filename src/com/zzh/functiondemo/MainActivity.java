package com.zzh.functiondemo;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.zzh.functiondemo.view.TopBar;
import com.zzh.functiondemo.view.TopBar.topbarClickListener;

/**
 * 
 * @author zzh
 * @时间 2017-7-1  下午3:48:04
 * @描述 主页面
 */
public class MainActivity extends Activity {

    private TopBar mTopbar;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        processView();
    }

	/**
	 * 处理图像和画笔
	 */
	private void processView() {
		
	}

	private void initData() {
		 mTopbar.setOnTopbarClickListener(new topbarClickListener() {
				
				@Override
				public void rightClick() {
					Toast.makeText(MainActivity.this, "����", 0).show();
				}
				
				@Override
				public void leftClick() {
					Toast.makeText(MainActivity.this, "����", 0).show();
				}
			});		
	}

	private void initView() {
		 mTopbar = (TopBar) findViewById(R.id.topbar);		
	}
    
}
