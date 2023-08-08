package com.littlejoyindia.littlejoyindia.ui.dashboard.profile;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

public class EditProfileViewModel extends BaseViewModel<EditProfileNavigator> {
    public EditProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void updateName(String name) {
        setIsLoading(true);
        String customerId = getDataManager().getUid();
        Map<String, String> map = new HashMap<>();
        map.put("user_id", customerId);
        map.put("profile_name", name);
        getCompositeDisposable().add(getDataManager()
                .updateUserName(ApiEndPoint.UPDATE_PROFILE_NAME, map)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().onSuccessMyEditProfile(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
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

    public void onSaveProfile() {
        getNavigator().onSaveProfile();
    }
}
