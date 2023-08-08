package com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat;

import android.graphics.Paint;

import androidx.annotation.NonNull;
import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.littlejoyindia.littlejoyindia.databinding.ServicesViewBinding;


public class ServicesViewHolder extends ChildViewHolder
        implements ServicesItemViewModel.ModelListener {


    ServicesViewBinding blogViewBinding ;
    ServicesItemViewModel servicesItemViewModel;
    ServicesAdapter.ServiceAdapterListener mListener ;

    public ServicesViewHolder(ServicesViewBinding blogViewBinding , ServicesAdapter.ServiceAdapterListener mListener) {
        super(blogViewBinding.getRoot());
        this.blogViewBinding = blogViewBinding;
        this.mListener = mListener;
    }



    public void bind(@NonNull Services ingredient) {
        servicesItemViewModel = new ServicesItemViewModel(ingredient, this);
        blogViewBinding.setViewModel(servicesItemViewModel);
        blogViewBinding.executePendingBindings();


        if (ingredient.getActualPrice() != null && ingredient.getActualPrice().length() > 0){
            blogViewBinding.tvActualPrice.setPaintFlags(blogViewBinding.tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }

    @Override
    public void addToCart(Services serviceModel) {
        if(mListener != null){
            mListener.addToCartOnServer(serviceModel);
        }
    }

    @Override
    public void updateCartQty(Services serviceModel, int qtyToUpdate) {
        if(mListener != null){
            mListener.updateCartQty(serviceModel, qtyToUpdate);
        }
    }
}