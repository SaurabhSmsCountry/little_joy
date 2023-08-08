package com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class SalonCatViewModel extends BaseViewModel<SalonCatNavigator> {

    public SalonCatViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void addToCartOnServer(Services serviceModel) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addUpdateToCartSalonService(new SalonRequest.AddUpdateSalonCartServicesRequest(
                        getDataManager().getUid(),
                        ""+serviceModel.getId(), serviceModel.getSellingPrice(), "1"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getNavigator().updateFooterUI();
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }


    public void updateCartQty(int serviceId, String price, String qty) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addUpdateToCartSalonService(new SalonRequest.AddUpdateSalonCartServicesRequest(
                        getDataManager().getUid(),
                        ""+serviceId, price, qty))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getNavigator().updateFooterUI();
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