package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivityDashboardBinding;
import com.littlejoyindia.littlejoyindia.databinding.ActivityShoppingProductBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ui.main.ShoppingProductFragment;
import com.minibugdev.sheetselection.SheetSelection;
import com.minibugdev.sheetselection.SheetSelectionItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class ShoppingProductActivity extends BaseActivity<ActivityShoppingProductBinding, ShoppingProductViewModel> implements ShoppingProductFragment.FragmentListener, ShoppingProductNavigator, HasSupportFragmentInjector, Function2<SheetSelectionItem, Integer, Unit> {


    public static final String TAG = ShoppingProductActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private ShoppingProductViewModel viewModel;
    private ActivityShoppingProductBinding mActivityMainBinding;

    private Toolbar mToolbar;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    private Function2<? super SheetSelectionItem, ? super Integer, Unit> listener;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping_product;
    }

    @Override
    public ShoppingProductViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ShoppingProductViewModel.class);
        return viewModel;
    }

    List<FilterModal> filterModalList = new ArrayList<>();
    List<SheetSelectionItem> filterListItem = new ArrayList<>();
    int selectedFilter = 0;

    ShoppingProductFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = getViewDataBinding();
        viewModel.setNavigator(this);

        mToolbar = mActivityMainBinding.toolbar;

        filterModalList.add(new FilterModal(0, "Price Low to High", "low to high", false));
        filterModalList.add(new FilterModal(1, "Price High to Low", "high to low", false));
        filterModalList.add(new FilterModal(2, "Discount", "", false));
        filterModalList.add(new FilterModal(3, "Rating High to Low", "", false));

        for (FilterModal filterModal : filterModalList) {
            SheetSelectionItem item = new SheetSelectionItem(String.valueOf(filterModal.getId()), filterModal.title, null);
            filterListItem.add(item);
        }

        Bundle bundle = getIntent().getExtras();

        String subCategoryName = bundle.getString("subCategoryName");
        String mainCategory = bundle.getString("mainCategory");
        String id = bundle.getString("mainId");

        mToolbar.setTitle(subCategoryName);
        setSupportActionBar(mToolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (savedInstanceState == null) {
            fragment = ShoppingProductFragment.newInstance(this, id, subCategoryName, mainCategory, "");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
        }

        mActivityMainBinding.filterIcon.setOnClickListener((view -> openSheet()));
    }

    @Override
    public void setHeaderText(String headerText) {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void showToastMessage(String message) {

    }

    void openSheet() {
        SheetSelection.Builder builder = new SheetSelection.Builder(this);
        builder.title("Sort By");
        builder.items(filterListItem);
        builder.selectedPosition(selectedFilter);
        builder.showDraggedIndicator(true);
        builder.onItemClickListener(this);
        builder.show();
    }

    @Override
    public Unit invoke(SheetSelectionItem sheetSelectionItem, Integer integer) {
        selectedFilter = Integer.parseInt(sheetSelectionItem.getKey());
        fragment.refreshList(filterModalList.get(selectedFilter).filterType);
        return null;
    }
}

