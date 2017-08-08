package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by zzh on 2017/8/2.
 *
 * App配置的一些检测工具类
 */

public class ConfigUtils {

    /**
     * 检测网络手机连接网络的状态
     *
     * @param context
     * @return
     */
    public static boolean checkNetworkConnectState(Context context){
        try{
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }

    }

    /**
     * 检测网络是否是wifi
     *
     * @param mContext
     * @return
     */
    public  static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 检测网络是否是移动数据
     *
     * @param mContext
     * @return
     */
    public  static boolean isGprs(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }
}
