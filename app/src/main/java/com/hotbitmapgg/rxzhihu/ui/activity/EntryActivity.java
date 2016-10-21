package com.hotbitmapgg.rxzhihu.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.hotbitmapgg.rxzhihu.R;
import com.hotbitmapgg.rxzhihu.model.LaunchImageBean;

import com.hotbitmapgg.rxzhihu.viewmodel.EntryViewModel;

/**
 * Created by hcc on 16/4/4.
 * <p/>
 * Tips:App启动页面 该页面不要继承AppCompatActivity
 * 会导致界面启动卡顿 加载主题的原因.
 */
public class EntryActivity extends Activity {

    private EntryViewModel mViewModel;


    private static final int ANIMATION_DURATION = 2000;

    private static final float SCALE_END = 1.13F;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            if (msg.what == 0) {
                //animateImage();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.entry_activity);
        SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        String text = sp.getString("Luanch_text", "");
        String image = sp.getString("launcher_image", "");
        LaunchImageBean launcherImage;
        if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(image)) {
            launcherImage = new LaunchImageBean("", "");
        } else {
            launcherImage = new LaunchImageBean(text, image);
        }
        //viewDataBinding.setLaunchImageBean(launcherImage);
        mViewModel = new EntryViewModel(this);
        //viewDataBinding.setViewModel(mViewModel);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        viewDataBinding.ivLuanch.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                animateImage();
//            }
//        }, 400);
    }

    private void animateImage() {

//        ObjectAnimator animatorX = ObjectAnimator.ofFloat(viewDataBinding.ivLuanch, "scaleX", 1f, SCALE_END);
//        ObjectAnimator animatorY = ObjectAnimator.ofFloat(viewDataBinding.ivLuanch, "scaleY", 1f, SCALE_END);
//
//        AnimatorSet set = new AnimatorSet();
//        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
//        set.start();
//
//        set.addListener(new AnimatorListenerAdapter() {
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//                startActivity(new Intent(EntryActivity.this, MainActivity.class));
//                EntryActivity.this.finish();
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mViewModel.destroy();
    }
}
