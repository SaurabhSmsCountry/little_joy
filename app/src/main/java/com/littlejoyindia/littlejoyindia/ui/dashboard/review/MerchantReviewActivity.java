package com.littlejoyindia.littlejoyindia.ui.dashboard.review;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProviders;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.databinding.ActivityMerchantReviewBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

import javax.inject.Inject;


public class MerchantReviewActivity extends BaseActivity<ActivityMerchantReviewBinding, MerchantReviewViewModel>
        implements MerchantReviewNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private ActivityMerchantReviewBinding mBinding;
    private MerchantReviewViewModel viewModel;

    int paymentSelectionType = 1;
    Address address = null;
    String userName =  null;
    String userMobile =  null, dateOfService = null, timeOfService = null , userEmail = null;


    public static Intent newIntent(Context context) {
        return new Intent(context, MerchantReviewActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_merchant_review;
    }

    @Override
    public MerchantReviewViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(MerchantReviewViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();


        Bundle mBundle = getIntent().getExtras();

//        if (mBundle != null){
//            address = (Address) mBundle.getSerializable("address");
//            userName = mBundle.getString("userName");
//            userMobile = mBundle.getString("userMobile");
//            dateOfService =  mBundle.getString("dateOfService");
//            timeOfService =  mBundle.getString("timeOfService");
//            userEmail = mBundle.getString("userEmail");
//        }

        setUpToolBar();


    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Reviews");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }
        return super.onOptionsItemSelected(item);
    }
}