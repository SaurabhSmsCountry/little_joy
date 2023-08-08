package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class OnlineShoppingCartViewMOdel extends BaseViewModel<OnlineShoppingCartNavigator> {
    public OnlineShoppingCartViewMOdel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public ObservableField<Boolean> getIsCheckOutAvailable() {
        return isCheckOutAvailable;
    }

    public ObservableField<String> getCheckoutAmount() {
        return checkoutAmount;
    }

    public ObservableField<Boolean> isCheckOutAvailable = new ObservableField<>();
    public ObservableField<String> checkoutAmount = new ObservableField<>();

    void getCart() {
        String customerId = getDataManager().getUid();
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getOnlineShoppingCart(ApiEndPoint.GET_CART_ONLINE_SHOPPING + customerId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateUI(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void onclickAddMoreService() {
        getNavigator().onclickAddMoreService();
    }

    public void onclickCheckOutSaloon() {
        getNavigator().onclickCheckOutSaloon();
    }

    void removeItemFromCart(SalonRequest.RemoveDealCartItemRequest request) {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .removeItemCartForOnlineSHopping(ApiEndPoint.REMOVE_CART_ONLINE_SHOPPING, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getCart();
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void addToAndRemoveCart(CartModel model) {
        setIsLoading(true);
        model.setCustomer_id(getDataManager().getUid());
        getCompositeDisposable().add(getDataManager()
                .addToCartForOnlineSHopping(ApiEndPoint.ADD_TO_CART_ONLINE_SHOPPING, model)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getCart();
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
