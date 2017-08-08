package utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zzh on 2017/8/8.
 *
 * SharedPreference工具类
 */

public class SharedUtils {

    private final static String SP_NAME = "zzh_sp";

    private static SharedPreferences getSharedPreference(Context context){
        return context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }

    public static void setSpBoolean(Context context,String key,Boolean value){
        getSharedPreference(context).edit().putBoolean(key,value).commit();
    }

    public static void setSpInt(Context context,String key,int value){
        getSharedPreference(context).edit().putInt(key,value).commit();
    }

    public static void setSpString(Context context,String key,String value){
        getSharedPreference(context).edit().putString(key,value).commit();
    }


    public static void setSpFloat(Context context,String key,Float value){
        getSharedPreference(context).edit().putFloat(key,value).commit();
    }

    public static void setSpLong(Context context,String key,Long value){
        getSharedPreference(context).edit().putLong(key,value).commit();
    }

    public static boolean getSpBoolean(Context context,String key,Boolean defualtvalue){
        return getSharedPreference(context).getBoolean(key,defualtvalue);
    }

    public static int getSpInt(Context context,String key,int defualtvalue){
        return getSharedPreference(context).getInt(key,defualtvalue);
    }

    public static String getSpString(Context context,String key,String defualtvalue){
        return getSharedPreference(context).getString(key,defualtvalue);
    }

    public static Long getSpLong(Context context,String key,Long defualtvalue){
        return getSharedPreference(context).getLong(key,defualtvalue);
    }

    public static Float getSpFloat(Context context,String key,Float defualtvalue){
        return getSharedPreference(context).getFloat(key,defualtvalue);
    }

}
