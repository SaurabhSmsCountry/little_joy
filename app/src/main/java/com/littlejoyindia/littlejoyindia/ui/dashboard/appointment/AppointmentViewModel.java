package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.review.MerchantReviewNavigator;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class AppointmentViewModel extends BaseViewModel<AppointmentNavigator> {



    public AppointmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void getDealsOnUser(String merchantId, int isFooter) {
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
                        if (isFooter == 0){
                            getNavigator().setCategoryUI(response.getData());
                        }
                        getNavigator().setUIFooter(response.getQty(), response.getTotal());
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