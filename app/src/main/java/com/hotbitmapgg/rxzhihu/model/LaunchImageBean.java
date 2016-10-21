package com.hotbitmapgg.rxzhihu.model;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.rxzhihu.R;

/**
 * Created by hcc on 16/4/4.
 */
public class LaunchImageBean {
    private String text;

    private String img;

    public LaunchImageBean(String text, String img) {
        this.text = text;
        this.img = img;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        if (TextUtils.isEmpty(url)) {
            view.setImageDrawable(view.getResources().getDrawable(R.drawable.default_splash));
        } else {
            Glide.with(view.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
        }
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "LuanchImageBean{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
