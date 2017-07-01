package com.zzh.functiondemo.utils;

import android.content.Context;

/**
 * 
 * @author zzh
 * @时间 2017-6-30  下午11:26:36
 * @描述 dp2px px2dp
 * 		px2sp sp2px
 * 		的转换
 */
public class DisplayUtils {
	/**
	 * dp 转 px
	 * @param context
	 * @param dpValues
	 * @return
	 */
	public static int dp2px(Context context,float dpValues){
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (dpValues*density+0.5f);
	}
	
	/**
	 * px 转 dp
	 * @param context
	 * @param pxValues
	 * @return
	 */
	public static int px2dp(Context context,float pxValues){
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (pxValues/density+0.5f);
	}
	
	/**
	 * px 转 sp
	 * @param context
	 * @param pxValues
	 * @return
	 */
	public static int px2sp(Context context,float pxValues){
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValues/scaledDensity+0.5f);
	}
	
	/**
	 * sp 转 px
	 * @param context
	 * @param spValues
	 * @return
	 */
	public static int sp2px(Context context,float spValues){
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValues * scaledDensity +0.5f);
	}
}
