package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zzh on 2017/8/3.
 *
 * Toast工具类
 */

public class ToastUtils {
    /**
     * Toast
     *
     * @param context
     * @param text
     */
    public static void show(Context context,CharSequence text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
