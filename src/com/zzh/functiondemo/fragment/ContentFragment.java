package com.zzh.functiondemo.fragment;



import com.zzh.functiondemo.R;

import android.view.View;

public class ContentFragment extends BaseFragment{

	@Override
	public View initFragmentView() {
		View mBaiduContentView = View.inflate(mActivity, R.layout.fragment_baidu_content, null);
		return mBaiduContentView;
	}
	
	
	@Override
	public void initData() {
	}
}
