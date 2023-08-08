package com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection;

import androidx.databinding.ObservableField;
import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealOrderCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonOrderCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.ThankYouCheckout;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class PaymentViewModel extends BaseViewModel<PaymentNavigator> {

    public ObservableField<String> getmTotalAmount() {
        return mTotalAmount;
    }

    public ObservableField<String> mTotalAmount = new ObservableField<>();

    public PaymentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void payWalletClick() {
        getNavigator().payWalletClick();
    }

    public void payOnlineClick() {
        getNavigator().payOnlineClick();
    }

    public void payHomeClick() {
        getNavigator().payHomeClick();
    }

    public void clickOnSubmit() {
        getNavigator().clickOnSubmit();
    }


    public void getWaletAmount() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getWalletAmount(new SalonRequest.GetAnyInfoBasedOnUser_Id(
                        getDataManager().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        // [ START Navigate to CART ]
                        setIsLoading(false);
                        if (response.getData().size() > 0)
                            mTotalAmount.set("\u20b9 " + response.getData().get(0).getAmount());
                        // [ END Navigate to CART ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

    }

    void submitPaymentMethod(OnlineShoppingPaymentSelection request) {
        setIsLoading(true);
        request.setCustomerId(Integer.valueOf(getDataManager().getUid()));
        getCompositeDisposable().add(getDataManager()
                .onlineShoppingPaymentMethodSubmitted(ApiEndPoint.PAYMENT_METHOD_SUBMITTED_ONLINE_SHOPPING, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().submitPaymentMethodForOnlineShopping(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void submitDealPaymentMethod(DealPaymentSelection request) {
        setIsLoading(true);
        request.setCustomerId(Integer.valueOf(getDataManager().getUid()));
        getCompositeDisposable().add(getDataManager()
                .dealPaymentMethodSubmitted(ApiEndPoint.PAYMENT_METHOD_SUBMITTED_DEAL, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().submitPaymentMethodForDeal(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void submitSalonPaymentMethod(SalonPaymentSelection request) {
        setIsLoading(true);
        request.setCustomerId(Integer.valueOf(getDataManager().getUid()));
        getCompositeDisposable().add(getDataManager()
                .salonPaymentMethodSubmitted(ApiEndPoint.PAYMENT_METHOD_SUBMITTED_SALON, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().submitPaymentMethodForSalon(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void onlineShoppingProductCheckOut(OnlineShoppingCheckout request) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .onlineShoppingCheckout(ApiEndPoint.ONLINE_SHOPPING_CHECKOUT, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().shoppingFinalCheckout(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void dealProductCheckOut(DealOrderCheckout request) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getDealOrderCheckout(ApiEndPoint.DEAL_PAYMENT_CHECKOUT, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().shoppingFinalCheckout(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void salonProductCheckOut(SalonOrderCheckout request) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getSalonOrderCheckout(ApiEndPoint.SALON_PAYMENT_CHECKOUT, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().shoppingFinalCheckout(response);
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
                        checkCodIsAvailableForUser();
                        getNavigator().updateCODUI(response.getMessage(),true);
                    } else {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage(),false);
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void checkCodIsAvailableForUser() {
        String customerId = getDataManager().getUid();
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .checkCODIsAvailable(ApiEndPoint.CHECK_COD_IS_AVAILABLE_FOR_A_USER + customerId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage(),true);
                    } else {
                        setIsLoading(false);
                        getNavigator().updateCODUI(response.getMessage(),false);
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void thankYouDealService(ThankYouCheckout request) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .salonThankYouCheckout(ApiEndPoint.DEAL_THANK_YOU, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void thankYouSalonService(ThankYouCheckout request) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .salonThankYouCheckout(ApiEndPoint.SALON_THANK_YOU, request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}