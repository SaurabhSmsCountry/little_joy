package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.modals.MyShoppingOrderListResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.timeline.OrderStatusResponse;

public interface OrderDetailsScreenNavigator {
    void showToastMessage(String message);
    void updateUI(MyShoppingOrderListResponse.ShoppingOrderData data);
    void updateTimeLineUI(OrderStatusResponse response);
}
