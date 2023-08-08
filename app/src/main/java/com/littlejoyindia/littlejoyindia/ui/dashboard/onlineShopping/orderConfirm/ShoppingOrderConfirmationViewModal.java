package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

public class ShoppingOrderConfirmationViewModal extends BaseViewModel<ShoppingOrderConfirmationNavigator> {
    public ShoppingOrderConfirmationViewModal(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

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

    void getDealCart() {
        String customerId = getDataManager().getUid();
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllDealsCartItems(new SalonRequest.GetAnyInfoBasedOnUserId(customerId))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateSalonDealUI(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void getSalonCart() {
        String customerId = getDataManager().getUid();
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllSalonServiceCartItems(new SalonRequest.GetAnyInfoBasedOnUserId(customerId))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateSalonDealUI(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void checkCoupon(String code) {
        String customerId = getDataManager().getUid();
        setIsLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("userId", customerId);
        map.put("coupon", code);
        getCompositeDisposable().add(getDataManager()
                .checkCouponCode(ApiEndPoint.ONLINE_SHOPPING_COUPON_CHECK, map)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateCouponCodeStatus(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
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

    void removeItemFromCartSalon(SalonRequest.RemoveCartItemServicesRequest request) {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .removeCartItemSalonService(request)
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

    void removeItemFromCartDeal(SalonRequest.RemoveDealCartItemRequest request) {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .removeCartItemDeal(request)
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

    void addToAndRemoveCartDeal(CartModel model) {
        setIsLoading(true);
        model.setCustomer_id(getDataManager().getUid());
        getCompositeDisposable().add(getDataManager()
                .addUpdateToCartDeals(new SalonRequest.AddUpdateSalonDealsRequest(
                        getDataManager().getUid(),
                        ""+model.getPro_id(), String.valueOf(model.getQty())))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getDealCart();
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void addToAndRemoveCartSalon(CartModel model) {
        setIsLoading(true);
        model.setCustomer_id(getDataManager().getUid());
        getCompositeDisposable().add(getDataManager()
                .addUpdateToCartSalonService(new SalonRequest.AddUpdateSalonCartServicesRequest(
                        getDataManager().getUid(),
                        ""+model.getSerId(),model.getPrice(), String.valueOf(model.getQty())
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getSalonCart();
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
