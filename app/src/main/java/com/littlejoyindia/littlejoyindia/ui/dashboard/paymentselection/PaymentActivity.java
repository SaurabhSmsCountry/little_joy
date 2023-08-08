package com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MenuItem;
import android.view.View;

import androidx.databinding.Observable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.local.prefs.PreferencesHelper;
import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.databinding.ActivityPaymentBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingPaymentWebview.OnlineShoppingPaymentWebviewActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.OrderDetailsScreenActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.ShoppingOrderListScreenActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealOrderCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealPaymentSelectionResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingPaymentSelectionResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.PaymentCheckoutResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonOrderCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonPaymentSelectionResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.ThankYouCheckout;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

import javax.inject.Inject;


public class PaymentActivity extends BaseActivity<ActivityPaymentBinding, PaymentViewModel>
        implements PaymentNavigator {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    PreferencesHelper preferenceManager;

    private ActivityPaymentBinding mBinding;
    private PaymentViewModel viewModel;

    int paymentSelectionType = 1;
    Address address = null;
    String userName = null;
    String userMobile = null, dateOfService = null, timeOfService = null, userEmail = null, city = null;

    int checkoutType = 0;
    int cartId = -1;
    int totalPaid = 0;
    String coupon = "";
    boolean isCodAvailable = false;


    public static Intent newIntent(Context context) {
        return new Intent(context, PaymentActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public PaymentViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(PaymentViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {
            address = (Address) mBundle.getSerializable("address");

            userName = mBundle.getString("userName");
            userMobile = mBundle.getString("userMobile");
            dateOfService = mBundle.getString("dateOfService");
            timeOfService = mBundle.getString("timeOfService");
            userEmail = mBundle.getString("userEmail");
            city = mBundle.getString("city");

            checkoutType = mBundle.getInt("checkoutType");
            cartId = mBundle.getInt("cartId");
            totalPaid = mBundle.getInt("cartTotal");
            coupon = mBundle.getString("coupon");
            isCodAvailable = mBundle.getBoolean("isCodAvailable");
        }

        setUpToolBar();
        changeUISelection();

//        mBinding.llCod.setVisibility(isCodAvailable ? View.VISIBLE : View.GONE);

        viewModel.getWaletAmount();
        if (address != null)
            viewModel.checkCodIsAvailable(address.getPinCode());

        viewModel.getIsLoading().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {

            }
        });

    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Select Payment Method");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {
        CommonUtils.toastMe(message, this);
    }

    @Override
    public void payWalletClick() {
        paymentSelectionType = 1;
        changeUISelection();
    }

    @Override
    public void payOnlineClick() {
        /*CommonUtils.toastMe("This method is not available for now 1", this);
        return;*/
        paymentSelectionType = 2;
        changeUISelection();
    }

    @Override
    public void payHomeClick() {

        if (isCodAvailable) {
            paymentSelectionType = 3;
            changeUISelection();
        }else{
            showToastMessage("COD is not available. Please pay online.");
        }
    }

    private void changeUISelection() {

        if (paymentSelectionType == 1) {
            mBinding.imgPayWallet.setImageResource(R.drawable.radio_btn);
            mBinding.imgPayOnline.setImageResource(R.drawable.unradio_btn);
            mBinding.imgPayAtHome.setImageResource(R.drawable.unradio_btn);
        } else if (paymentSelectionType == 2) {
            mBinding.imgPayWallet.setImageResource(R.drawable.unradio_btn);
            mBinding.imgPayOnline.setImageResource(R.drawable.radio_btn);
            mBinding.imgPayAtHome.setImageResource(R.drawable.unradio_btn);
        } else {
            mBinding.imgPayWallet.setImageResource(R.drawable.unradio_btn);
            mBinding.imgPayOnline.setImageResource(R.drawable.unradio_btn);
            mBinding.imgPayAtHome.setImageResource(R.drawable.radio_btn);
        }

    }

    @Override
    public void clickOnSubmit() {
        if (checkoutType == 2) {
            // For Online Shopping
            OnlineShoppingPaymentSelection request = new OnlineShoppingPaymentSelection();
            request.setCartId(cartId);
            request.setCustomerId(Integer.parseInt(preferenceManager.getUid()));
            request.setName(userName);
            request.setMobile(userMobile);
            request.setAddressId(address.getId());
            request.setCity(address.getCity());
            request.setModePay(getModeOfPay());
            request.setCoupon(coupon);
            viewModel.submitPaymentMethod(request);
        } else if (checkoutType == 0) {
            DealPaymentSelection request = new DealPaymentSelection();
            request.setCartId(cartId);
            request.setCustomerId(Integer.parseInt(preferenceManager.getUid()));
            request.setName(userName);
            request.setMobile(userMobile);
            request.setEmail(userEmail);
            request.setCity(city);
            request.setModePay(getModeOfPay());
            request.setTotalPaid(String.valueOf(totalPaid));
            request.setTotal(String.valueOf(totalPaid));
            viewModel.submitDealPaymentMethod(request);
        } else {
            SalonPaymentSelection request = new SalonPaymentSelection();
            request.setCartId(cartId);
            request.setCustomerId(Integer.parseInt(preferenceManager.getUid()));
            request.setName(userName);
            request.setMobile(userMobile);
            request.setAddressId(address.getId());
            request.setCity(address.getCity());
            request.setModePay(getModeOfPay());
            request.setServiceDate(dateOfService);
            request.setTime(timeOfService);
            request.setCoupon("");
            viewModel.submitSalonPaymentMethod(request);
        }
    }

    private String getModeOfPay() {
        switch (paymentSelectionType) {
            case 1:
                return "Wallet";
            case 2:
                return "Pay Online";
            case 3:
                return "Pay at Home";
            default:
                return "";
        }
    }

    @Override
    public void submitPaymentMethodForOnlineShopping(OnlineShoppingPaymentSelectionResponse response) {

        if (paymentSelectionType == 3 || paymentSelectionType == 1) {

            String orderId = response.getData().getOrderId();
            String transId = response.getData().getTxnId();

            final SpannableString text = new SpannableString("Hello! Your Order has been successfully booked\n\nYour Order id is " + orderId + "\n\nThank you for your order");
            text.setSpan(new RelativeSizeSpan(1.5f), text.length() - orderId.length(), text.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new ForegroundColorSpan(Color.WHITE), 3, text.length() - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            final FlatDialog flatDialog = new FlatDialog(this);
            flatDialog.setTitle("Congrats")
                    .setSubtitle(text.toString())
                    .setFirstButtonText("Back to dashboard")
                    .setSecondButtonText("My Order List")
                    .setIcon(R.drawable.tick_success)
                    .withFirstButtonListner(view -> {
                        flatDialog.dismiss();
                        redirectToHomeScreen();
                    }).withSecondButtonListner(view -> {
                        flatDialog.dismiss();
                        redirectToOrderListScreen();
                    })
                    .show();

//            redirectToOrderDetailsScreen(response.getData().getOrderId());
        } else {
            // Payment method submitted
            OnlineShoppingCheckout checkout = new OnlineShoppingCheckout();
            checkout.setId(response.getData().getId().toString());
            checkout.setOrderId(response.getData().getOrderId());
            checkout.setMobile(userMobile);
            checkout.setEmail(userEmail);
            checkout.setName(userName);
            checkout.setGrandTotal(totalPaid);
            viewModel.onlineShoppingProductCheckOut(checkout);
        }
    }

    @Override
    public void submitPaymentMethodForDeal(DealPaymentSelectionResponse response) {

        if (paymentSelectionType == 3 || paymentSelectionType == 1) {

            ThankYouCheckout checkout = new ThankYouCheckout();
            checkout.setCustomerId(Integer.parseInt(viewModel.getDataManager().getUid()));
            checkout.setCartId(cartId);
            viewModel.thankYouDealService(checkout);

            String orderId = response.getData().getOrderId();

            final SpannableString text = new SpannableString("Hello! Your Order has been successfully booked\n\nYour Order id is " + orderId + "\n\nThank you for your order");
            text.setSpan(new RelativeSizeSpan(1.5f), text.length() - orderId.length(), text.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new ForegroundColorSpan(Color.WHITE), 3, text.length() - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            final FlatDialog flatDialog = new FlatDialog(this);
            flatDialog.setTitle("Congrats")
                    .setSubtitle(text.toString())
                    .setFirstButtonText("Back to dashboard")
                    .setSecondButtonText("My Deals Order List")
                    .setIcon(R.drawable.tick_success)
                    .withFirstButtonListner(view -> {
                        flatDialog.dismiss();
                        redirectToHomeScreen();
                    }).withSecondButtonListner(view -> {
                        flatDialog.dismiss();
                        redirectToDealListScreen();
                    })
                    .show();
        } else {
            // Payment method submitted
            DealOrderCheckout checkout = new DealOrderCheckout();
            checkout.setCustomerId(response.getData().getId());
            checkout.setOrderId(response.getData().getOrderId());
            viewModel.dealProductCheckOut(checkout);
        }
    }

    @Override
    public void submitPaymentMethodForSalon(SalonPaymentSelectionResponse response) {
        if (paymentSelectionType == 3 || paymentSelectionType == 1) {

            ThankYouCheckout checkout = new ThankYouCheckout();
            checkout.setCustomerId(Integer.parseInt(viewModel.getDataManager().getUid()));
            checkout.setCartId(cartId);
            viewModel.thankYouSalonService(checkout);

            String orderId = response.getData().getOrderId();

            final SpannableString text = new SpannableString("Hello! Your Order has been successfully booked\n\nYour Order id is " + orderId + "\n\nThank you for your order");
            text.setSpan(new RelativeSizeSpan(1.5f), text.length() - orderId.length(), text.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new ForegroundColorSpan(Color.WHITE), 3, text.length() - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            final FlatDialog flatDialog = new FlatDialog(this);
            flatDialog.setTitle("Congrats")
                    .setSubtitle(text.toString())
                    .setFirstButtonText("Back to dashboard")
                    .setSecondButtonText("My Salon Order List")
                    .setIcon(R.drawable.tick_success)
                    .withFirstButtonListner(view -> {
                        flatDialog.dismiss();
                        redirectToHomeScreen();
                    }).withSecondButtonListner(view -> {
                        flatDialog.dismiss();
                        redirectToSalonAtHomeListScreen();
                    })
                    .show();
        } else {
            // Payment method submitted
            SalonOrderCheckout checkout = new SalonOrderCheckout();
            checkout.setCustomerId(String.valueOf(response.getData().getId()));
            checkout.setOrderId(response.getData().getOrderId());
            checkout.setName(userName);
            checkout.setMobile(userMobile);
            checkout.setEmail(userEmail);
            checkout.setGrandTotal(response.getData().getPaidAmount());
            viewModel.salonProductCheckOut(checkout);
        }
    }

    private void redirectToHomeScreen() {
        Intent mIntent = DashboardActivity.newIntent(this);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
    }

   /* private void redirectToOrderDetailsScreen(String orderId) {
        Intent mIntent = OrderDetailsScreenActivity.newIntent(this);
        mIntent.putExtra("orderId", orderId);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
    }*/

    private void redirectToOrderListScreen() {
        Intent intent = ShoppingOrderListScreenActivity.newIntent(this);
        startActivity(intent);
    }

    private void redirectToDealListScreen() {
        Intent intent = DashboardActivity.newIntent(this);
        intent.putExtra("page", 2);
        startActivity(intent);
        finishAffinity();
    }

    private void redirectToSalonAtHomeListScreen() {
        Intent intent = DashboardActivity.newIntent(this);
        intent.putExtra("page", 1);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void shoppingFinalCheckout(PaymentCheckoutResponse response) {
        // Online Shopping Product Checkout
        Intent mIntent = OnlineShoppingPaymentWebviewActivity.newIntent(this);
        mIntent.putExtra("paymentUrl", response.getData().getPaymentLink());
        mIntent.putExtra("orderId", response.getData().getOrderId());
        startActivity(mIntent);
    }

    @Override
    public void updateCODUI(String msg, boolean isCodAvailable) {
        this.isCodAvailable = isCodAvailable;
        if (!isCodAvailable) {
            mBinding.llCod.setForeground(getDrawable(R.drawable.transparent_foreground));
            mBinding.codMsg.setVisibility(View.VISIBLE);
            mBinding.codMsg.setText(msg);
        }
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