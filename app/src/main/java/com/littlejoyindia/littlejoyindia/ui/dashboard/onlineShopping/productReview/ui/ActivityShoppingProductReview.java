package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivityShoppingProductReviewBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.models.ProductReviewResponse;

import javax.inject.Inject;

public class ActivityShoppingProductReview extends BaseActivity<ActivityShoppingProductReviewBinding, ShoppingProductReviewViewModel> implements ShoppingProductReviewNavigator {

    private ActivityShoppingProductReviewBinding binding;

    public static final String TAG = ActivityShoppingProductReview.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private ShoppingProductReviewViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, ActivityShoppingProductReview.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping_product_review;
    }

    @Override
    public ShoppingProductReviewViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ShoppingProductReviewViewModel.class);
        return viewModel;
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(ProductReviewResponse reviewResponse) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (reviewResponse.getData() != null) {
            getViewDataBinding().tvTotalReview.setText(String.format("%d customer reviews", reviewResponse.getData().size()));
        }

        mAdapter = new ProductReviewListAdapter(reviewResponse.getData());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            reviewResponse.getData().forEach((datum -> {
                float rating = Float.parseFloat(datum.getRating());
                if (rating == 5.0) {
                    p5 = p5 + 1;
                }
                if (rating == 4.0) {
                    p4 = p4 + 1;
                }
                if (rating == 3.0) {
                    p3 = p3 + 1;
                }
                if (rating == 2.0) {
                    p2 = p2 + 1;
                }
                if (rating == 1.0) {
                    p1 = p1 + 1;
                }
            }));

            getViewDataBinding().progress5.setProgress(Integer.parseInt(String.valueOf((p5 * 100) / 5)));
            getViewDataBinding().progress4.setProgress(Integer.parseInt(String.valueOf((p4 * 100) / 5)));
            getViewDataBinding().progress3.setProgress(Integer.parseInt(String.valueOf((p3 * 100) / 5)));
            getViewDataBinding().progress2.setProgress(Integer.parseInt(String.valueOf((p2 * 100) / 5)));
            getViewDataBinding().progress1.setProgress(Integer.parseInt(String.valueOf((p1 * 100) / 5)));
        }
    }

    RecyclerView recyclerView;
    String productId;
    String productName;
    String productRating;
    ProductReviewListAdapter mAdapter;

    int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        binding = ActivityShoppingProductReviewBinding.inflate(getLayoutInflater());

        productName = getIntent().getExtras().getString("title");
        productId = getIntent().getExtras().getString("proId");
        productRating = getIntent().getExtras().getString("proRating");

        getViewDataBinding().proTitle.setText(productName);
        getViewDataBinding().tvMainRating.setText(productRating + " of 5");
        getViewDataBinding().avgRating.setRating(Float.parseFloat(productRating));

        ImageButton imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(v -> {
            //What to do on back clicked
            onBackPressed();
        });
        recyclerView = findViewById(R.id.reviewsRecyclerView);
        // Change into real productId
        viewModel.getProductReviews(productId);
    }
}
