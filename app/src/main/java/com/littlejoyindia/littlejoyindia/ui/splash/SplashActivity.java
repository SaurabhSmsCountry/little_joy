package com.littlejoyindia.littlejoyindia.ui.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.lifecycle.ViewModelProviders;


import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivitySplashBinding;
import com.littlejoyindia.littlejoyindia.ui.auth.AuthActivity;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }


    @Override
    public void openMainActivity() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent mIntent = DashboardActivity.newIntent(SplashActivity.this);
                startActivity(mIntent);
                finish();
            }
        }, 3000);

    }

    @Override
    public void openAuthActivity() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent mIntent = AuthActivity.newIntent(SplashActivity.this);
                startActivity(mIntent);
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarTransparent();

        mSplashViewModel.setNavigator(this);
        mSplashViewModel.startSeeding();
    }

    private void setStatusBarTransparent() {

        //make translucent statusBar on kitkat devices
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}