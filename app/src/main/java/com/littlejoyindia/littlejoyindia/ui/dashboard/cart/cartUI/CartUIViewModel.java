package com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.CartNavigator;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.Services;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class CartUIViewModel extends BaseViewModel<CartUINavigator> {


    public ObservableField<Boolean> getIsCheckOutAvailable() {
        return isCheckOutAvailable;
    }

    public ObservableField<String> getCheckoutAmount() {
        return checkoutAmount;
    }

    public ObservableField<Boolean> isCheckOutAvailable = new ObservableField<>();
    public ObservableField<String> checkoutAmount = new ObservableField<>();



    public CartUIViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void getSerViceCartByUserId(int type) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllSalonServiceCartItems(new SalonRequest.GetAnyInfoBasedOnUserId(
                        getDataManager().getUid()
                        ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        if (response.getTotal() > 0){
                            isCheckOutAvailable.set(true);
                            checkoutAmount.set("\u20b9 "+response.getTotal());
                            getNavigator().setDataToUI(response.getData() , type, false);
                        } else {
                            isCheckOutAvailable.set(false);
                            checkoutAmount.set("\u20b9 "+response.getTotal());
                            getNavigator().setDataToUI(response.getData() , type, true);
                        }

                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    public void getDealsCartByUserId(int type) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllDealsCartItems(new SalonRequest.GetAnyInfoBasedOnUserId(
                        getDataManager().getUid()
                ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        if (response.getTotal() > 0){
                            isCheckOutAvailable.set(true);
                            checkoutAmount.set("\u20b9 "+response.getTotal());
                            getNavigator().setDataToUI(response.getData() , type, false);
                        } else {
                            isCheckOutAvailable.set(false);
                            checkoutAmount.set("\u20b9 "+response.getTotal());
                            getNavigator().setDataToUI(response.getData() , type, true);
                        }

                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }


    public void updateDealsCartQty(CartModel serviceModel, String qty) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addUpdateToCartDeals(new SalonRequest.AddUpdateSalonDealsRequest(
                        getDataManager().getUid(),
                        ""+serviceModel.getPro_id(),  qty))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getDealsCartByUserId(1);
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    public void updateCartQty(CartModel serviceModel, String qty) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addUpdateToCartSalonService(new SalonRequest.AddUpdateSalonCartServicesRequest(
                        getDataManager().getUid(),
                        ""+serviceModel.getSerId(), serviceModel.getPrice(), qty))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getSerViceCartByUserId(1);
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    public void removeCartItem(CartModel serviceModel) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .removeCartItemSalonService(new SalonRequest.RemoveCartItemServicesRequest(  ""+serviceModel.getCartId(),
                        ""+serviceModel.getSerId()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getSerViceCartByUserId(1);
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    public void removeDealCartItem(CartModel serviceModel) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .removeCartItemDeal(new SalonRequest.RemoveDealCartItemRequest(  ""+serviceModel.getCartId(),
                        ""+serviceModel.getPro_id()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        getDealsCartByUserId(1);
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }


    public void onclickAddMoreService(){
        getNavigator().onclickAddMoreService();
    }

    public void onclickCheckOutSaloon(){
        getNavigator().onclickCheckOutSaloon();
    }

}