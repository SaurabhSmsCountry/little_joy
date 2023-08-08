package com.littlejoyindia.littlejoyindia.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;


import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivityAuthBinding;
import com.littlejoyindia.littlejoyindia.ui.auth.login.LoginFragment;
import com.littlejoyindia.littlejoyindia.ui.auth.signup.SignupFragment;
import com.littlejoyindia.littlejoyindia.ui.auth.verify.VerifyOtpFragment;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;

public class AuthActivity extends BaseActivity<ActivityAuthBinding, AuthViewModel>
        implements AuthNavigator, HasSupportFragmentInjector, LoginFragment.LoginFragmentListener,
        SignupFragment.RegisterFragmentListener,
        VerifyOtpFragment.FragmentListener {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    AuthPagerAdapter mPagerAdapter;

    @Inject
    ViewModelProviderFactory factory;

    private AuthViewModel viewModel;
    private ActivityAuthBinding mBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, AuthActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    public AuthViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        viewModel.setNavigator(this);

        setStatusBarTransparent();

        setUp();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUp() {

        FirebaseAuth.getInstance().signOut();
        mPagerAdapter.setCount(3);

        mBinding.feedViewPager.setAdapter(mPagerAdapter);
        mBinding.feedViewPager.setCurrentItem(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            //System.out.println("@#@");
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentByTag(SearchedJobFragment.TAG);
//        if (fragment == null) {
//            super.onBackPressed();
//        } else {
        // onFragmentDetached(SearchedJobFragment.TAG);
        // }
    }

    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void showCurrentUI(int position) {
        // Navigate Viewpager to the incoming position
        mBinding.feedViewPager.setCurrentItem(position);
    }


    @Override
    public void navigateToVerifyOtp() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        showCurrentUI(2);
    }


    @Override
    public void navigateToLoginUI() {
        showCurrentUI(0);
    }


    @Override
    public void navigateToPersonalProfile() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        showCurrentUI(1);
    }

    @Override
    public void navigateToLogin() {
        navigateToLoginUI();
    }


}
