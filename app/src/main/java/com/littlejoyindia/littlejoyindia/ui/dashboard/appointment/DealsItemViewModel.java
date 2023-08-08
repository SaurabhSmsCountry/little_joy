package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;


import android.util.Log;
import androidx.databinding.ObservableField;
import com.littlejoyindia.littlejoyindia.data.model.Deal;



public class DealsItemViewModel {

    public Deal serviceModel ;
    public ObservableField<String> mDuration = new ObservableField<>();
    public ObservableField<String> mActualPrice = new ObservableField<>();
    public ObservableField<String> mSellingPrice = new ObservableField<>();
    public ObservableField<String> mDescription = new ObservableField<>();

    public ModelListener modelListener ;

    public ObservableField<String> getmDuration() {
        return mDuration;
    }

    public ObservableField<String> getmActualPrice() {
        return mActualPrice;
    }

    public ObservableField<String> getmSellingPrice() {
        return mSellingPrice;
    }

    public ObservableField<String> getmDescription() {
        return mDescription;
    }


    public DealsItemViewModel(Deal serviceModel, ModelListener listener) {
        this.serviceModel = serviceModel;
        this.modelListener = listener;

        Log.e("serviceModel", ""+serviceModel.getIsAddedInCart());
       // mDuration.set(serviceModel.getm() + " mins");
        if (serviceModel.getActualPrice() != null){
            mActualPrice.set("\u20b9 " +serviceModel.getActualPrice());
        }

        mSellingPrice.set("\u20b9 " + serviceModel.getSellingPrice());
       // mDescription.set(""+ Html.fromHtml(serviceModel.getDescription(), Html.FROM_HTML_OPTION_USE_CSS_COLORS));

    }


    public void onClickAddToCart(){
        if(modelListener != null){
            modelListener.addToCart(serviceModel);
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
        void addToCart(Deal serviceModel);

        void updateCartQty(Deal serviceModel, int qtyToUpdate);
    }
}
