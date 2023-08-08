package com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonServiceModel;
import com.littlejoyindia.littlejoyindia.databinding.FragmentSalonServicesCatBinding;
import com.littlejoyindia.littlejoyindia.databinding.SalonFragmentBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.CartActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.home.HomeFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.CategoryPagerAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.SalonFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.SalonNavigator;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.SalonViewModel;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SalonCatFragment extends BaseFragment<FragmentSalonServicesCatBinding, SalonCatViewModel>
        implements SalonCatNavigator,  ServicesAdapter.ServiceAdapterListener{

    public static final String TAG = SalonCatFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private SalonCatViewModel viewModel;

    @Inject
    ServicesAdapter mServicesAdapter;

    private FragmentListener fragmentListener;
    private boolean isAttached = false;
    private FragmentSalonServicesCatBinding mBinding;

    private SalonServiceModel salonServiceModel = null;
    private int salonModelPosition = 0;

    public static SalonCatFragment newInstance(int position, SalonServiceModel salonServiceModel) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putSerializable("salonModel", salonServiceModel);
        SalonCatFragment fragment = new SalonCatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_salon_services_cat;
    }

    @Override
    public SalonCatViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(SalonCatViewModel.class);
        return viewModel;
    }

    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!getUserVisibleHint())
        {
            return;
        }
        salonServiceModel =  SalonFragment.salonServiceModelList.get(salonModelPosition);

        updateUI();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            fragmentListener = (FragmentListener) context;
            isAttached = true; //flag for whether this framgnet is attache to pager
        } catch (ClassCastException e){
            throw new ClassCastException(this.toString() + " must implement interface ChoseLocFragment");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();

        Bundle mBundle = getArguments();
        if(mBundle != null){
            salonModelPosition = mBundle.getInt("position");
            salonServiceModel = (SalonServiceModel) mBundle.getSerializable("salonModel");


            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mBinding.rvSubCat.setLayoutManager(layoutManager);
            mBinding.rvSubCat.setItemAnimator(new DefaultItemAnimator());
            mBinding.rvSubCat.setAdapter(mServicesAdapter);
            mServicesAdapter.setListener(this);

            updateUI();


        }

    }


    public void updateUI(){
        ArrayList<Category> modelArrayList =  new ArrayList<>();
        for (int i =0; i< salonServiceModel.getGetSub().size(); i++){


            ArrayList<Services> mServices = new ArrayList<>();
            for (int j =0; j< salonServiceModel.getServices().size(); j++ ){
                if (salonServiceModel.getServices().get(j).getSubCategory().equalsIgnoreCase(salonServiceModel.getGetSub().get(i))){
                    // add here
                    Services modelServices = new Services();

                    SalonServiceModel.Service mServiceSalonModel = salonServiceModel.getServices().get(j);
                    modelServices.setId(mServiceSalonModel.getId());
                    modelServices.setActualPrice(mServiceSalonModel.getActualPrice());
                    modelServices.setCategory(mServiceSalonModel.getCategory());
                    modelServices.setDate(mServiceSalonModel.getDate());
                    modelServices.setDescription(mServiceSalonModel.getDescription());
                    modelServices.setDuration(mServiceSalonModel.getDuration());
                    modelServices.setIsAddedInCart(mServiceSalonModel.getIsAddedInCart());
                    modelServices.setQuantity(mServiceSalonModel.getQuantity());
                    modelServices.setSellingPrice(mServiceSalonModel.getSellingPrice());
                    modelServices.setStatus(mServiceSalonModel.getStatus());
                    modelServices.setSubCategory(mServiceSalonModel.getSubCategory());
                    modelServices.setTitle(mServiceSalonModel.getTitle());
                    modelServices.setTitleSlug(mServiceSalonModel.getTitleSlug());

                    mServices.add(modelServices);
                }
            }
            Category model = new Category(salonServiceModel.getGetSub().get(i), mServices);
            modelArrayList.add(model);
        }
        mServicesAdapter.addItems(modelArrayList);
    }

    @Override
    public void addToCartOnServer(Services serviceModel) {

        serviceModel.setQuantity(serviceModel.getQuantity() + 1);
        serviceModel.setIsAddedInCart(1);
        mServicesAdapter.notifyDataSetChanged();

        viewModel.addToCartOnServer(serviceModel);

        if (isAttached){
            //fragmentListener.addToCart(serviceModel);
        }
    }

    @Override
    public void updateCartQty(Services serviceModel, int qtyToUpdate) {
        if (qtyToUpdate == -1){
            if (serviceModel.getQuantity() == 1){
                serviceModel.setQuantity(0);
                serviceModel.setIsAddedInCart(0);
                mServicesAdapter.notifyDataSetChanged();

                SalonFragment mSalonFragment = (SalonFragment) getParentFragmentManager().findFragmentByTag(SalonFragment.TAG);
                mSalonFragment.updateParentListData(serviceModel, 0, 0, salonModelPosition);


                viewModel.updateCartQty(serviceModel.getId(), serviceModel.getSellingPrice(), ""+qtyToUpdate  );

            } else  if (serviceModel.getQuantity()  > 1){
                int qty = serviceModel.getQuantity() ;
                serviceModel.setQuantity(serviceModel.getQuantity() - 1);
                mServicesAdapter.notifyDataSetChanged();

                SalonFragment mSalonFragment = (SalonFragment) getParentFragmentManager().findFragmentByTag(SalonFragment.TAG);
                mSalonFragment.updateParentListData(serviceModel, qty -1, 1, salonModelPosition);

                viewModel.updateCartQty(serviceModel.getId(), serviceModel.getSellingPrice(), ""+qtyToUpdate  );
            }
        } else {

            int qty = serviceModel.getQuantity() ;

            serviceModel.setQuantity(serviceModel.getQuantity() + 1);
            serviceModel.setIsAddedInCart(1);
            mServicesAdapter.notifyDataSetChanged();


            SalonFragment mSalonFragment = (SalonFragment) getParentFragmentManager().findFragmentByTag(SalonFragment.TAG);
            mSalonFragment.updateParentListData(serviceModel, qty + 1, 1, salonModelPosition);

            viewModel.updateCartQty(serviceModel.getId(), serviceModel.getSellingPrice(), ""+qtyToUpdate  );
        }
    }


    public interface FragmentListener {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showToastMessage(String message) {
        CommonUtils.toastMe(message, getActivity());
    }

    @Override
    public void navigateToCartActivity() {
//        Intent mIntent = CartActivity.newIntent(getActivity());
//        startActivity(mIntent);
    }

    @Override
    public void updateFooterUI() {
        SalonFragment mSalonFragment = (SalonFragment) getParentFragmentManager().findFragmentByTag(SalonFragment.TAG);
        mSalonFragment.setFooterUIFromSubCat();
    }
}
