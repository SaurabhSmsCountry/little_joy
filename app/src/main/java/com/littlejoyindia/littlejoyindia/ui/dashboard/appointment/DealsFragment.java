package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.Deal;
import com.littlejoyindia.littlejoyindia.data.model.MerchantData;
import com.littlejoyindia.littlejoyindia.databinding.FragmentSalonDealsBinding;
import com.littlejoyindia.littlejoyindia.databinding.FragmentSalonServicesCatBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI.CartAdapter;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

import java.util.ArrayList;

import javax.inject.Inject;

public class DealsFragment extends BaseFragment<FragmentSalonDealsBinding, AppointmentCatViewModel>
        implements DealsNavigator, DealsAdapter.AdapterListener {

    public static final String TAG = DealsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    DealsAdapter mAdapter;

    private AppointmentCatViewModel viewModel;



    private FragmentListener fragmentListener;
    private boolean isAttached = false;
    private FragmentSalonDealsBinding mBinding;

    private MerchantData salonServiceModel = null;
    private int salonModelPosition = 0;

    public static DealsFragment newInstance(int position, MerchantData salonServiceModel) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putSerializable("salonModel", salonServiceModel);
        DealsFragment fragment = new DealsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_salon_deals;
    }

    @Override
    public AppointmentCatViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(AppointmentCatViewModel.class);
        return viewModel;
    }


    @Override
    public void onResume()
    {
        super.onResume();
        //salonServiceModel =  SalonFragment.salonServiceModelList.get(salonModelPosition);
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
            salonServiceModel = (MerchantData) mBundle.getSerializable("salonModel");


            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mBinding.rvSubCat.setLayoutManager(layoutManager);
            mBinding.rvSubCat.setItemAnimator(new DefaultItemAnimator());
            mBinding.rvSubCat.setAdapter(mAdapter);
            mAdapter.setListener(this);
            mAdapter.addItems(salonServiceModel.getDeals());

        }

    }


    @Override
    public void updateCartQty(Deal serviceModel, int qtyToUpdate) {
        if (qtyToUpdate == -1){
            if (serviceModel.getQuantity() == 1){
                serviceModel.setQuantity(0);
                serviceModel.setIsAddedInCart(0);


                if(isAttached){
                    fragmentListener.updateParentListData(serviceModel, 0, 0, salonModelPosition);
                }
                mAdapter.notifyDataSetChanged();
                viewModel.updateCartQty(serviceModel.getId(),  ""+qtyToUpdate  );

            } else  if (serviceModel.getQuantity()  > 1){
                int qty = serviceModel.getQuantity() ;
                serviceModel.setQuantity(serviceModel.getQuantity() - 1);


                if(isAttached){
                    fragmentListener.updateParentListData(serviceModel, qty -1, 1, salonModelPosition);
                }
                mAdapter.notifyDataSetChanged();
                viewModel.updateCartQty(serviceModel.getId(),  ""+qtyToUpdate  );
            }
        } else {

            int qty = serviceModel.getQuantity() ;
            serviceModel.setQuantity(serviceModel.getQuantity() + 1);
            serviceModel.setIsAddedInCart(1);

            if(isAttached){
                fragmentListener.updateParentListData(serviceModel, qty + 1, 1, salonModelPosition);
            }
            mAdapter.notifyDataSetChanged();
            viewModel.updateCartQty(serviceModel.getId(), ""+qtyToUpdate  );
        }
    }


    public interface FragmentListener {
        void updateParentListData(Deal serviceModel,
                                  int qty,
                                  int isAddedToCart,
                                  int salonModelPosition);

        void updateFooterUIFromFragment();

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
    public void updateFooterUI() {
        if (isAttached){
         fragmentListener.updateFooterUIFromFragment();
        }
    }

//    @Override
//    public void navigateToCartActivity() {
////        Intent mIntent = CartActivity.newIntent(getActivity());
////        startActivity(mIntent);
//    }

}
