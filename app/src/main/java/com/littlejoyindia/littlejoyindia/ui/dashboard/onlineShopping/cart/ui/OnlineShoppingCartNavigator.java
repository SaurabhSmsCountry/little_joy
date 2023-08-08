package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.modal.OnlineShoppingCartResponse;

public interface OnlineShoppingCartNavigator {
    void showToastMessage(String message);
    void updateUI(OnlineShoppingCartResponse cartList);
    void onclickAddMoreService();
    void onclickCheckOutSaloon();
}
