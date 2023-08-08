package com.littlejoyindia.littlejoyindia.ui.dashboard.merchant;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentNavigator;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class MerchantViewModel extends BaseViewModel<MerchantDetailsNavigator> {



    public MerchantViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getWaletAmount() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getWalletAmount(new SalonRequest.GetAnyInfoBasedOnUser_Id(
                        getDataManager().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }


    public void navigateToAppointment(){
        getNavigator().navigateToAppointment();

    }


    public void getMerchantData(String merchantId, String lat, String lng) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getMerchantDetailsById(new SalonRequest.GetMerchantDetailsById(
                        merchantId, lat, lng))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getNavigator().setDataToUI(response.getData());
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }
}