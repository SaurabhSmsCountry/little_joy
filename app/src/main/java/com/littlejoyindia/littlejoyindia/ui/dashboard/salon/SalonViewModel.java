package com.littlejoyindia.littlejoyindia.ui.dashboard.salon;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class SalonViewModel extends BaseViewModel<SalonNavigator> {

    public SalonViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void getCategoryAndSubCategories() {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllSalonServices(new SalonRequest.GetAllSalonServicesRequest(getDataManager().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to HOME ]
                        setIsLoading(false);
                        getNavigator().setCategoryAndSubCategoryUI(response.getData());
                        getNavigator().setUIFooter(response.getQty(), response.getTotal());
                        // [ END Navigate to HOME ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    public void getCategoryAndSubCategoriesForFooterOnly() {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllSalonServices(new SalonRequest.GetAllSalonServicesRequest(getDataManager().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to HOME ]
                        setIsLoading(false);
                        //getNavigator().setCategoryAndSubCategoryUI(response.getData());
                        getNavigator().setUIFooter(response.getQty(), response.getTotal());
                        // [ END Navigate to HOME ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }


    public void getSaloonServiceCity() {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getSalonServiceCity(new SalonRequest.GetSaloonRequest())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to HOME ]
                        setIsLoading(false);
                        getNavigator().setCityToUI(response.getData());
//                        getNavigator().setUIFooter(response.getQty(), response.getTotal());
                        // [ END Navigate to HOME ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }


}