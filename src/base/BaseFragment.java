package base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zzh on 2017/7/24.
 *
 * Fragment基类:
 *
 *  使用方法：
 *      在使用时候在initFragmentView方法中，用View view = View.inflater(R.layout.xxx);来加载当前的布局，
 *      并且把当前的布局view返回，即可.
 */

public abstract class BaseFragment extends Fragment {


    public Activity mActivity;
    // fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    // 处理fragment布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initFragmentView();
    }

    // 依附Activity完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
    }

    /**
     * 初始化Fragement布局
     *
     * @return View，返回当前加载的View，可以通过返回的View，通过findViewById（）找到相应该布局里面的空间，并进行操作
     *
     *
     */
    public abstract View initFragmentView();

    /**
     * 初始化当前Fragment的数据
     */
    public void initData(){

    }
}
