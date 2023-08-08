package com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class MyWalletViewModel extends BaseViewModel<MyWalletNavigator> {
    public MyWalletViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getMyWallet() {
        setIsLoading(true);
        String customerId = getDataManager().getUid();
        getCompositeDisposable().add(getDataManager()
                .getMyWallet(ApiEndPoint.GET_MY_WALLET + customerId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().onSuccessMyWallet(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void getMyWalletHistory() {
        setIsLoading(true);
        String customerId = getDataManager().getUid();
        getCompositeDisposable().add(getDataManager()
                .getMyWalletHistory(ApiEndPoint.GET_MY_WALLET_HISTORY + customerId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().onSuccessMyWalletHistory(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
