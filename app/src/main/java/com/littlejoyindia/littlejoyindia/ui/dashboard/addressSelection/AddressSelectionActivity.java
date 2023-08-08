package com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.databinding.ActivityAddressSelectionBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.basicDetails.BasicDetailsActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.map.MapUIActivity;

import java.util.List;

import javax.inject.Inject;


public class AddressSelectionActivity extends BaseActivity<ActivityAddressSelectionBinding, AddressSelectionViewModel>
        implements AddressSelectionNavigator, AddressAdapter.AdapterListener {

    @Inject
    ViewModelProviderFactory factory;

    private ActivityAddressSelectionBinding mBinding;
    private AddressSelectionViewModel viewModel;

    @Inject
    AddressAdapter mAdapter;

    int checkoutType = 0;
    int cartId = -1;
    int cartTotal = 0;
    boolean isCodAvailable = false;

    public static Intent newIntent(Context context) {
        return new Intent(context, AddressSelectionActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_selection;
    }

    @Override
    public AddressSelectionViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(AddressSelectionViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            checkoutType = mBundle.getInt("checkoutType");
            cartId = mBundle.getInt("cartId");
            cartTotal = mBundle.getInt("cartTotal");
            setUpToolBar();
            viewModel.getSavedAddressByUser();
        }
    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            if (checkoutType == 1) {
                getSupportActionBar().setTitle("Service Address");
            } else {
                getSupportActionBar().setTitle("Delivery Address");
            }

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void setDataToUI(List<Address> data, boolean isAvailable) {

        isCodAvailable = isAvailable;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.rvListData.setLayoutManager(layoutManager);
        mBinding.rvListData.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvListData.setAdapter(mAdapter);
        mAdapter.setListener(this);
        mAdapter.addItems(data);
    }

    @Override
    public void clcikOnAddNewAddress() {
        Intent mIntent = MapUIActivity.newIntent(this);
        mapActivityResultLauncher.launch(mIntent);
    }

    @Override
    public void clickOnDeleteAddress() {

    }

    ActivityResultLauncher<Intent> mapActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        String address = data.getExtras().getString("address");
                        String houseNo = data.getExtras().getString("houseNo");
                        String pincode = data.getExtras().getString("pincode");
                        //  String streetNo =  data.getExtras().getString("streetNo");
                        String landmark = data.getExtras().getString("landmark");
                        String latitude = data.getExtras().getString("latitude");
                        String longitude = data.getExtras().getString("longitude");
                        String userState = data.getExtras().getString("userState");
                        String userDistrict = data.getExtras().getString("userDistrict");
                        String city = data.getExtras().getString("city");
                        isCodAvailable = data.getExtras().getBoolean("isAvailable");

                        //  viewModel.addAddressOnServer(houseNo, pincode, streetNo, landmark, userState,  userDistrict);


                        viewModel.addAddressOnServer(houseNo, pincode, landmark, address,userState, userDistrict,isCodAvailable);

                    }
                }
            });

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
    public void setAddressToProcess(Address address) {
        Intent mIntent = BasicDetailsActivity.newIntent(this);
        mIntent.putExtra("address", address);
        mIntent.putExtra("checkoutType", checkoutType);
        mIntent.putExtra("cartId", cartId);
        mIntent.putExtra("cartTotal", cartTotal);
        mIntent.putExtra("isCodAvailable", isCodAvailable);
        startActivity(mIntent);
    }

    @Override
    public void deleteAddressToProcess(List<Address> modelList, int position) {
        //TODO
        Address address = modelList.get(position);
        viewModel.deleteAddressFromServer(ApiEndPoint.Delete_ADDRESS_ON_SERVER + address.getId());
    }
}