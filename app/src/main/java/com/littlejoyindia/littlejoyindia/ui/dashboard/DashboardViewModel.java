package com.littlejoyindia.littlejoyindia.ui.dashboard;


import android.util.Log;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.AppConstants;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class DashboardViewModel extends BaseViewModel<DashboardNavigator> {

    public DashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setDefaults() {
        AppConstants.USER_TOKEN = getDataManager().getUserToken();
        Log.e("TOKEN", "Token : "+AppConstants.USER_TOKEN );

        if(getDataManager().getUserAddressAvailable()){
            getNavigator().showHomeFragment();
        } else {
            getNavigator().showHomeFragment();
        }
    }

    public void onClickHome() {
        getNavigator().onClickHome();
    }

    public void onClickReferAndEarn() {
        getNavigator().onClickReferAndEarn();
    }

    public void onClickWallet() {
        getNavigator().onClickWallet();
    }

    public void onClickAccount() {
        getNavigator().onClickAccount();
    }
}