package com.technovate18.sanskar.touristy.adapters;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.technovate18.sanskar.touristy.R;
import com.technovate18.sanskar.touristy.other.DemoData;

/**
 * Created by hadoop on 14/3/18.
 */

public class CarouselPagerAdapter extends PagerAdapter {

    Activity activity;
    LayoutInflater inflater;

    public CarouselPagerAdapter(Activity activity){
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.carousel_item_cover,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.carousel_item_image);
        imageView.setImageDrawable(activity.getResources().getDrawable(DemoData.covers[position]));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return DemoData.covers.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}