package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class OnlineShoppingViewModel extends BaseViewModel<OnlineShoppingNavigator> {
    public OnlineShoppingViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getCategory() {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getOnlineShoppingCategory(ApiEndPoint.GET_SHOPPING_PRODUCT_CATEGORY)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().setCategoryAndSubCategoryUI(response.getData());
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    void getProductList(String type) {

        Uri uri = Uri.parse(ApiEndPoint.SHOPPING_HOME_PRODUCTS)
                .buildUpon()
                .appendQueryParameter("filter", type)
                .build();

        String myUrl = uri.toString();

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getOnlineShoppingProductsByCategoryId(myUrl)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().setProductList(response.getData());
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

}