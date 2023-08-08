package com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat;

import android.text.Html;
import android.text.TextUtils;

import androidx.databinding.ObservableField;


public class ServicesItemViewModel {

    public Services serviceModel;
    public ObservableField<String> mDuration = new ObservableField<>();
    public ObservableField<String> mActualPrice = new ObservableField<>();
    public ObservableField<String> mSellingPrice = new ObservableField<>();
    public ObservableField<String> mDescription = new ObservableField<>();
    public ObservableField<String> percentage = new ObservableField<>();

    public ModelListener modelListener;

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


    public ServicesItemViewModel(Services serviceModel, ModelListener listener) {
        this.serviceModel = serviceModel;
        this.modelListener = listener;

        mDuration.set(serviceModel.getDuration() + " mins");
        if (serviceModel.getActualPrice() != null && serviceModel.getActualPrice().length() > 0) {
            mActualPrice.set("\u20b9 " + serviceModel.getActualPrice());
        }

        mSellingPrice.set("\u20b9 " + serviceModel.getSellingPrice());
        mDescription.set("" + Html.fromHtml(serviceModel.getDescription(), Html.FROM_HTML_OPTION_USE_CSS_COLORS));

        if (!TextUtils.isEmpty(serviceModel.getActualPrice()) && !TextUtils.isEmpty(serviceModel.getSellingPrice())) {
            int per = (int) (((Double.parseDouble(serviceModel.getActualPrice()) - Double.parseDouble(serviceModel.getSellingPrice())) / Double.parseDouble(serviceModel.getActualPrice())) * 100);
            percentage.set(per + "% off");
        }
    }


    public void onClickAddToCart() {
        if (modelListener != null) {
            modelListener.addToCart(serviceModel);
        }
    }

    public void updateCartByMinus() {
        if (modelListener != null) {
            modelListener.updateCartQty(serviceModel, -1);
        }
    }

    public void updateCartByPlus() {
        if (modelListener != null) {
            modelListener.updateCartQty(serviceModel, 1);
        }
    }


    public interface ModelListener {
        void addToCart(Services serviceModel);

        void updateCartQty(Services serviceModel, int qtyToUpdate);
    }
}
