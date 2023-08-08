package com.littlejoyindia.littlejoyindia.ui.dashboard.merchant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;
import com.littlejoyindia.littlejoyindia.databinding.ActivityMerchantDetailsBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.appointment.AppointmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import me.relex.circleindicator.CircleIndicator;


public class MerchantDetailsActivity extends BaseActivity<ActivityMerchantDetailsBinding, MerchantViewModel>
        implements MerchantDetailsNavigator, MerchantBannerAdapter.TopBrandAdapterListener {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    MerchantBannerAdapter merchantsAdapter;

    private ActivityMerchantDetailsBinding mBinding;
    private MerchantViewModel viewModel;

    String merchantId = "", merchantName = "", lat = "", lng = "";


    public static Intent newIntent(Context context) {
        return new Intent(context, MerchantDetailsActivity.class);
    }


    final int duration = 10;
    final int pixelsToMove = 30;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            mBinding.topSlider.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_merchant_details;
    }

    @Override
    public MerchantViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(MerchantViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {
            merchantId = mBundle.getString("merchantId");
            merchantName = mBundle.getString("merchantName");
            lat = mBundle.getString("lat");
            lng = mBundle.getString("lng");
        }

        setUpToolBar();

        viewModel.getMerchantData(merchantId, lat, lng);

    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(merchantName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void setDataToUI(List<SalonResponse.MerchantDeatilsByIdResponse.MerchantData> data) {
        SalonResponse.MerchantDeatilsByIdResponse.MerchantData merchantData = data.get(0);
        mBinding.tvName.setText(merchantName);
        mBinding.tvDesc.setText(merchantData.getCategory());
        mBinding.tvTimings.setText(merchantData.getBusinessHour());
        mBinding.tvBrands.setText(merchantData.getTopBrand());
        mBinding.tvDistance.setText(Math.round(merchantData.getDistance()) + " KM");
        mBinding.tvRating.setText("" + merchantData.getMerAvgRating());
        mBinding.rbRating.setRating(merchantData.getMerAvgRating());
        mBinding.tvAddress.setText(merchantData.getMerAddress());
        mBinding.tvService.setText("Female Only /Unisex");


        //Slider slider = mBinding.topSlider;

        ArrayList<String> elephantList = new ArrayList<>(Arrays.asList(merchantData.getMerchantImg().split(",")));

        ArrayList<String> sliderArrayList = new ArrayList<>();

        for (int i = 0; i < elephantList.size(); i++) {
            sliderArrayList.add("https://littlejoyindia.com/merchant_reg/" + elephantList.get(i));
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.topSlider.setLayoutManager(layoutManager);
        mBinding.topSlider.setItemAnimator(new DefaultItemAnimator());
        mBinding.topSlider.setAdapter(merchantsAdapter);
        merchantsAdapter.setListener(this);
        merchantsAdapter.addItems(sliderArrayList);


        //  slider.setAdapter(new TopSliderAdapter(this, sliderArrayList));
        // slider.setInterval(5000);

        CircleIndicator indicator = getViewDataBinding().indicatorTopSlider;
        indicator.createIndicators(sliderArrayList.size(), 0);


        mBinding.topSlider.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem == layoutManager.getItemCount() - 1) {
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.topSlider.setAdapter(null);
                            mBinding.topSlider.setAdapter(merchantsAdapter);
                            mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                        }
                    }, 2000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);


        mBinding.tvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hi check out " + merchantName.trim() + " " + merchantData.getCategory() + " at " + merchantData.getMerAddress());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


    }

    @Override
    public void navigateToAppointment() {
        Intent mIntent = AppointmentActivity.newIntent(this);
        mIntent.putExtra("merchantId", merchantId);
        mIntent.putExtra("merchantName", merchantName);
        mIntent.putExtra("userCity", getIntent().getExtras().getString("userCity"));
        startActivity(mIntent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}