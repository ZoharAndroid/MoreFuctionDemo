package com.zzh.functiondemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzh.functiondemo.R;

/**
 * 
 * @author zzh
 * @ʱ�� 2017-6-29  ����10:27:28
 * @���� �Զ���ViewGroup��TopBar
 */
public class TopBar extends RelativeLayout {
	
	public static final int LEFT_BUTTON =0;
	public static final int Right_BUTTON=1;

	private int mLeftTextColor;
	private Drawable mLeftBackground;
	private String mLeftText;
	private int mRigtTextColor;
	private Drawable mRightBackground;
	private String mRightText;
	private String mTitle;
	private int mTitleColor;
	private float mTitleTextSize;
	private Button mLeftButton;
	private Button mRightButton;
	private TextView mTitleView;
	private topbarClickListener mListenr;
	
	public TopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context, attrs);
		
	}

	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public TopBar(Context context) {
		super(context);
	}
	
	@SuppressLint("NewApi") 
	private void initView(Context context,AttributeSet attrs){
		//ͨ���������������attrs.xml�ж����declare-styleable���������Ե�ֵ�洢��TypedArray��
		TypedArray typedArray = context.
				obtainStyledAttributes(attrs, R.styleable.TopBar);
		
		//��TypedArray��ȡ����Ӧ��ֵ��ΪҪ���õ����Ը�ֵ
		mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
		mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
		mLeftText = typedArray.getString(R.styleable.TopBar_leftText);
		
		mRigtTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
		mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
		mRightText = typedArray.getString(R.styleable.TopBar_rightText);
		
		mTitle = typedArray.getString(R.styleable.TopBar_title);
		mTitleColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
		mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 10);
		
		//��ȡ��TypedArray��ֵ��һ��Ҫ����recyle�������������´�����ʱ��Ĵ���
		typedArray.recycle();
		
		mLeftButton = new Button(context);
		mRightButton = new Button(context);
		mTitleView = new TextView(context);
		
		//Ϊ�������齨Ԫ�ظ�ֵ������ֵ��Դ�����������õ�xml�ļ��и���Ӧ���Եĸ�ֵ
		mLeftButton.setTextColor(mLeftTextColor);
		mLeftButton.setText(mLeftText);
		mLeftButton.setBackground(mLeftBackground);
		
		mRightButton.setTextColor(mRigtTextColor);
		mRightButton.setText(mRightText);
		mRightButton.setBackground(mRightBackground);
		
		mTitleView.setText(mTitle);
		mTitleView.setTextSize(mTitleTextSize);
		mTitleView.setTextColor(mTitleColor);
		mTitleView.setGravity(Gravity.CENTER);
		
		//Ϊ���Ԫ��������Ӧ�Ĳ���Ԫ��
		LayoutParams mLeftParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
		addView(mLeftButton,mLeftParams);
		
		LayoutParams mRightParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
		addView(mRightButton,mRightParams);
		
		
		LayoutParams mTitleParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
		addView(mTitleView,mTitleParams);
		

		//����ť���õ���¼�
		mRightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListenr.rightClick();
			}
		});
		mLeftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListenr.leftClick();
			}
		});
	}
	
	
	public void setOnTopbarClickListener(topbarClickListener mListenr){
		this.mListenr = mListenr;
		
	}
	//��LeftButton��RightButton��������¼��ӿ�
	public interface topbarClickListener{
		void leftClick();
		void rightClick();
	}

	/**
	 * ����������ť����ʾ������
	 * @param id
	 * @param flag
	 */
	public void setButtonVisable(int id,boolean flag){
		if (flag) {
			if (id == LEFT_BUTTON) {
				mLeftButton.setVisibility(View.VISIBLE);
			}else if (id == Right_BUTTON){
				mRightButton.setVisibility(View.VISIBLE);
			}
		}else{
			if (id == LEFT_BUTTON) {
				mLeftButton.setVisibility(View.GONE);
			}else{
				mRightButton.setVisibility(View.GONE);
			}
		}
	}
	

}
