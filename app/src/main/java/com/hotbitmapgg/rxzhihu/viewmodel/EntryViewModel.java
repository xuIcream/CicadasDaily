package com.hotbitmapgg.rxzhihu.viewmodel;


import android.content.Context;
import android.content.SharedPreferences;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.rxzhihu.model.LaunchImageBean;
import com.hotbitmapgg.rxzhihu.network.RetrofitHelper;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class EntryViewModel implements ViewModel {

    private Context context;
    private static final String RESOLUTION = "1080*1776";


    public EntryViewModel(Context context) {
        this.context = context;
        getNextImage();
    }

    @Override
    public void destroy() {
    }

    private void getNextImage() {

        RetrofitHelper.builder().getLuanchImage(RESOLUTION)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<LaunchImageBean>() {
                    @Override
                    public void call(LaunchImageBean launchImageBean) {
                        if (launchImageBean != null) {
                            String img = launchImageBean.getImg();
                            Glide.with(context).load(img).diskCacheStrategy(DiskCacheStrategy.ALL);
                            SharedPreferences sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString("Luanch_text", launchImageBean.getText());
                            edit.putString("launcher_image", launchImageBean.getImg());
                            edit.commit();
                        }
                    }
                });
    }

}
