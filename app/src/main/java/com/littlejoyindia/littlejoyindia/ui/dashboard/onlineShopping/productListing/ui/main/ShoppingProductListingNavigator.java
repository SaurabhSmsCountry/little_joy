package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ui.main;

import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;

import java.util.List;

public interface ShoppingProductListingNavigator {
    void showToastMessage(String message);
    void setProductList(List<ProductListResponse.Datum> datumList);
}
