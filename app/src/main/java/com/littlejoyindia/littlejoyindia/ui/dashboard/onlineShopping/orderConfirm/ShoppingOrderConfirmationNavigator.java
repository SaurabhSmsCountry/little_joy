package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm;

import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.modal.OnlineShoppingCartResponse;

public interface ShoppingOrderConfirmationNavigator {
    void showToastMessage(String message);

    void updateUI(OnlineShoppingCartResponse response);
    void updateSalonDealUI(SalonResponse.CartListingServicesResponse response);

    void updateCouponCodeStatus(CouponCodeCheckoutResponse response);
}

