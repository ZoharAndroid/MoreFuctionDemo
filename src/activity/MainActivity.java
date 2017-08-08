package activity;

import com.zzh.functiondemo.R;

import base.BaseActionBarActivity;
import android.os.Bundle;



public class MainActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBarTitle("主页");
    }
}
