package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productOverview;

import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;

public interface ProductDescriptionNavigator {
    void showToastMessage(String message);

    void addToCart(String message);

    void updateUI(ProductListResponse.Datum datum);
    void updateCODUI(String msg);
}
