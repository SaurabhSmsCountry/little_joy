package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsCity;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.basicDetails.BasicDetailsNavigator;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class DealsCityViewModel extends BaseViewModel<DealsCityNavigator> {

    public DealsCityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void getDealsCityFromServer() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getSaloonDealsCity(new SalonRequest.GetSaloonRequest())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to HOME ]
                        setIsLoading(false);
                        getNavigator().setCityToUI(response.getData());
                        // [ END Navigate to HOME ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void onCurrentLocationSelect(){
        getNavigator().onCurrentLocationSelect();
    }


}