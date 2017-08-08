package interfaces;

import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by zzh on 2017/8/8.
 */

public interface OnSetProgressBarLisenter {

    /**
     * 设置WebView网址
     *
     * @param Url
     */
    void setProgressBarUrl(WebView webView, String Url);

    /**
     * 设置WebView中的标题
     *
     * @param title
     */
    void setActionBarTitle(TextView view, CharSequence title);
}
