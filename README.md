# MoreFuctionDemo
发现各种各样的功能，然后在此基础上测试的Demo。自己阅读的书籍上的代码的演练和测试。

#阅读书/参考书《Android群英传》

## 1、自定义控件##
通常情况下，有以下三种方法来实现自定义的控件：

- 对现有控件进行拓展
- 通过组合来实现新的控件
- 重写View来实现全新的控件

通常有以下一些比较重要的回调方法：

- onFinishInflate() : 从xml文件加载组件后的回调
- onMeasure(int widthMeasureSpec, int heightMeasureSpec)：测量 
- onDraw(Canvas canvas) ： 绘制
- onSizeChanged(int w, int h, int oldw, int oldh) ：组件大小改变
- onLayout(boolean changed, int l, int t, int r, int b) ：确定显示的位置
- onTouchEvent(MotionEvent event) ： 触摸事件

### 1.1 对现有控件进行拓展
这是一个非常重要的自定义View方法，它可以在原生控件的基础上进行拓展，增加新的功能，修改显示UI等。一般来说，可以在onDraw（）方法中对原生控件行为进行拓展。

### 1.2 对现有控件进行拓展
创建复合控件可以很好创建出具有**重用功能**的控件集合。这种方式通常需要继承一个合适的ViewGroup，再跟它添加指定的功能控件，从而组合成新的复合控件。通过这种方法创建的控件，一般回个它指定一些可以配置的属性，让控件具有更强的拓展性。

### 例子：


**（1）定义属性**

在res资源目录的values目录下创建一个attrs.xml的属性定义文件，并在该文件中通过如下代码定义相应的属性即可。

	<?xml version="1.0" encoding="utf-8"?>
	<resources>
    <!-- 通过declare-styleable 标签生命了使用自定义属性，并通过name属性来确定引用的名称，最后通过attr
    标签来声明具体的自定义属性 -->
    <declare-styleable name="TopBar">
        <attr name="title" format="string"/>
        <attr name="titleTextSize" format="dimension"/>
        <attr name="titleTextColor" format="color"/>
        <attr name="leftText" format="string"/>
        <attr name="leftTextColor" format="color"/>
        <attr name="leftBackground" format="reference|color"/>
        <attr name="rightText" format="string"/>
        <attr name="rightTextColor" format="color"/>
        <attr name="rightBackground" format="reference|color"/>
    </declare-styleable>
	</resources>


----------


**（2）定义控件**

    /**
    * 
    * @author zzh
    * @时间 2017-6-29  上午10:27:28
    * @描述 自定义ViewGroup，TopBar
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
		//通过这个方法，将在attrs.xml中定义的declare-styleable的所有属性的值存储到TypedArray中
		TypedArray typedArray = context.
				obtainStyledAttributes(attrs, R.styleable.TopBar);
		
		//从TypedArray中取出对应的值来为要设置的属性赋值
		mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
		mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
		mLeftText = typedArray.getString(R.styleable.TopBar_leftText);
		
		mRigtTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
		mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
		mRightText = typedArray.getString(R.styleable.TopBar_rightText);
		
		mTitle = typedArray.getString(R.styleable.TopBar_title);
		mTitleColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
		mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 10);
		
		//获取玩TypedArray的值后，一般要调用recyle方法来避免重新创建的时候的错误
		typedArray.recycle();
		
		mLeftButton = new Button(context);
		mRightButton = new Button(context);
		mTitleView = new TextView(context);
		
		//为创建的组建元素赋值，其中值来源于我们在引用的xml文件中给对应属性的赋值
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
		
		//为组件元素设置相应的布局元素
		LayoutParams mLeftParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
		addView(mLeftButton,mLeftParams);
		
		LayoutParams mRightParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
		addView(mRightButton,mRightParams);
		
		
		LayoutParams mTitleParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
		addView(mTitleView,mTitleParams);
		

		//给按钮设置点击事件
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
	//给LeftButton和RightButton创建点击事件接口
	public interface topbarClickListener{
		void leftClick();
		void rightClick();
	}

	/**
	 * 设置两个按钮的显示和隐藏
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


----------

**（3）在布局文件中引入自定义控件**

>`xmlns:app="http://schemas.android.com/apk/res/com.zzh.functiondemo`
>
>这行代码就是：指定引用的名字控件。


    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res/com.zzh.functiondemo">
    
    <com.zzh.functiondemo.view.TopBar 
    android:id="@+id/topbar"
    android:layout_width="match_parent"
    android:layout_height="40dip"
    app:title="自定义标题栏"
    app:titleTextSize="10sp"
    app:titleTextColor="#123412"
    
    app:leftText="返回"
    app:leftTextColor="@android:color/black"
    app:leftBackground="#00ff00"
    
    app:rightText="更多"
    app:rightTextColor="#ff0000"
    app:rightBackground="#00ff00"
    />
    
    </RelativeLayout>

----------



### 1.3 重写View来实现全新的空间

通常需要继承View类，并重写onDraw()、onMeasure()等方法来实现绘制逻辑，同时可以重写onTouchEvent()等触控事件来实现交互逻辑。

----------

----------

----------

#事件拦截机制分析
2017/6/29 星期四 下午 8:36:41 

Android的View结构是树形结构，也就是说，View可以放在ViewGroup里面，通过不同的组合来实现不同样式。那么问题就是，View放在一个ViewGroup里面，这个ViewGroup又放在另外一个ViewGroup里面，甚至还可能继续嵌套，一层一层的叠加起来。可是，触摸事件就一个，对于同一个事件，子View和父ViewGroup都有可能想要进行处理，这样就很有必要对事件拦截机制进行分析。

1、对于事件拦截机制分析，主要设置三个方法：

- 事件分发
- 事件拦截
- 事件处理

    
    	//事件分发
    	@Override
    	public boolean dispatchTouchEvent(MotionEvent ev) {
    		// TODO Auto-generated method stub
    		return super.dispatchTouchEvent(ev);
    	}
    	//事件拦截:true,拦截，不继续；false，不拦截，继续流程；默认false
    	@Override
    	public boolean onInterceptTouchEvent(MotionEvent ev) {
    		// TODO Auto-generated method stub
    		return super.onInterceptTouchEvent(ev);
    	}
    	//事件处理：true，不进行传递给上一级ViewGroup；false，报告给上一级父控件；默认false
    	@Override
    	public boolean onTouchEvent(MotionEvent event) {
    		// TODO Auto-generated method stub
    		return super.onTouchEvent(event);
    	}

2、运行流程

如果ViewGroupA 包含 ViewGroupB，而ViewGroupB 包含一个子View。如下图所示：
![](http://i.imgur.com/H95ovxD.png)

（1）第一种情况：事件分发，事件拦截和事件处理都是默认false，不进行处理。

	运行的流程大致是：
		ViewGroupA dispatchTouchEvent  --> 
		ViewGroupA onInterceptTouchEvent  --> 
		ViewGroupB dispatchTouchEvent --> 
		ViewGroupB onInterceptTouchEvent  --> 
		View dispatchTouchEvent  --> 
		View onTouchEvent -->
		ViewGroupB onTouchEvent  -->
		ViewGroupA onTouchEvent
	
（2）第二种情况：把ViewGroupA 的onInterceptTouchEvent（）的方法返回值改为true，其他默认；

	运行的流程大致是：
		ViewGroupA dispatchTouchEvent  --> 
		ViewGroupA onInterceptTouchEvent  --> 
		ViewGroupA onTouchEvent
	
	从上述流程可以看到，ViewGroupA直接把事件拦截掉了，不让它所包含的 子ViewGroupB 和 子View 进行事件处理，直接自己（ViewGroupA）的onTouchEvent()事件就处理了。

（3）第三种情况：把ViewGroupB 的onInterceptTouchEvent（）的方法返回值改为true，其他默认；

	运行的流程大致是：
		ViewGroupA dispatchTouchEvent  --> 
		ViewGroupA onInterceptTouchEvent  --> 
		ViewGroupB dispatchTouchEvent --> 
		ViewGroupB onInterceptTouchEvent  -->
		ViewGroupB onTouchEvent  -->
		ViewGroupA onTouchEvent 
	
	从上述流程来看，ViewGroupB把事件拦截下来了，不让 子View 进行事件处理，自己（ViewGroupB）处理掉了，然后把处理报告给 父ViewGroupA 处理了。

（4）第四种情况：把 子View 的onTouchEvent（）直接返回true，其他的默认。

	运行的流程大致是：
		ViewGroupA dispatchTouchEvent  --> 
		ViewGroupA onInterceptTouchEvent  --> 
		ViewGroupB dispatchTouchEvent --> 
		ViewGroupB onInterceptTouchEvent  --> 
		View dispatchTouchEvent  --> 
		View onTouchEvent

	从上述流程可以来看，进行到 子View 处理事件，就不再上报到 父ViewGroupB 和 祖父ViewGroupA处理了。

（5）第五种情况：把 子ViewGroupB 的onTouchEvent（）直接返回true，其他的默认。
		
	ViewGroupA dispatchTouchEvent  --> 
		ViewGroupA onInterceptTouchEvent  --> 
		ViewGroupB dispatchTouchEvent --> 
		ViewGroupB onInterceptTouchEvent  --> 
		View dispatchTouchEvent  --> 
		View onTouchEvent -->
		ViewGroupB onTouchEvent

	从上述流程来看，ViewGroupB 处理完事件后不再上报给 父ViewGroupA 再去处理了。


#屏幕单位转换代码
2017/6/30 星期五 下午 11:38:49 

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