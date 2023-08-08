package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;
import com.littlejoyindia.littlejoyindia.databinding.FragmentMainBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ShoppingProductActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.productListingRecyclerView.ProductAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.productListingRecyclerView.ProductItemClickListener;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productOverview.ProductDescriptionActivity;
import com.littlejoyindia.littlejoyindia.utils.ImageViewerActivity;

import java.util.List;

import javax.inject.Inject;

public class ShoppingProductFragment extends BaseFragment<FragmentMainBinding, MainViewModel> implements ShoppingProductListingNavigator, ProductItemClickListener {

    private MainViewModel mViewModel;
    @Inject
    ViewModelProviderFactory factory;

    private FragmentListener fragmentListener;
    private boolean isAttached = false;
    private FragmentMainBinding mBinding;

    ProductAdapter mAdapter;

    static String id;
    static String subCategoryName;
    static String mainCategory;
    static String filterType;

    List<ProductListResponse.Datum> datumList;

    public static final String TAG = ShoppingProductFragment.class.getSimpleName();

    public static ShoppingProductFragment newInstance(ShoppingProductActivity shoppingProductActivity, String _id, String _subCategoryName, String _mainCategory, String _filterType) {
        id = _id;
        subCategoryName = _subCategoryName;
        mainCategory = _mainCategory;
        filterType = _filterType;
        return new ShoppingProductFragment();
    }

    public void refreshList(String type) {
        mViewModel.getProductList(id, subCategoryName, mainCategory, type);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(MainViewModel.class);
        return mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();

        mViewModel.getProductList(id, subCategoryName, mainCategory, filterType);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentListener = (ShoppingProductFragment.FragmentListener) context;
            isAttached = true; //flag for whether this framgnet is attache to pager
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + " must implement interface ChoseLocFragment");
        }
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProductList(List<ProductListResponse.Datum> datumList) {
        Log.e("@Response", datumList.toString());
        this.datumList = datumList;
        mAdapter = new ProductAdapter(datumList, this,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.productRecyclerView.setLayoutManager(layoutManager);
        mBinding.productRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mBinding.productRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position) {
        ProductListResponse.Datum datum = datumList.get(position);
        Intent intent = ProductDescriptionActivity.newIntent(getActivity());
        intent.putExtra("data", datum);
        startActivity(intent);
    }

    @Override
    public void onImageClick(int position) {
        Intent intent = new Intent(getActivity(),ImageViewerActivity.class);
        intent.putExtra("imageUrl",datumList.get(position).getProductImage().get(0));
        startActivity(intent);
    }

    public interface FragmentListener {
        void setHeaderText(String headerText);
    }


}