package com.littlejoyindia.littlejoyindia.ui.dashboard.basicDetails;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.databinding.ActivityBasicDetailsBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm.ShoppingOrderConfirmationScreen;
import com.littlejoyindia.littlejoyindia.ui.dashboard.timeslot.SelectTimeActivity;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;


public class BasicDetailsActivity extends BaseActivity<ActivityBasicDetailsBinding, BasicDetailsViewModel>
        implements BasicDetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private ActivityBasicDetailsBinding mBinding;
    private BasicDetailsViewModel viewModel;


    Address address = null;
    List<String> data = null;


    public static Intent newIntent(Context context) {
        return new Intent(context, BasicDetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_basic_details;
    }

    @Override
    public BasicDetailsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(BasicDetailsViewModel.class);
        return viewModel;
    }

    int checkoutType = 0;
    int cartId = -1;
    int cartTotal = 0;
    boolean isCodAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();
        setUpToolBar();


        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            address = (Address) mBundle.getSerializable("address");
            checkoutType = mBundle.getInt("checkoutType");
            cartId = mBundle.getInt("cartId");
            cartTotal = mBundle.getInt("cartTotal");
            isCodAvailable = mBundle.getBoolean("isCodAvailable");

            if (address != null){
                if (address.getCompleteAddress() == null) {
                    mBinding.spinnerCity.setVisibility(View.GONE);
                    mBinding.spCity.setVisibility(View.GONE);
                    mBinding.address.setVisibility(View.GONE);
                    mBinding.tvAddress.setVisibility(View.GONE);
                } else {
                    mBinding.tvAddress.setText(address.getCompleteAddress());
                }
            }
            viewModel.getServerCityForSaloon();

            mBinding.address.setOnClickListener(view -> {
                onBackPressed();
            });
        }

        if (checkoutType == 2) {
            mBinding.cardAddress.setVisibility(View.VISIBLE);
            mBinding.llSelectedAddress.setVisibility(View.GONE);
        } else {
            // Set pre filed values of address
            mBinding.etFullAddress.setText(address.getCompleteAddress());
            mBinding.etHouseNo.setText(address.getHouseNo());
            mBinding.etLandmark.setText(address.getLandmark());
            mBinding.etDistrict.setText(address.getCity());
            mBinding.etPincode.setText(address.getPinCode());
            mBinding.etState.setText(address.getState());
        }
    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Basic Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void onClickNext() {


        String userName = getViewDataBinding().etName.getText().toString();
        String userMobile = getViewDataBinding().etMobile.getText().toString();
        String userEmail = getViewDataBinding().etEmail.getText().toString();


        if (CommonUtils.checkForEmptyField(userName)) {
            CommonUtils.toastMe("[ Name ] is required", this);
            return;
        }

        if (CommonUtils.checkForEmptyField(userMobile)) {
            CommonUtils.toastMe("[ Mobile Number ] is required", this);
            return;
        }

        if (!CommonUtils.isValidMobileNumber(userMobile)) {
            CommonUtils.toastMe("[ Mobile Number ] is not valid", this);
            return;
        }

        /*if( CommonUtils.checkForEmptyField(userEmail)){
            CommonUtils.toastMe("[ Email ] is required",this);
            return;
        }

        if( !CommonUtils.isEmailValid(userEmail)){
            CommonUtils.toastMe("[ Email ID ] is not valid",this);
            return;
        }*/

        Intent mIntent;
        if (checkoutType == 2) {
            // Checkout For Online Shopping
            String userHouseNo = mBinding.etHouseNo.getText().toString();
            String fullAddress = mBinding.etFullAddress.getText().toString();
            String userPincode = mBinding.etPincode.getText().toString();
            String userLandmark = mBinding.etLandmark.getText().toString();
            String useretState = mBinding.etState.getText().toString();
            String useretDistrict = mBinding.etDistrict.getText().toString();


            if (CommonUtils.checkForEmptyField(fullAddress)) {
                CommonUtils.toastMe("[ Please add full address details ]", this);
                return;
            }

//            if (CommonUtils.checkForEmptyField(userHouseNo)) {
//                CommonUtils.toastMe("[ House No ] is required", this);
//                return;
//            }

//            if (CommonUtils.checkForEmptyField(userLandmark)) {
//                CommonUtils.toastMe("[ Landmark ] is required", this);
//                return;
//            }

            if (CommonUtils.checkForEmptyField(useretDistrict)) {
                CommonUtils.toastMe("[ City ] is required", this);
                return;
            }

//            if (CommonUtils.checkForEmptyField(useretState)) {
//                CommonUtils.toastMe("[ State ] is required", this);
//                return;
//            }

            if (CommonUtils.checkForEmptyField(userPincode)) {
                CommonUtils.toastMe("[ Pin code ] is required", this);
                return;
            }
            viewModel.checkCodIsAvailable(userPincode);
        } else {
            if (address.getCity() != null && data != null) {
                boolean withinArea = false;
                for (String d : data)
                    if (d.equalsIgnoreCase(mBinding.etDistrict.getText().toString().trim()))
                        withinArea = true;
                if (!withinArea) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this)
                            .setCancelable(false)
                            .setMessage("Sorry our service is not available in your Area/Locality.")
                            .setPositiveButton("OK", (dialogInterface, i) -> {
                                dialogInterface.dismiss();
                                finish();
                            })
                            .setNegativeButton("No", null);
                    AlertDialog alert = builder1.create();
                    alert.show();
                    alert.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
                } else {
                    mIntent = SelectTimeActivity.newIntent(this);
                    mIntent.putExtra("address", address);
                    mIntent.putExtra("userName", userName);
                    mIntent.putExtra("userMobile", userMobile);
                    mIntent.putExtra("userEmail", userEmail);
                    mIntent.putExtra("checkoutType", checkoutType);
                    mIntent.putExtra("cartId", cartId);
                    mIntent.putExtra("isCodAvailable", isCodAvailable);
                    startActivity(mIntent);
                }
            }

        }

    }

    @Override
    public void setCityToUI(List<String> data) {
        this.data = data;
//        ArrayAdapter<CharSequence> spCustomerType = ArrayAdapter.createFromResource(this,
//                R.array.gender, android.R.layout.simple_spinner_item);

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        getViewDataBinding().spCity.setAdapter(aa);
    }

    @Override
    public void updateUI(Address address) {
        Log.d("Okhttp","Clicked");
        String userName = getViewDataBinding().etName.getText().toString();
        String userMobile = getViewDataBinding().etMobile.getText().toString();
        String userEmail = getViewDataBinding().etEmail.getText().toString();

        Intent mIntent = ShoppingOrderConfirmationScreen.newIntent(this);
        mIntent.putExtra("cartTotal", cartTotal);
        mIntent.putExtra("address", address);
        mIntent.putExtra("userName", userName);
        mIntent.putExtra("userMobile", userMobile);
        mIntent.putExtra("userEmail", userEmail);
        mIntent.putExtra("checkoutType", checkoutType);
        mIntent.putExtra("cartId", cartId);
        mIntent.putExtra("isCodAvailable", isCodAvailable);
        startActivity(mIntent);
    }

    @Override
    public void updateCODUI(String message, boolean isAvailable) {
        if (!isAvailable) {
            final Toast toast = Toast.makeText(getApplicationContext(), message + " For the new added address", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 4000);
        }
        viewModel.addAddressOnServer(mBinding.etHouseNo.getText().toString(), mBinding.etPincode.getText().toString(), mBinding.etLandmark.getText().toString(), mBinding.etFullAddress.getText().toString(), mBinding.etState.getText().toString(), mBinding.etDistrict.getText().toString(), isAvailable);
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