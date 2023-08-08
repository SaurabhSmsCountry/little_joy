package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping;


import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.OnlineShoppingResponse;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonServiceModel;

import java.util.List;

public interface OnlineShoppingNavigator {

    void showToastMessage(String message);

    void setCategoryAndSubCategoryUI(List<OnlineShoppingResponse.Datum> dataList);

    void onItemClickListener(OnlineShoppingResponse.Datum data);
    void onProductItemClickListener(ProductListResponse.Datum data);

    void onItemSubCategoryClickListener(OnlineShoppingResponse.SubCategory data, OnlineShoppingResponse.Datum datum);

    void setProductList(List<ProductListResponse.Datum> data);
}
