package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.Deal;
import com.littlejoyindia.littlejoyindia.data.model.MerchantData;
import com.littlejoyindia.littlejoyindia.databinding.ActivityAppointmentBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.CartActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.Services;
import com.littlejoyindia.littlejoyindia.utils.ActivityPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class AppointmentActivity extends BaseActivity<ActivityAppointmentBinding, AppointmentViewModel>
        implements AppointmentNavigator , DealsFragment.FragmentListener , HasSupportFragmentInjector {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    ActivityPagerAdapter mPagerAdapter;

    private ActivityAppointmentBinding mBinding;
    private AppointmentViewModel viewModel;

    String merchantId =  null;
    String merchantName =  null;

    public static List<MerchantData> merchantDataList  = new ArrayList<>();


    public static Intent newIntent(Context context) {
        return new Intent(context, AppointmentActivity.class);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_appointment;
    }

    @Override
    public AppointmentViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(AppointmentViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();




        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){
            merchantId = mBundle.getString("merchantId");
            merchantName = mBundle.getString("merchantName");
        }

        setUpToolBar();

        viewModel.getDealsOnUser(merchantId, 0);

        mPagerAdapter = new ActivityPagerAdapter(getSupportFragmentManager());

        mBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View v =   tab.getCustomView();
                if (v !=null){
                    int i = tab.getPosition();
                    changeTabItemView(v , merchantDataList.get(i) ,
                            "https://littlejoyindia.com/icons/deals_icons/"+merchantDataList.get(i).getActiveIcon(), true
                    );
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int i = tab.getPosition();
                View v =   tab.getCustomView();
                changeTabItemView(v , merchantDataList.get(i) ,
                        "https://littlejoyindia.com/icons/deals_icons/"+merchantDataList.get(i).getInactiveIcon(), false
                );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        getViewDataBinding().llFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = CartActivity.newIntent(AppointmentActivity.this);
                mIntent.putExtra("cartType", 2);
                mIntent.putExtra("userCity", getIntent().getExtras().getString("userCity"));
//                startActivity(mIntent);
                someActivityResultLauncher.launch(mIntent);
            }
        });

    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                viewModel.getDealsOnUser(merchantId, 0);
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    Log.e("@TAG", "Get Result");
                }
            });

    private View createTabItemView(MerchantData mCategory  ,
                                   String imgUri, boolean isActive) {

        LayoutInflater vi = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.category_tab_item, null);

        ImageView imageView = v.findViewById(R.id.imgCat);
        TextView tv = v.findViewById(R.id.tvCatName);
        LinearLayout llLayout = v.findViewById(R.id.llLayout);
        tv.setText(mCategory.getCategory());

        if (isActive){
            v.findViewById(R.id.vOrange).setVisibility(View.VISIBLE);
            tv.setTextColor(getResources().getColor(R.color.black));
            llLayout.setBackground(getResources().getDrawable(R.drawable.circle_tab_selected));

            if (mCategory.getCategory().equalsIgnoreCase("All")){
                Glide.with(this).load(getResources().getDrawable(R.drawable.checkbox_selcted)).into(imageView);
            } else {
                Glide.with(this).load(imgUri).into(imageView);
            }
        } else {
            v.findViewById(R.id.vOrange).setVisibility(View.INVISIBLE);
            tv.setTextColor(getResources().getColor(R.color.dark_gray));
            llLayout.setBackground(getResources().getDrawable(R.drawable.circle_tab_unselected));

            if (mCategory.getCategory().equalsIgnoreCase("All")){
                Glide.with(this).load(getResources().getDrawable(R.drawable.checkbox_unselcted)).into(imageView);
            } else {
                Glide.with(this).load(imgUri).into(imageView);
            }
        }

        v.setTag(mCategory.getCategory());
        return v;
    }


    private void changeTabItemView(View v, MerchantData mCategory , String imgUri, boolean isActive) {

        ImageView imageView = v.findViewById(R.id.imgCat);
        TextView tv = v.findViewById(R.id.tvCatName);
        LinearLayout llLayout = v.findViewById(R.id.llLayout);
        tv.setText(mCategory.getCategory());

        if (isActive){
            v.findViewById(R.id.vOrange).setVisibility(View.VISIBLE);
            tv.setTextColor(getResources().getColor(R.color.black));
            llLayout.setBackground(getResources().getDrawable(R.drawable.circle_tab_selected));

            if (mCategory.getCategory().equalsIgnoreCase("All")){
                Glide.with(this).load(getResources().getDrawable(R.drawable.checkbox_selcted)).into(imageView);
            } else {
                Glide.with(this).load(imgUri).into(imageView);
            }
        } else {
            v.findViewById(R.id.vOrange).setVisibility(View.INVISIBLE);
            tv.setTextColor(getResources().getColor(R.color.dark_gray));
            llLayout.setBackground(getResources().getDrawable(R.drawable.circle_tab_unselected));

            if (mCategory.getCategory().equalsIgnoreCase("All")){
                Glide.with(this).load(getResources().getDrawable(R.drawable.checkbox_unselcted)).into(imageView);
            } else {
                Glide.with(this).load(imgUri).into(imageView);
            }
        }

    }

    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Book Appointment");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void setCategoryUI(List<MerchantData> data) {
        ArrayList<Deal> mArrayListSubCats = new ArrayList<>();

        for (int i = 0; i < data.size(); i++){
            mArrayListSubCats.addAll(data.get(i).getDeals());
        }


        MerchantData serviceModel =  new MerchantData();
        serviceModel.setCategory("All");
        serviceModel.setDeals(mArrayListSubCats);

        data.add(0,serviceModel);

        merchantDataList = data;

        mBinding.viewPager.setSaveFromParentEnabled(false);
        setupViewPager(mBinding.viewPager, data);
        setupTablayout(data);
    }



    private void setupViewPager(ViewPager viewPager, List<MerchantData> data) {


        for (int i = 0; i < data.size(); i++){
            mPagerAdapter.addFrag(DealsFragment.newInstance( i, data.get(i)), data.get(i).getCategory());
        }
        viewPager.setAdapter(mPagerAdapter);
    }

    private void setupTablayout(List<MerchantData> mCategory) {
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        for (int i = 0; i < mCategory.size(); i++) {
            if (i==0){
                mBinding.tabLayout.getTabAt(i).setCustomView(createTabItemView(mCategory.get(i) ,
                        "https://littlejoyindia.com/icons/deals_icons/"+mCategory.get(i).getActiveIcon()
                        , true
                ));
            } else {
                mBinding.tabLayout.getTabAt(i).setCustomView(createTabItemView(mCategory.get(i),
                        "https://littlejoyindia.com/icons/deals_icons/"+mCategory.get(i).getInactiveIcon()
                        , false
                ));
            }
        }
    }

    @Override
    public void setUIFooter(Integer qty, Integer total) {
        if (qty > 0){
            getViewDataBinding().llFooter.setVisibility(View.VISIBLE);
            getViewDataBinding().tvItem.setText(""+qty+ " Item in cart");
            getViewDataBinding().tvPrice.setText("\u20b9"+ total);
        } else {
            getViewDataBinding().llFooter.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateParentListData(Deal serviceModel, int qty, int isAddedToCart, int salonModelPosition) {
        Log.e("abc", serviceModel.getDealTitle() );
        for (int i = 0; i < merchantDataList.get(salonModelPosition).getDeals().size(); i++){
            if (merchantDataList.get(salonModelPosition).getDeals().get(i).getId().equals(serviceModel.getId())){
                merchantDataList.get(salonModelPosition).getDeals().get(i).setIsAddedInCart(isAddedToCart);
                merchantDataList.get(salonModelPosition).getDeals().get(i).setQuantity(qty);

                break;
            }
        }
    }

    @Override
    public void updateFooterUIFromFragment() {
        viewModel.getDealsOnUser(merchantId, 1);
    }
}