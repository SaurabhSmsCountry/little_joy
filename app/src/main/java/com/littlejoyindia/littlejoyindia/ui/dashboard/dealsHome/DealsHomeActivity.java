package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.DealsTopBrands;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;
import com.littlejoyindia.littlejoyindia.databinding.ActivityDealsHomeBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsCity.DealsCityActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.banner.DealsBannerAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.merchants.MerchantsAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.popular.PopularServicesAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.topbrands.TopBrandsAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.merchant.MerchantDetailsActivity;

import java.util.List;

import javax.inject.Inject;


public class DealsHomeActivity extends BaseActivity<ActivityDealsHomeBinding, DelasHomeViewModel>
        implements DealsHomeNavigator, TopBrandsAdapter.TopBrandAdapterListener,
        PopularServicesAdapter.PopularServicesAdapterListener, DealsBannerAdapter.TopBrandAdapterListener,
        MerchantsAdapter.TopBrandAdapterListener {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    TopBrandsAdapter topBrandsAdapter;

    @Inject
    PopularServicesAdapter popularServicesAdapter;

    @Inject
    DealsBannerAdapter dealsBannerAdapter;

    @Inject
    MerchantsAdapter merchantsAdapter;


    private ActivityDealsHomeBinding mBinding;
    private DelasHomeViewModel viewModel;

    String userCity = "", latitude = "0", longitude = "0";


    public static Intent newIntent(Context context) {
        return new Intent(context, DealsHomeActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_deals_home;
    }

    @Override
    public DelasHomeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(DelasHomeViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        init(intent);
    }

    private void init(Intent intent) {
        viewModel.setNavigator(this);
        mBinding = getViewDataBinding();


        Bundle mBundle = intent.getExtras();

        if (mBundle != null) {
            userCity = mBundle.getString("userCity");
            latitude = mBundle.getString("latitude");
            longitude = mBundle.getString("longitude");
        }

        mBinding.toolbarLayout.selectCity.setVisibility(View.VISIBLE);
        mBinding.toolbarLayout.selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = DealsCityActivity.newIntent(DealsHomeActivity.this);
                startActivity(mIntent);
            }
        });

        setUpToolBar();

        viewModel.getTopBrands();

        viewModel.getDealsServicesAll(userCity, latitude, longitude);

        viewModel.getDealsBanners();
    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(userCity);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void setTopBrands(List<DealsTopBrands> data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvTopBrands.setLayoutManager(layoutManager);
        mBinding.rvTopBrands.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvTopBrands.setAdapter(topBrandsAdapter);
        topBrandsAdapter.setListener(this);
        topBrandsAdapter.addItems(data);
    }

    @Override
    public void setPopularServices(List<SalonResponse.DealsHomeResponse.Popular> popular) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvPopularServices.setLayoutManager(layoutManager);
        mBinding.rvPopularServices.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvPopularServices.setAdapter(popularServicesAdapter);
        popularServicesAdapter.setListener(this);
        popularServicesAdapter.addItems(popular);
    }

    @Override
    public void setDealMerchants(List<SalonResponse.DealsHomeResponse.Merchant> merchants) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.rvNearYou.setLayoutManager(layoutManager);
        mBinding.rvNearYou.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvNearYou.setAdapter(merchantsAdapter);
        merchantsAdapter.setListener(this);
        merchantsAdapter.addItems(merchants);
    }

    @Override
    public void setDealBanners(List<SalonResponse.DealsBannerResponse.DealsBanner> data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBanners.setLayoutManager(layoutManager);
        mBinding.rvBanners.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBanners.setAdapter(dealsBannerAdapter);
        dealsBannerAdapter.setListener(this);
        dealsBannerAdapter.addItems(data);
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

    @Override
    public void navigateToMerchantDetails(String merchantId, String merchantName) {
        Intent mIntent = MerchantDetailsActivity.newIntent(this);
        mIntent.putExtra("merchantId", merchantId);
        mIntent.putExtra("merchantName", merchantName);
        mIntent.putExtra("lat", latitude);
        mIntent.putExtra("lng", longitude);
        mIntent.putExtra("userCity", userCity);
        startActivity(mIntent);
    }
}