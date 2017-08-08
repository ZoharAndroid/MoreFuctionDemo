package adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.util.ArrayList;

import com.zzh.functiondemo.R;


/**
 * Created by zzh on 2017/8/8.
 * <p>
 * IntroduceActivity的ViewPager适配器
 */

public class IntroducePagerAdapter extends PagerAdapter {

    private int[] imageIds = new int[]{R.mipmap.introduce_1, R.mipmap.introduce_2, R.mipmap.introduce_3};
    private ArrayList<ImageView> mImageViewList = new ArrayList<ImageView>();

    public IntroducePagerAdapter(Context context) {

        //生成图片
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setImageResource(imageIds[i]);
            mImageViewList.add(imageView);
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageViewList.get(position));
        return mImageViewList.get(position);
    }
}
