package com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI;


import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;

import java.util.List;

public interface CartUINavigator {

    void showToastMessage(String message);

    void setDataToUI(List<CartModel> data, int type, boolean aBoolean);

    void onclickAddMoreService();

    void onclickCheckOutSaloon();
}
