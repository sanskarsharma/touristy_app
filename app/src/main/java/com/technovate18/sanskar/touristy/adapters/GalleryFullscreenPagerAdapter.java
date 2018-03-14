package com.technovate18.sanskar.touristy.adapters;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.technovate18.sanskar.touristy.R;
import com.technovate18.sanskar.touristy.models.ImageModel;

import java.util.ArrayList;

/**
 * Created by hadoop on 14/3/18.
 */

public class GalleryFullscreenPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    Activity activity;
    private ArrayList<ImageModel> images;

    public GalleryFullscreenPagerAdapter(Activity activity, ArrayList<ImageModel> images) {
        this.activity = activity;
        this.images = images;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

        ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);

        ImageModel image = images.get(position);

        Glide.with(activity).load(image.getLarge())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewPreview);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
