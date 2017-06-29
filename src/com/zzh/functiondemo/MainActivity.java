package com.zzh.functiondemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.zzh.functiondemo.view.TopBar;
import com.zzh.functiondemo.view.TopBar.topbarClickListener;

/**
 * 
 * @author zzh
 * @时间 2017-6-29  上午11:14:51
 * @描述 TODO
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
       
    }

	private void initData() {
		 mTopbar.setOnTopbarClickListener(new topbarClickListener() {
				
				@Override
				public void rightClick() {
					Toast.makeText(MainActivity.this, "返回", 0).show();
				}
				
				@Override
				public void leftClick() {
					Toast.makeText(MainActivity.this, "更多", 0).show();
				}
			});		
	}

	private void initView() {
		 mTopbar = (TopBar) findViewById(R.id.topbar);		
	}
    
}
