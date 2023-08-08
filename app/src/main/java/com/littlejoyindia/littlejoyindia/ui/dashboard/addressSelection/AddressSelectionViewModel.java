package com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.CartNavigator;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class AddressSelectionViewModel extends BaseViewModel<AddressSelectionNavigator> {

    boolean isAvailable = false;

    public AddressSelectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getSavedAddressByUser() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllSavedAddress(new SalonRequest.GetAnyInfoBasedOnUser_Id(
                        getDataManager().getUid()
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getNavigator().setDataToUI(response.getData(),isAvailable);
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void deleteAddressFromServer(String apiEndPoint) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deleteAddressFromServer(apiEndPoint)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getSavedAddressByUser();
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    public void clcikOnAddNewAddress() {
        getNavigator().clcikOnAddNewAddress();
    }

    /*public void addAddressOnServer(String houseNo,
                                   String pincode,
                                   String streetNo,
                                   String landmark,
                                   String userState,
                                   String userDistrict) {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addAddressByUser(new SalonRequest.AddAddressOnServer(
                        getDataManager().getUid(), houseNo, streetNo, landmark, pincode, userDistrict, userState
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getSavedAddressByUser();
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }*/

    public void addAddressOnServer(String houseNo,
                                   String pincode,
                                   String landmark,
                                   String address,
                                   String userState,
                                   String userDistrict, boolean isCodAvailable) {
        isAvailable = isCodAvailable;

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
                        getSavedAddressByUser();
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