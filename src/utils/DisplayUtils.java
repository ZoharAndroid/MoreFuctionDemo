package utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

/**
 *
 * @author zzh
 *
 * @时间 2017/7/6
 * @描述 dp2px,px2dp,px2sp,sp2px的转换
 *      屏幕中的单位转换
 */
public class DisplayUtils {
    /**
     * dp 转 px
     * @param context
     * @param dpValues
     * @return
     */
    public static int dp2px(Context context, float dpValues){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValues*density+0.5f);
    }

    /**
     * px 转 dp
     * @param context
     * @param pxValues
     * @return
     */
    public static int px2dp(Context context, float pxValues){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValues/density+0.5f);
    }

    /**
     * px 转 sp
     * @param context
     * @param pxValues
     * @return
     */
    public static int px2sp(Context context, float pxValues){
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValues/scaledDensity+0.5f);
    }

    /**
     * sp 转 px
     * @param context
     * @param spValues
     * @return
     */
    public static int sp2px(Context context, float spValues){
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValues * scaledDensity +0.5f);
    }


    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    /**
     * 直接获取控件的宽、高
     * @param view
     * @return int[]
     */
    public static int[] getWidgetWH(final View view){
        ViewTreeObserver vto2 = view.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        return new int[]{view.getWidth(),view.getHeight()};
    }

    /**
     * 直接获取控件的宽、高
     * @param view
     * @return int[]
     */
    public static int getViewHeight(final View view){
        ViewTreeObserver vto2 = view.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        return view.getHeight();
    }

    /**
     * 直接获取控件的宽、高
     * @param view
     * @return int[]
     */
    public static int getViewWidth(final View view){
        ViewTreeObserver vto2 = view.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        return view.getWidth();
    }


    /**
     * 获得状态栏的高度
     * 注意：该方法只能在Activity类中使用，在测试模式下失败
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取控件的宽
     * @param view
     * @return
     */
    public static int getWidgetWidth(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);//先度量
        int width = view.getMeasuredWidth();
        return width;
    }
    /**
     * 获取控件的高
     * @param view
     * @return
     */
    public static int getWidgetHeight(View view){
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);//先度量
        int height = view.getMeasuredHeight();
        return height;
    }
    /**
     * 设置控件宽
     * @param view
     * @param width
     */
    public static void setWidgetWidth(View view, int width){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }
    /**
     * 设置控件高
     * @param view
     * @param height
     */
    public static void setWidgetHeight(View view, int height){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }


    //----------------------------------------------

    /**
     * 获取当前屏幕截图，包含状态栏（这个方法没测试通过）
     *
     * @param activity
     * @return Bitmap
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏（这个方法没测试通过）
     *
     * @param activity
     * @return Bitmap
     */
    @SuppressLint("NewApi") public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }
}