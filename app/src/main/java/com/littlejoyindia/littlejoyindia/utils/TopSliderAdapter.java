package com.littlejoyindia.littlejoyindia.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.littlejoyindia.littlejoyindia.R;

import java.util.ArrayList;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class TopSliderAdapter extends SliderAdapter {

    private  ArrayList<String> sliderArrayList;
    private  Context context;
    public TopSliderAdapter(Context mContext, ArrayList<String> sliderArrayList){
        this.context = mContext;
        this.sliderArrayList = sliderArrayList;
    }
    @Override
    public int getItemCount() {
        return sliderArrayList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {

//        Glide.with(context).load(sliderArrayList.get(position)).into(viewHolder.imageView);
        viewHolder.bindImageSlide(sliderArrayList.get(position));
        switch (position) {
            case 0:
              //  viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            case 1:

            //    viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
               // viewHolder.bindImageSlide("http://masterjigroups.com/assets/img/ctet1.png");
             //   break;
            case 2:
            //    viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //viewHolder.bindImageSlide("http://masterjigroups.com/assets/img/ctet%20social.png");
            //    break;
            case 3:
                //viewHolder.bindImageSlide(R.drawable.dummy_banner);
          //      viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
              //  viewHolder.bindImageSlide("http://masterjigroups.com/assets/img/ctet%20math.png");
                break;
        }
       // viewHolder.bindImageSlide(sliderArrayList.get(position));

    }
}