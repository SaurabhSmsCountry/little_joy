package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsCity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivityDealsCityBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.DealsHomeActivity;
import com.littlejoyindia.littlejoyindia.utils.AppLocationService;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;
import com.littlejoyindia.littlejoyindia.utils.LocationAddress;

import java.util.List;
import javax.inject.Inject;



public class DealsCityActivity extends BaseActivity<ActivityDealsCityBinding, DealsCityViewModel>
        implements DealsCityNavigator, CityAdapter.CityAdapterListener {

    @Inject
    ViewModelProviderFactory factory;

    private ActivityDealsCityBinding mBinding;
    private DealsCityViewModel viewModel;


    private String locationStringCity = "";
    @Inject
    CityAdapter mAdapter;

    AppLocationService appLocationService;
    private double latitude = 0;
    private double longitude = 0;

    public static Intent newIntent(Context context) {
        return new Intent(context, DealsCityActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_deals_city;
    }

    @Override
    public DealsCityViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(DealsCityViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();
        setUpToolBar();
        viewModel.getDealsCityFromServer();


        appLocationService = new AppLocationService(this);
        Location gpsLocation = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
             latitude = gpsLocation.getLatitude();
             longitude = gpsLocation.getLongitude();
            String result = "Latitude: " + gpsLocation.getLatitude() +
                    " Longitude: " + gpsLocation.getLongitude();
            Log.e("result", "a : "+result);


            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    this, new GeocoderHandler());
        }

    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationStringCity = bundle.getString("address");
                    Log.e("locationAddress", "a : "+locationStringCity);
                    break;
            }

        }
    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Select City");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void setCityToUI(List<String> data) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.rvListData.setLayoutManager(layoutManager);
        mBinding.rvListData.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvListData.setAdapter(mAdapter);
        mAdapter.setListener(this);
        mAdapter.addItems(data);
    }

    @Override
    public void onCurrentLocationSelect() {

        if (latitude == 0){
            CommonUtils.toastMe("Current Location is Not Available at this time. Please check you GPS settings and come again !", this);
        } else {
            navigateToDealsHomeUI(locationStringCity, latitude, longitude);
        }

    }

    private void navigateToDealsHomeUI(String locationStringCity, double latitude, double longitude) {
        Intent mIntent = DealsHomeActivity.newIntent(this);
        mIntent.putExtra("userCity", locationStringCity );
        mIntent.putExtra("latitude", ""+latitude );
        mIntent.putExtra("longitude", ""+longitude );
        startActivity(mIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickCityItem(String city) {
        navigateToDealsHomeUI(city, 0, 0);
    }
}