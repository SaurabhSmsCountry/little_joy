package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.littlejoyindia.littlejoyindia.databinding.ActivityShoppingOrderListBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.modals.MyShoppingOrderListResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.orderList.ShoppingOrderItemClickListener;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.orderList.ShoppingOrderListAdapter;

import java.util.List;

import javax.inject.Inject;

public class ShoppingOrderListScreenActivity extends BaseActivity<ActivityShoppingOrderListBinding, ShoppingOrderListScreenViewModel> implements ShoppingOrderListScreenNavigator, ShoppingOrderItemClickListener {

    private ActivityShoppingOrderListBinding binding;

    public static final String TAG = ShoppingOrderListScreenActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private ShoppingOrderListScreenViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, ShoppingOrderListScreenActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping_order_list;
    }

    @Override
    public ShoppingOrderListScreenViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ShoppingOrderListScreenViewModel.class);
        return viewModel;
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(MyShoppingOrderListResponse orderListResponse) {
        shoppingOrderDataList = orderListResponse.getData();

        mAdapter = new ShoppingOrderListAdapter(shoppingOrderDataList, this,this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

    }

    RecyclerView recyclerView;
    ShoppingOrderListAdapter mAdapter;
    List<MyShoppingOrderListResponse.ShoppingOrderData> shoppingOrderDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        binding = ActivityShoppingOrderListBinding.inflate(getLayoutInflater());

        getViewDataBinding().imageBack.setOnClickListener(view -> {
            onBackPressed();
        });

        recyclerView = getViewDataBinding().rvOrderList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        viewModel.getShoppingOrderList();
    }

    @Override
    public void onItemClickListener(int position) {
        redirectToOrderDetailsScreen(shoppingOrderDataList.get(position).getOrderId());
    }

    private void redirectToOrderDetailsScreen(String orderId) {
        Intent mIntent = OrderDetailsScreenActivity.newIntent(this);
        mIntent.putExtra("orderId", orderId);
        mIntent.putExtra("isFromOrderList", true);
        startActivity(mIntent);
    }
}
