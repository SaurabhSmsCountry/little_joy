package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.addReview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
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
import com.littlejoyindia.littlejoyindia.data.model.CommonResponse;
import com.littlejoyindia.littlejoyindia.databinding.ActivityAddProductReviewBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui.CommanUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class AddProductReviewActivity extends BaseActivity<ActivityAddProductReviewBinding, AddProductReviewViewModel> implements AddProductReviewNavigator, AddProductReviewListener {

    private ActivityAddProductReviewBinding binding;

    public static final String TAG = AddProductReviewActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private AddProductReviewViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AddProductReviewActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_product_review;
    }

    @Override
    public AddProductReviewViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(AddProductReviewViewModel.class);
        return viewModel;
    }

    RecyclerView recyclerView;
    AddReviewProductAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.setNavigator(this);

        binding = ActivityAddProductReviewBinding.inflate(getLayoutInflater());

        ImageButton imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(v -> {
            //What to do on back clicked
            onBackPressed();
        });
        recyclerView = findViewById(R.id.rvProductReview);
        adapter = new AddReviewProductAdapter(CommanUtils.productDetails, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateUI(int position, CommonResponse response) {
        showToastMessage(response.getMessage());
    }

    @Override
    public void onItemClick(int position, String review, int rating) {
        Map<String, String> map = new HashMap<>();
        map.put("comment", review);
        map.put("pro_id", CommanUtils.productDetails.get(position).getProId());
        map.put("rating", String.valueOf(rating));
        viewModel.submitReview(position, map);
    }
}
