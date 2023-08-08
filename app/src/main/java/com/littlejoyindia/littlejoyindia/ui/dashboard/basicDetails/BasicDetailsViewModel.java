package com.littlejoyindia.littlejoyindia.ui.dashboard.basicDetails;

import android.content.Intent;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.User;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthRequest;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentNavigator;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class BasicDetailsViewModel extends BaseViewModel<BasicDetailsNavigator> {

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();

    public BasicDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        name.set(dataManager.getFullName());
        mobile.set(dataManager.getMobileNumber());
    }

    public void onClickNext() {
        getNavigator().onClickNext();
    }


    public void getServerCityForSaloon() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getSalonServiceCity(new SalonRequest.GetSaloonRequest())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        // [ START Navigate to HOME ]
                        setIsLoading(false);
                        getNavigator().setCityToUI(response.getData());
                        //  getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
                        // [ END Navigate to HOME ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void addAddressOnServer(String houseNo,
                                   String pincode,
                                   String landmark,
                                   String address,
                                   String userState,
                                   String userDistrict, boolean isCodAvailable) {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addAddressByUser(new SalonRequest.AddAddressOnServer(
                        Integer.parseInt(getDataManager().getUid()), houseNo, "home", landmark, address, 3232.3232, 322.3232, Integer.parseInt(pincode), userState, userDistrict
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getNavigator().updateUI(response.getAddress());
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void checkCodIsAvailable(String pinCode) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .checkCODIsAvailable(ApiEndPoint.CHECK_COD_IS_AVAILABLE + pinCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage(),true);
                    } else {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage(),false);
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

}