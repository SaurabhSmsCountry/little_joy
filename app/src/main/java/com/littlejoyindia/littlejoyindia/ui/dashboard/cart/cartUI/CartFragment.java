package com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.databinding.FragmentCartUiBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;



public class CartFragment extends BaseFragment<FragmentCartUiBinding, CartUIViewModel>
        implements CartUINavigator, CartAdapter.CartAdapterListener {


    public static final String TAG = CartFragment.class.getSimpleName();

    @Inject
    CartAdapter mAdapter;

    @Inject
    ViewModelProviderFactory factory;
    private CartUIViewModel viewModel;

    private CartFragment.FragmentListener fragmentListener;
    private boolean isAttached = false;
    private FragmentCartUiBinding mBinding;
    private int cartType = 1;
    private double price = 0;
    private int cartId = 0;

    public static CartFragment newInstance(int cartType) {
        Bundle args = new Bundle();
        args.putInt("cartType", cartType);
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cart_ui;
    }

    @Override
    public CartUIViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this,factory).get(CartUIViewModel.class);
        return viewModel;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            fragmentListener = (CartFragment.FragmentListener) context;
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

        cartType = getArguments().getInt("cartType");
        if(cartType == 1){
            viewModel.getSerViceCartByUserId(0);
            mBinding.btAddMore.setText("Add Services");
        } else {
            viewModel.getDealsCartByUserId(0);
            mBinding.btAddMore.setText("Add Deals");
        }


    }

    @Override
    public void removeCartItem(CartModel serviceModel) {
        if (cartType == 1) {
            viewModel.removeCartItem(serviceModel);
        } else {
            viewModel.removeDealCartItem(serviceModel);
        }
    }

    @Override
    public void updateCartQty(CartModel serviceModel, int i) {
        if (i == -1 &&  serviceModel.getQty() <= 1 ){
            viewModel.removeCartItem(serviceModel);
        } else {
            if (cartType == 1) {
                viewModel.updateCartQty(serviceModel, "" + i);
            } else {
                viewModel.updateDealsCartQty(serviceModel, "" + i);
            }
        }
    }


    public interface FragmentListener {
        void onBackPressed();

        void onclickCheckOutSaloon(int cartId, double price);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void setDataToUI(List<CartModel> data, int type, boolean aBoolean) {


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.rvListData.setLayoutManager(layoutManager);
        mBinding.rvListData.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvListData.setAdapter(mAdapter);
        mAdapter.setListener(this);

        for (int i = 0; i < data.size(); i++){
            data.get(i).setCartType(cartType);
            price += Double.parseDouble(data.get(i).getPrice());
        }


        mAdapter.addItems(data);

        if (data.size() == 0 && type == 1 && aBoolean){
            if (isAttached){
                fragmentListener.onBackPressed();
            }
        }

        if (data.size() > 0) cartId = data.get(0).getCartId();
    }

    @Override
    public void onclickAddMoreService() {
        if (isAttached){
            fragmentListener.onBackPressed();
        }
    }

    @Override
    public void onclickCheckOutSaloon() {
        if (isAttached){
            if (Integer.parseInt(
                    Objects.requireNonNull(viewModel.getCheckoutAmount().get()).replace("\u20b9", "").trim()
            ) < 999 && cartType == 1) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext())
                        .setMessage("Minimum Booking Amount for Home Services is 999/-\nPlease Add More Services.")
                        .setPositiveButton("OK", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                            fragmentListener.onBackPressed();
                        })
                        .setNegativeButton("No", null);
                AlertDialog alert = builder1.create();
                alert.show();
                alert.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
            }
            else fragmentListener.onclickCheckOutSaloon(cartId, price);
        }
    }

}