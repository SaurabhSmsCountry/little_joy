package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.modals.MyShoppingOrderListResponse;

public interface ShoppingOrderListScreenNavigator {
    void showToastMessage(String message);
    void updateUI(MyShoppingOrderListResponse orderListResponse);
}
