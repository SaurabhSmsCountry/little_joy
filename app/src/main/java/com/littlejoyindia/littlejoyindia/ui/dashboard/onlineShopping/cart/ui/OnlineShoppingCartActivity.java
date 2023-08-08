package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.databinding.ActivityOnlineShoppingCartBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.basicDetails.BasicDetailsActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.cart_item_list.ShoppingCartAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.cart_item_list.ShoppingCartItemClickListener;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.modal.OnlineShoppingCartResponse;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class OnlineShoppingCartActivity extends BaseActivity<ActivityOnlineShoppingCartBinding, OnlineShoppingCartViewMOdel> implements OnlineShoppingCartNavigator, ShoppingCartItemClickListener {

    private ActivityOnlineShoppingCartBinding binding;

    public static final String TAG = OnlineShoppingCartActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private OnlineShoppingCartViewMOdel viewModel;

    int cartId = -1;
    int total = 0;

    public static Intent newIntent(Context context) {
        return new Intent(context, OnlineShoppingCartActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_online_shopping_cart;
    }

    @Override
    public OnlineShoppingCartViewMOdel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(OnlineShoppingCartViewMOdel.class);
        return viewModel;
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    RecyclerView recyclerView;
    ShoppingCartAdapter mAdapter;
    List<OnlineShoppingCartResponse.Datum> datumList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        binding = ActivityOnlineShoppingCartBinding.inflate(getLayoutInflater());

        ImageButton imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(v -> {
            //What to do on back clicked
            onBackPressed();
        });
        recyclerView = findViewById(R.id.rvView);
        viewModel.getCart();
    }

    @Override
    public void updateUI(OnlineShoppingCartResponse cartList) {
        // Set Total Amount
        total = cartList.getTotal();
        viewModel.checkoutAmount.set("\u20B9" + " " + cartList.getTotal().toString());
        datumList.clear();
        datumList.addAll(cartList.getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new ShoppingCartAdapter(datumList, null, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        if (!cartList.getData().isEmpty()){
            cartId = cartList.getData().get(0).getCartId();
        }
    }

    @Override
    public void onclickAddMoreService() {

    }

    @Override
    public void onclickCheckOutSaloon() {
        /*Intent mIntent = AddressSelectionActivity.newIntent(this);
        mIntent.putExtra("checkoutType", 2);
        mIntent.putExtra("cartId", cartId);
        mIntent.putExtra("cartTotal", total);
        startActivity(mIntent);*/

        Intent mIntent = BasicDetailsActivity.newIntent(this);
        mIntent.putExtra("checkoutType", 2);
        mIntent.putExtra("cartId", cartId);
        mIntent.putExtra("cartTotal", total);
        mIntent.putExtra("isCodAvailable", false);
        startActivity(mIntent);
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onItemDeleteClickListener(int position) {
        SalonRequest.RemoveDealCartItemRequest request = new SalonRequest.RemoveDealCartItemRequest(datumList.get(position).getCartId().toString(), datumList.get(position).getProId());
        viewModel.removeItemFromCart(request);
    }

    @Override
    public void onItemMinusClickListener(int position) {
        OnlineShoppingCartResponse.Datum datum = datumList.get(position);
        int newQty = -1;
        if (datum.getQty() == 1){
            newQty = 0;
        }
        addToAndRemoveFromCart(newQty, datum.getProId());

    }

    private void addToAndRemoveFromCart(int newQty, String prodId) {
        CartModel model = new CartModel();
        model.setPro_id(prodId);
        model.setQty(newQty);
        viewModel.addToAndRemoveCart(model);
    }

    @Override
    public void onItemAddClickListener(int position) {
        OnlineShoppingCartResponse.Datum datum = datumList.get(position);
        int newQty = 1;
        addToAndRemoveFromCart(newQty, datum.getProId());
    }
}
