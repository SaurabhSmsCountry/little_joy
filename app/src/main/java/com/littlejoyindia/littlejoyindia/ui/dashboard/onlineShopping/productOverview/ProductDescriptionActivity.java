package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productOverview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.databinding.ActivityProductDescriptionBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui.OnlineShoppingCartActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.productListingRecyclerView.ProductAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.productListingRecyclerView.ProductItemClickListener;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.ui.ActivityShoppingProductReview;
import com.littlejoyindia.littlejoyindia.utils.AppBarStateChangeListener;
import com.littlejoyindia.littlejoyindia.utils.ImageViewerActivity;
import com.littlejoyindia.littlejoyindia.utils.TopSliderAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import me.relex.circleindicator.CircleIndicator;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

public class ProductDescriptionActivity extends BaseActivity<ActivityProductDescriptionBinding, ProductDescriptionViewModel> implements ProductDescriptionNavigator, ProductItemClickListener {

    private ActivityProductDescriptionBinding binding;

    public static final String TAG = ProductDescriptionActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private ProductDescriptionViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, ProductDescriptionActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_description;
    }

    @Override
    public ProductDescriptionViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ProductDescriptionViewModel.class);
        return viewModel;
    }

    TextView plusTv;
    TextView minusTv;
    TextView productCount;

    int productNumber = 0;
    ArrayList<String> sliderArrayList = new ArrayList<>();
    Slider slider;
    ProductListResponse.Datum datum;

    ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductDescriptionBinding.inflate(getLayoutInflater());
        viewModel.setNavigator(this);
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        slider = binding.topSlider;


        // add back arrow to toolbar

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Bundle bundle = getIntent().getExtras();

        datum = (ProductListResponse.Datum) bundle.getSerializable("data");

        viewModel.getProductDetails(datum.getId().toString());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
            binding.toolbar.setVisibility(View.VISIBLE);
            setSupportActionBar(toolbar);
        }

 /*       binding.appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
               if (state == State.COLLAPSED){
                   binding.toolbar.setVisibility(View.VISIBLE);
               }
               if (state == State.EXPANDED){
                   binding.toolbar.setVisibility(View.GONE);
               }
                if (state == State.IDLE){
                    binding.toolbar.setVisibility(View.GONE);
                }
            }
        });*/

        String selling = "\u20B9 " + String.valueOf(datum.getProductSellingPrice());
        String actual = "\u20B9 " + String.valueOf(datum.getProductActualPrice());

        binding.sellingPrice.setText(selling);
        binding.actualPrice.setText(actual);

        float per = (datum.getProductSellingPrice() * 100) / datum.getProductActualPrice();

        binding.productName.setText(datum.getProductTitle());
//        binding.toolbar.setTitle(datum.getProductTitle());
        binding.tvPer.setText("Save "+String.valueOf(Math.round(per))+" %");

        binding.actualPrice.setPaintFlags(binding.actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        plusTv = binding.plusAddToCart;
        minusTv = binding.minusRemoveFromCart;
        productCount = binding.productCount;

        binding.rating.setText(datum.getAvgRating());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvDescription.setText(Html.fromHtml(datum.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            binding.tvDescription.setText(Html.fromHtml(datum.getDescription()));
        }
        if (datum.getProductQuantity().equals("0")){
            binding.tvInStock.setText("NOT IN STOCK");
            binding.tvInStock.setCompoundDrawablesWithIntrinsicBounds(R.drawable.uncheck_24, 0, 0, 0);
        }else{
            binding.tvInStock.setText("IN STOCK");
            binding.tvInStock.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check_circle_24, 0, 0, 0);
        }


        plusTv.setOnClickListener((view -> {
            if (Integer.parseInt(datum.getProductQuantity()) > productNumber) {
                productNumber = productNumber + 1;
                productCount.setText(String.valueOf(productNumber));
            }
        }));

        minusTv.setOnClickListener((view -> {
            if (productNumber != 0) {
                productNumber = productNumber - 1;
                productCount.setText(String.valueOf(productNumber));
            }
        }));

        binding.rating.setOnClickListener(view -> {
            Intent intent = ActivityShoppingProductReview.newIntent(ProductDescriptionActivity.this);
            intent.putExtra("proId",datum.getId().toString());
            intent.putExtra("title", datum.getProductTitle());
            intent.putExtra("proRating", datum.getAvgRating());
            startActivity(intent);
        });

        CircleIndicator indicator = getViewDataBinding().indicatorTopSlider;
        indicator.createIndicators(4, 0);

        /*FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        binding.btnCheckout.setOnClickListener((view -> {
            if (productNumber == 0) {
                showToastMessage("Product Out Of Stock");
            } else {
                CartModel model = new CartModel();
                model.setPro_id(datum.getId().toString());
                model.setQty(productNumber);
                viewModel.addToCart(model);
            }
        }));

        binding.btnCod.setOnClickListener(view -> {
            if (!binding.etCOD.getText().toString().equals("")) {
                viewModel.checkCodIsAvailable(binding.etCOD.getText().toString());
            }
        });
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }



    @Override
    public void addToCart(String message) {
        showToastMessage(message);
        // Redirect to user into Cart Screen
        Intent intent = OnlineShoppingCartActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    public void updateUI(ProductListResponse.Datum datum) {
//        datum.setProductQuantity("5");
        if (datum.getProductImage().size() > 0) {
            sliderArrayList.addAll(datum.getProductImage());
        } else {
            sliderArrayList.add("http://masterjigroups.com/assets/img/current%20affairs.png");
        }

        slider.setAdapter(new TopSliderAdapter(this, sliderArrayList));

        slider.setOnSlideClickListener(position -> {
            Intent intent = new Intent(ProductDescriptionActivity.this, ImageViewerActivity.class);
            intent.putExtra("imageUrl",sliderArrayList.get(position));
            startActivity(intent);
        });

        this.datum = datum;
        if (Integer.parseInt(datum.getProductQuantity()) > 0){
            productNumber = productNumber + 1;
            productCount.setText(String.valueOf(productNumber));
        }else{
            binding.llQty.setBackgroundTintList(ContextCompat.getColorStateList(ProductDescriptionActivity.this, R.color.gray_cf));
        }

        if (datum.getRelatedProduct().size() > 0) {
            mAdapter = new ProductAdapter(datum.getRelatedProduct(), this,true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            binding.relatedProductRecyclerView.setLayoutManager(layoutManager);
            binding.relatedProductRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.relatedProductRecyclerView.setAdapter(mAdapter);
        } else {
            binding.llRelatedProducts.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateCODUI(String msg) {
        binding.tvCodAvaiable.setText(msg);
    }

    @Override
    public void onItemClick(int position) {
        ProductListResponse.Datum datum = this.datum.getRelatedProduct().get(position);
        Intent intent = ProductDescriptionActivity.newIntent(this);
        intent.putExtra("data", datum);
        startActivity(intent);
    }

    @Override
    public void onImageClick(int position) {
        Intent intent = new Intent(this, ImageViewerActivity.class);
        intent.putExtra("imageUrl",this.datum.getRelatedProduct().get(position).getProductImage().get(0));
        startActivity(intent);
    }
}