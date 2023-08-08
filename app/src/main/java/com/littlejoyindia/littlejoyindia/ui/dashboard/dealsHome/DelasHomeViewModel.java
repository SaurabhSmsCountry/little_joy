package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentNavigator;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class DelasHomeViewModel extends BaseViewModel<DealsHomeNavigator> {


    public ObservableField<Boolean> isTopBrandsAvailable = new ObservableField<>();
    public ObservableField<Boolean> isPopularDealsAvailable = new ObservableField<>();
    public ObservableField<Boolean> isDealsBrandsAvailable = new ObservableField<>();



    public ObservableField<Boolean> getIsTopBrandsAvailable() {
        return isTopBrandsAvailable;
    }

    public ObservableField<Boolean> getIsPopularDealsAvailable() {
        return isPopularDealsAvailable;
    }

    public ObservableField<Boolean> getIsDealsBrandsAvailable() {
        return isDealsBrandsAvailable;
    }



    public DelasHomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getWaletAmount() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getWalletAmount(new SalonRequest.GetAnyInfoBasedOnUser_Id(
                        getDataManager().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        setIsLoading(false);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }


    public void getTopBrands() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getHomeTopBrands(new SalonRequest.GetSaloonRequest())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        setIsLoading(false);
                        if (response.getData().size() > 0){
                            isTopBrandsAvailable.set(true);
                        } else {
                            isTopBrandsAvailable.set(false);
                        }
                        getNavigator().setTopBrands(response.getData());
                    } else {
                        setIsLoading(false);
                        isTopBrandsAvailable.set(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void getDealsServicesAll(String userCity, String latitude, String longitude) {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getHomeDeals(new SalonRequest.GetDealsHomeBasedOnLocation(
                        userCity, latitude, longitude))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        setIsLoading(false);

                        if (response.getData().getPopular() != null  && response.getData().getPopular().size() > 0){
                            isPopularDealsAvailable.set(true);
                            getNavigator().setPopularServices(response.getData().getPopular());
                        } else {
                            isPopularDealsAvailable.set(false);
                        }

                        if (response.getData().getMerchants() != null && response.getData().getMerchants().size() > 0){
                            isDealsBrandsAvailable.set(true);
                            getNavigator().setDealMerchants(response.getData().getMerchants());
                        } else {
                            isDealsBrandsAvailable.set(false);
                        }


                    } else {
                        setIsLoading(false);
                        isPopularDealsAvailable.set(false);
                        isDealsBrandsAvailable.set(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    public void getDealsBanners() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getDealsBanners(new SalonRequest.GetSaloonRequest())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        setIsLoading(false);
                        getNavigator().setDealBanners(response.getData());
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }
}