package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.OnlineShoppingResponse;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;
import com.littlejoyindia.littlejoyindia.databinding.FragmentOnlineShoppingBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productCategory.HomeProductsAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productCategory.ProductCategoryAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productCategory.ShoppingSubListDialogFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.FilterModal;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ShoppingProductActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productOverview.ProductDescriptionActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.SalonFragment;
import com.littlejoyindia.littlejoyindia.utils.TopSliderAdapter;
import com.minibugdev.sheetselection.SheetSelection;
import com.minibugdev.sheetselection.SheetSelectionItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import me.relex.circleindicator.CircleIndicator;
import ss.com.bannerslider.Slider;

public class OnlineShoppingFragment extends BaseFragment<FragmentOnlineShoppingBinding, OnlineShoppingViewModel>
        implements OnlineShoppingNavigator, Function2<SheetSelectionItem, Integer, Unit> {

    public static final String TAG = SalonFragment.class.getSimpleName();

    ShoppingSubListDialogFragment shoppingSubListDialogFragment;

    @Inject
    ViewModelProviderFactory factory;
    private OnlineShoppingViewModel mViewModel;

    private FragmentListener fragmentListener;
    private boolean isAttached = false;
    private FragmentOnlineShoppingBinding mBinding;

    GridView productCategoryGridView;
    GridView homeProductGridView;

    List<FilterModal> filterModalList = new ArrayList<>();
    List<SheetSelectionItem> filterListItem = new ArrayList<>();
    int selectedFilter = 0;

    public static OnlineShoppingFragment newInstance() {
        Bundle args = new Bundle();
        OnlineShoppingFragment fragment = new OnlineShoppingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_online_shopping;
    }

    @Override
    public OnlineShoppingViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(OnlineShoppingViewModel.class);
        return mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.setNavigator(this);

        filterModalList.add(new FilterModal(0, "Price Low to High", "low to high", false));
        filterModalList.add(new FilterModal(1, "Price High to Low", "high to low", false));
        filterModalList.add(new FilterModal(2, "Discount", "discount", false));
        filterModalList.add(new FilterModal(3, "Rating High to Low", "rating", false));

        for (FilterModal filterModal : filterModalList) {
            SheetSelectionItem item = new SheetSelectionItem(String.valueOf(filterModal.getId()), filterModal.getTitle(), null);
            filterListItem.add(item);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.getCategory();
        mViewModel.getProductList("");
        Slider slider = getViewDataBinding().topSlider;

        productCategoryGridView = getViewDataBinding().gridViewCategory;
        homeProductGridView = getViewDataBinding().gridViewProducts;

        // Slider images
        ArrayList<String> sliderArrayList = new ArrayList<>();
        sliderArrayList.add("https://www.littlejoyindia.com/shopping_banner/ccec720791e0866bb59fdc681f995bf4.jpg");
        sliderArrayList.add("https://www.littlejoyindia.com/shopping_banner/2ee400817447c214a29107c9f39c31cf.jpg");


        slider.setAdapter(new TopSliderAdapter(getActivity(), sliderArrayList));

        CircleIndicator indicator = getViewDataBinding().indicatorTopSlider;
        indicator.createIndicators(4, 0);

        if (isAttached) {
            fragmentListener.setHeaderText("Online Shopping");
            fragmentListener.setHeaderColor(R.color.quantum_purple800);
        }

        getViewDataBinding().filterIcon.setOnClickListener(view1 -> openSheet());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentListener = (FragmentListener) context;
            isAttached = true; //flag for whether this framgnet is attache to pager
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + " must implement interface ChoseLocFragment");
        }
    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void setCategoryAndSubCategoryUI(List<OnlineShoppingResponse.Datum> dataList) {
        ArrayList<OnlineShoppingResponse.Datum> courseModelArrayList = new ArrayList<>(dataList);
        ProductCategoryAdapter adapter = new ProductCategoryAdapter(getActivity().getApplicationContext(), courseModelArrayList, this);
        productCategoryGridView.setAdapter(adapter);
    }

    @Override
    public void onItemClickListener(OnlineShoppingResponse.Datum data) {
        shoppingSubListDialogFragment = ShoppingSubListDialogFragment.newInstance(this, data);
        shoppingSubListDialogFragment.show(getActivity().getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onProductItemClickListener(ProductListResponse.Datum data) {
        Intent intent = ProductDescriptionActivity.newIntent(getActivity());
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void onItemSubCategoryClickListener(OnlineShoppingResponse.SubCategory data, OnlineShoppingResponse.Datum datum) {
        Intent intent = new Intent(getActivity(), ShoppingProductActivity.class);
        intent.putExtra("mainId", datum.getCategoryId());
        intent.putExtra("subCategoryName", data.getCategory());
        intent.putExtra("mainCategory", datum.getCategory());
        shoppingSubListDialogFragment.dismiss();
        startActivity(intent);
    }

    @Override
    public void setProductList(List<ProductListResponse.Datum> dataList) {
        ArrayList<ProductListResponse.Datum> courseModelArrayList = new ArrayList<>(dataList);
        HomeProductsAdapter adapter = new HomeProductsAdapter(getActivity().getApplicationContext(), courseModelArrayList, this);
        homeProductGridView.setAdapter(adapter);
    }

    @Override
    public Unit invoke(SheetSelectionItem sheetSelectionItem, Integer integer) {
        selectedFilter = Integer.parseInt(sheetSelectionItem.getKey());
        mViewModel.getProductList(filterModalList.get(selectedFilter).getFilterType());
        return null;
    }

    public interface FragmentListener {
        void setHeaderText(String headerText);

        void setHeaderColor(int color);
    }

    void openSheet() {
        SheetSelection.Builder builder = new SheetSelection.Builder(getActivity());
        builder.title("Sort By");
        builder.items(filterListItem);
        builder.selectedPosition(selectedFilter);
        builder.showDraggedIndicator(true);
        builder.onItemClickListener(this);
        builder.show();
    }
}