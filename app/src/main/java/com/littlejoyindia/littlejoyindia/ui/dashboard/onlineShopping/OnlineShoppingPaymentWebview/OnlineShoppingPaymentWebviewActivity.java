package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingPaymentWebview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.BuildConfig;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.remote.AppApiHelper;
import com.littlejoyindia.littlejoyindia.databinding.ActivityShoppingPaymentWebviewBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.ShoppingPaymentViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.OrderDetailsScreenActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.ShoppingOrderListScreenActivity;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

import javax.inject.Inject;

import im.delight.android.webview.AdvancedWebView;

public class OnlineShoppingPaymentWebviewActivity extends BaseActivity<ActivityShoppingPaymentWebviewBinding, ShoppingPaymentViewModel>
        implements ShoppingPaymentNavigator, AdvancedWebView.Listener {

    @Inject
    ViewModelProviderFactory factory;

    private ActivityShoppingPaymentWebviewBinding mBinding;
    private ShoppingPaymentViewModel viewModel;

    private AdvancedWebView mWebView;

    String paymentUrl;
    String orderId;

    public static Intent newIntent(Context context) {
        return new Intent(context, OnlineShoppingPaymentWebviewActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping_payment_webview;
    }

    @Override
    public ShoppingPaymentViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ShoppingPaymentViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.setNavigator(this);
        mBinding = getViewDataBinding();
        mBinding.setViewModel(viewModel);

        Bundle mBundle = getIntent().getExtras();

        paymentUrl = mBundle.getString("paymentUrl");
        orderId = mBundle.getString("orderId");


        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.setMixedContentAllowed(false);
        mWebView.loadUrl(paymentUrl);
    }

    @Override
    public void showToastMessage(String message) {
        CommonUtils.toastMe(message, this);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {
        Log.d("@Current Url", url);

        if (url.equalsIgnoreCase(BuildConfig.BASE_URL + "payment-success")) {
            // Payment Success
//            showToastMessage("Your order is successfully completed");
//            redirectToOrderDetailsScreen();

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
        } else if (url.equalsIgnoreCase(BuildConfig.BASE_URL + "payment-failure")) {
            // Payment Failed
            showToastMessage("Your payment is failed, Please try again");
            finish();
        } else {

        }
    }

    private void redirectToHomeScreen() {
        Intent mIntent = DashboardActivity.newIntent(this);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
    }

    private void redirectToOrderDetailsScreen() {
        Intent mIntent = OrderDetailsScreenActivity.newIntent(this);
        mIntent.putExtra("orderId", orderId);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
    }

    private void redirectToOrderListScreen() {
        Intent intent = ShoppingOrderListScreenActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
