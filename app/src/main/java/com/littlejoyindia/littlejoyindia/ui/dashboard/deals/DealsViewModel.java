package com.littlejoyindia.littlejoyindia.ui.dashboard.deals;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class DealsViewModel extends BaseViewModel<DealsNavigator> {
    public DealsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getMyDeals() {
        setIsLoading(true);
        String customerId = getDataManager().getUid();
        getCompositeDisposable().add(getDataManager()
                .getMyDeals(ApiEndPoint.GET_MY_DEALS + customerId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().onSuccessMyDeals(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void getMyDealDetails(String dealId) {
        setIsLoading(true);
        String customerId = getDataManager().getUid();
        getCompositeDisposable().add(getDataManager()
                .getMyDealDetails(ApiEndPoint.GET_MY_DEAL_DETAILS + customerId + "&order_id=" + dealId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().onSuccessMyDealDetails(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void success() {
        getNavigator().success();
    }

    public void cancelled() {
        getNavigator().cancelled();
    }

    public void failed() {
        getNavigator().failed();
    }
}
