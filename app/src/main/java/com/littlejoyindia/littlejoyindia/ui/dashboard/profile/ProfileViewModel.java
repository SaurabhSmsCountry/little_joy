package com.littlejoyindia.littlejoyindia.ui.dashboard.profile;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {
    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getProfile() {
        setIsLoading(true);
        String customerId = getDataManager().getUid();
        getCompositeDisposable().add(getDataManager()
                .getMyProfile(ApiEndPoint.GET_MY_PROFILE + customerId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().onSuccessProfile(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void onEditProfile() {
        getNavigator().onEditProfile();
    }
}
