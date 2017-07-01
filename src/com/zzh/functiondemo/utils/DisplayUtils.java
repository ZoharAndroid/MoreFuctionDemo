package com.zzh.functiondemo.utils;

import android.content.Context;

/**
 * 
 * @author zzh
 * @ʱ�� 2017-6-30  ����11:26:36
 * @���� dp2px px2dp
 * 		px2sp sp2px
 * 		��ת��
 */
public class DisplayUtils {
	/**
	 * dp ת px
	 * @param context
	 * @param dpValues
	 * @return
	 */
	public static int dp2px(Context context,float dpValues){
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (dpValues*density+0.5f);
	}
	
	/**
	 * px ת dp
	 * @param context
	 * @param pxValues
	 * @return
	 */
	public static int px2dp(Context context,float pxValues){
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (pxValues/density+0.5f);
	}
	
	/**
	 * px ת sp
	 * @param context
	 * @param pxValues
	 * @return
	 */
	public static int px2sp(Context context,float pxValues){
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValues/scaledDensity+0.5f);
	}
	
	/**
	 * sp ת px
	 * @param context
	 * @param spValues
	 * @return
	 */
	public static int sp2px(Context context,float spValues){
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValues * scaledDensity +0.5f);
	}
}
