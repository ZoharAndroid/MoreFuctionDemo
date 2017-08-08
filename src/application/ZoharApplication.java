package application;

import android.app.Application;

/**
 * Created by zzh on 2017/7/24.
 *
 * App的Application类
 */

public class ZoharApplication extends Application {

    private  static ZoharApplication application;

    public static ZoharApplication  getApplicationInstance (){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
