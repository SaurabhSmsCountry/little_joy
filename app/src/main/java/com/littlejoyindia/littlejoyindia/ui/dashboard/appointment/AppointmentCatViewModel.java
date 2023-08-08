package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class AppointmentCatViewModel extends BaseViewModel<DealsNavigator> {



    public AppointmentCatViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void getDealsOnUser(String merchantId) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getMerchantDealsByMerchant(new SalonRequest.GetMerchantDealsByMerchant( merchantId,
                        getDataManager().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                       // getNavigator().setCategoryUI(response.getData());
                      //  getNavigator().setUIFooter(response.getQty(), response.getTotal());
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }


//    public void removeCartItem(int dealId) {
//        setIsLoading(true);
//        getCompositeDisposable().add(getDataManager()
//                .removeCartItemSalonService(new SalonRequest.RemoveCartItemDealsRequest(  ""+dealId,
//                        ""+serviceModel.getSerId()))
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(response -> {
//                    if(response.getSuccess()){
//                        // [ START Navigate to CART ]
//                        setIsLoading(false);
//                        getSerViceCartByUserId(1);
//                        // [ END Navigate to CART ]
//                    } else {
//                        setIsLoading(false);
//                        getNavigator().showToastMessage(response.getMessage());
//                    }
//                }, throwable -> {
//                    setIsLoading(false);
//                }));
//
//    }

    public void updateCartQty(int serviceId,  String qty) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addUpdateToCartDeals(new SalonRequest.AddUpdateSalonDealsRequest(
                        getDataManager().getUid(),
                        ""+serviceId, qty))
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