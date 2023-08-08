package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productOverview;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class ProductDescriptionViewModel extends BaseViewModel<ProductDescriptionNavigator> {
    public ProductDescriptionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void addToCart(CartModel model) {
        setIsLoading(true);
        model.setCustomer_id(getDataManager().getUid());
        getCompositeDisposable().add(getDataManager()
                .addToCartForOnlineSHopping(ApiEndPoint.ADD_TO_CART_ONLINE_SHOPPING, model)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().addToCart(response.getMessage());
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void getProductDetails(String id) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getOnlineShoppingProductsByCategoryId(ApiEndPoint.GET_SHOPPING_PRODUCT_BY_CATEGORY_ID + id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateUI(response.getData().get(0));
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void checkCodIsAvailable(String pinCode) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .checkCODIsAvailable(ApiEndPoint.CHECK_COD_IS_AVAILABLE + pinCode)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage());
                    } else {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
