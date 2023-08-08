package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ui.main;

import android.net.Uri;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<ShoppingProductListingNavigator> {
    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getProductList(String id, String sub, String main, String type) {

        Uri uri = Uri.parse(ApiEndPoint.GET_SHOPPING_PRODUCT_BY_CATEGORY_NAME)
                .buildUpon()
                .appendQueryParameter("category", main)
                .appendQueryParameter("subcategory", sub)
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