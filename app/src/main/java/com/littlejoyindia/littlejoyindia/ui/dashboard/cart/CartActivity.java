package com.littlejoyindia.littlejoyindia.ui.dashboard.cart;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivityCartBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection.AddressSelectionActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI.CartFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm.ShoppingOrderConfirmationScreen;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class CartActivity extends BaseActivity<ActivityCartBinding, CartViewModel> implements CartNavigator,
        HasSupportFragmentInjector, CartFragment.FragmentListener {

    @Inject
    ViewModelProviderFactory factory;

    private ActivityCartBinding mBinding;
    private CartViewModel viewModel;

    private int cartType = 1; // 1 for saloon at home, 2 for deals, 3 for online shopping


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    public static Intent newIntent(Context context) {
        return new Intent(context, CartActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    public CartViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(CartViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();

        cartType = getIntent().getExtras().getInt("cartType", 1);

        setUpToolBar();

        showHomeFragment();


    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cart");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
            // getSupportActionBar().setHomeAsUpIndicator(CommonUtils.changeBackArrowColor(this, Color.rgb(255, 255, 255)));
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Log.e("@TAG", "OnBackPressed from home");
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showHomeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, CartFragment.newInstance(cartType), CartFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }

    @Override
    public void onclickCheckOutSaloon(int cartId, double price) {
        Intent mIntent;
        if (cartType == 1) {
            //salon at home
            mIntent = AddressSelectionActivity.newIntent(this);
            mIntent.putExtra("checkoutType", 1);
            mIntent.putExtra("cartId", cartId);
        } else {
            //deals
            mIntent = ShoppingOrderConfirmationScreen.newIntent(this);
            //mIntent.putExtra("userEmail", userEmail);
            mIntent.putExtra("checkoutType", 0);
            mIntent.putExtra("userName", viewModel.getDataManager().getFullName());
            mIntent.putExtra("userMobile", viewModel.getDataManager().getMobileNumber());
            mIntent.putExtra("cartId", cartId);
            mIntent.putExtra("cartTotal", String.valueOf(price));
            mIntent.putExtra("city", getIntent().getExtras().getString("userCity"));
        }
        startActivity(mIntent);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("editTextValue", "value_here");
        setResult(RESULT_OK, intent);
    }
}