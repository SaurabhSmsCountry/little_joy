package com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection;


import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealPaymentSelectionResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingPaymentSelectionResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.PaymentCheckoutResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonPaymentSelectionResponse;

public interface PaymentNavigator {

    void showToastMessage(String message);

    void payWalletClick();

    void payOnlineClick();

    void payHomeClick();

    void clickOnSubmit();

    void submitPaymentMethodForOnlineShopping(OnlineShoppingPaymentSelectionResponse data);
    void submitPaymentMethodForDeal(DealPaymentSelectionResponse data);
    void submitPaymentMethodForSalon(SalonPaymentSelectionResponse data);

    void shoppingFinalCheckout(PaymentCheckoutResponse response);
    void updateCODUI(String msg,boolean isCodAvailable);
}
