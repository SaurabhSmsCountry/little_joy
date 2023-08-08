package com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI;

import android.text.Html;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.Services;


public class CartItemViewModel {

    public CartModel serviceModel ;
    public ObservableField<String> mDuration = new ObservableField<>();
    public ObservableField<String> mSellingPrice = new ObservableField<>();
    public ObservableField<String> mDescription = new ObservableField<>();

    public ModelListener modelListener ;

    public ObservableField<String> getmDuration() {
        return mDuration;
    }

    public ObservableField<String> getmSellingPrice() {
        return mSellingPrice;
    }

    public ObservableField<String> getmDescription() {
        return mDescription;
    }


    public CartItemViewModel(CartModel serviceModel, ModelListener listener) {
        this.serviceModel = serviceModel;
        this.modelListener = listener;

        mDuration.set(serviceModel.getDuration() + " mins");
        mSellingPrice.set("\u20b9 " + serviceModel.getPrice());
      //  mDescription.set(""+Html.fromHtml(serviceModel.getDescription(), Html.FROM_HTML_OPTION_USE_CSS_COLORS));

    }


    public void removeCartItem(){
        if(modelListener != null){
            modelListener.removeCartItem(serviceModel);
        }
    }

    public void updateCartByMinus(){
        if(modelListener != null){
              modelListener.updateCartQty(serviceModel, -1 );
        }
    }

    public void updateCartByPlus(){
        if(modelListener != null){
              modelListener.updateCartQty(serviceModel,  1);
        }
    }





    public interface ModelListener {

        void removeCartItem(CartModel serviceModel);

        void updateCartQty(CartModel serviceModel, int i);

    }
}
