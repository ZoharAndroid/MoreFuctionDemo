package base;

import com.zzh.functiondemo.R;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by zzh on 2017/8/8.
 * <p>
 * WebView网络加载基类:
 *  使用方法：
 *      （1）添加initBaseWebView到继承的子类中
 *      （2）,然后在子类中 // 加载地址
 *                       ProgressBarImpl progressBarImpl = new ProgressBarImpl();
 *                       progressBarImpl.setActionBarTitle(mTvActionBarTitle, "标题栏");
 *                      progressBarImpl.setProgressBarUrl(mWebView, "https://www.baidu.com");
 */

public class BaseWebViewActivity extends BaseActivity {
    public TextView mTvActionBarTitle;//标题
    public ImageView mIvActionBarBack;//返回
    public ProgressBar mPbProgress;//进度条
    public WebView mWebView;

    /**
     * 初始化WebView基本布局
     */
    protected void initBaseWebView() {
        //初始化布局
        initWebView();
        initWebData();
        initWebEvent();
    }

    /**
     * 初始布局控件
     */
    private void initWebView() {
        mTvActionBarTitle = (TextView) findViewById(R.id.tv_action_bar_title);
        mIvActionBarBack = (ImageView) findViewById(R.id.iv_action_bar_back);
        mPbProgress = (ProgressBar) findViewById(R.id.pb_actiion_bar_progress);
        mWebView = (WebView) findViewById(R.id.wv_content);
    }

    /**
     * 初始本地数据
     */
    private void initWebData() {
        //WebView自己加载本地连接
        mWebView.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          view.loadUrl(url);
                                          return true;

                                      }
                                  }
        );
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 加载进度
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    //加载进度为100，就隐藏
                    mPbProgress.setVisibility(View.GONE);
                } else {
                    //显示加载进度
                    mPbProgress.setVisibility(View.VISIBLE);
                    mPbProgress.setProgress(newProgress);
                }
            }
        });


    }

    private void initWebEvent() {
        mIvActionBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * 设置返回键动作（防止按返回键直接)
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                mWebView.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                finish();
            }


        }
        return super.onKeyDown(keyCode, event);
    }

}
