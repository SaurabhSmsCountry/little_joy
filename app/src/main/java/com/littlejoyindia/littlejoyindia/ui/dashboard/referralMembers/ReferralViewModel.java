package com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

import java.util.Map;

public class ReferralViewModel extends BaseViewModel<ReferralNavigator> {
    public ReferralViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getReferralMembers() {
        setIsLoading(true);
        String customerId = getDataManager().getUid();
        getCompositeDisposable().add(getDataManager()
                .getReferralMembers(ApiEndPoint.GET_REFERRAL_MEMBER + customerId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().onSuccessReferralMembers(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}