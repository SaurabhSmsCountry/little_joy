package com.littlejoyindia.littlejoyindia.ui.dashboard.orders;

import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.orderModel.OrderResponse;

public interface OrderNavigator {
    void showToastMessage(String message);
    void onSuccessMyOrders(OrderResponse response);
    void success();
    void refunded();
    void failed();
}
