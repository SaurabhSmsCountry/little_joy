package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivityShoppingOrderScreenBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.cart_item_list.ShoppingCartAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui.CommanUtils;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.addReview.AddProductReviewActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.modals.MyShoppingOrderListResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.timeline.OrderStatusResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.timeline.TimeLineAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class OrderDetailsScreenActivity extends BaseActivity<ActivityShoppingOrderScreenBinding, OrderDetailsScreenViewModel> implements OrderDetailsScreenNavigator {

    private ActivityShoppingOrderScreenBinding binding;

    public static final String TAG = OrderDetailsScreenActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private OrderDetailsScreenViewModel viewModel;
    String orderId;
    boolean isFromOrderList = false;
    Bundle mBundle;

    RecyclerView recyclerView;
    TimeLineAdapter mAdapter;
    private List<String> orderStatus = new ArrayList<>();

    Button btnSubmit;

    public static Intent newIntent(Context context) {
        return new Intent(context, OrderDetailsScreenActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping_order_screen;
    }

    @Override
    public OrderDetailsScreenViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(OrderDetailsScreenViewModel.class);
        return viewModel;
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void updateUI(MyShoppingOrderListResponse.ShoppingOrderData data) {
        try {
            // Set Order Details in UI
            // Order Id and Transaction Id's Section
            getViewDataBinding().tvOrderId.setText(data.getOrderId());
            getViewDataBinding().tvTransactionId.setText(data.getTxnId());
            // Order Cost Section
            getViewDataBinding().tvPaymentMethod.setText(data.getPayMode());
            getViewDataBinding().tvOrderCost.setText(String.format("₹ %s", data.getTotalAmount()));
            getViewDataBinding().tvDeliveryCost.setText(String.format("₹ %s", data.getShipCharge()));
            getViewDataBinding().tvTotalCost.setText(String.format("₹ %s", data.getGrandTotal()));
            // User Details Section
            getViewDataBinding().tvUserName.setText(data.getName());
            getViewDataBinding().tvUserMobile.setText(data.getMobile());
            getViewDataBinding().tvUserAddress.setText(data.getAddress());

            // Product Details Section
            SpannableStringBuilder productDescription = new SpannableStringBuilder();

            for (int i = 0; i < data.getProductDetails().size(); i++) {
                productDescription.append(data.getProductDetails().get(i).getService()).append(" * Qty ").append(data.getProductDetails().get(i).getQty().toString());
                productDescription.append("\n");
            }

            getViewDataBinding().tvProductDetails.setText(productDescription.toString());

        getViewDataBinding().btnSubmitReview.setOnClickListener(view -> {
            // Redirect to redirect to Add Review screen
            Intent intent = AddProductReviewActivity.newIntent(OrderDetailsScreenActivity.this);
            intent.putExtra("orderId",data.getOrderId());
            CommanUtils.productDetails.addAll(data.getProductDetails());
            startActivity(intent);
        });
        }catch (Exception e){
            Log.d("@Error 2",e.toString());
        }

    }

    @Override
    public void updateTimeLineUI(OrderStatusResponse response) {
        try {
            orderStatus.add("Processing");
            orderStatus.add("Accepted");
            orderStatus.add("Shipped");
            orderStatus.add("Delivered");
            orderStatus.add("Completed");
            orderStatus.add("Canceled");

            if (response.getData().getOrderStatus().equalsIgnoreCase("Delivered")) {
                getViewDataBinding().btnSubmitReview.setVisibility(View.VISIBLE);
            }

            mAdapter = new TimeLineAdapter(orderStatus, response.getData().getOrderStatus(), response.getData().getUpdationDate(), 0);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setHasFixedSize(true);
        }catch (Exception e){
            Log.d("@Error", Arrays.toString(e.getStackTrace()));
        }
        // mAdapter.updateList(orderStatus, response.getData().getOrderStatus(), response.getData().getUpdationDate());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            orderId = mBundle.getString("orderId","");
            isFromOrderList = mBundle.getBoolean("isFromOrderList", false);
        }

        binding = ActivityShoppingOrderScreenBinding.inflate(getLayoutInflater());
        if (orderId != null) {
            viewModel.getOrderByOrderId(orderId);
            viewModel.getOrderStatusByOrderId(orderId);
        }

        getViewDataBinding().btnBackToHome.setOnClickListener(view -> {
            redirectToHomeScreen();
        });
        getViewDataBinding().btnBackToOrderList.setOnClickListener(view -> {
            redirectToOrderListScreen();
        });

        getViewDataBinding().btnBackToHome.setVisibility(isFromOrderList ? View.GONE : View.VISIBLE);
        getViewDataBinding().btnBackToOrderList.setVisibility(isFromOrderList ? View.GONE : View.VISIBLE);
        getViewDataBinding().imageBack.setVisibility(isFromOrderList ? View.VISIBLE : View.GONE);
        getViewDataBinding().imageBack.setOnClickListener(view -> {
            onBackPressed();
        });

        recyclerView = getViewDataBinding().recyclerView;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        btnSubmit = getViewDataBinding().btnSubmitReview;
    }

    private void redirectToHomeScreen() {
        Intent mIntent = DashboardActivity.newIntent(this);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
    }

    private void redirectToOrderListScreen() {
        Intent intent = ShoppingOrderListScreenActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isFromOrderList) {
            redirectToHomeScreen();
        }
    }
}
