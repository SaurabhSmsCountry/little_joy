package com.littlejoyindia.littlejoyindia.ui.dashboard.map;


import android.content.Intent;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class MapUIViewModel extends BaseViewModel<MapUINavigator> {


    public MapUIViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void onClickSetLocation(){
        getNavigator().onClickSetLocation();
    }

    public void onClickConfirm(){
        getNavigator().onClickConfirm();
    }

    public void onClickEdit(){
        getNavigator().onClickEdit();
    }

    public void onClickSearch(){
        getNavigator().onClickSearch();
    }

    void checkCodIsAvailable(String pinCode, Intent returnIntent) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .checkCODIsAvailable(ApiEndPoint.CHECK_COD_IS_AVAILABLE + pinCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage(),true,returnIntent);
                    } else {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage(),false,returnIntent);
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }


}