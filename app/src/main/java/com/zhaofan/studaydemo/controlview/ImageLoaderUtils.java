package com.zhaofan.studaydemo.controlview;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/25
 * description:
 */
public class ImageLoaderUtils {
    private Context context;

    public static ImageLoaderUtils Builder(Context context){
        return new ImageLoaderUtils(context);
    }

    private ImageLoaderUtils(Context context){
        this.context = context;
    }
    public void ImageLoader(ImageView imageView,String url){
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
