package com.littlejoyindia.littlejoyindia.ui.dashboard.salon;


import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonServiceModel;

import java.util.List;

public interface SalonNavigator {

    void showToastMessage(String message);

    void setCategoryAndSubCategoryUI(List<SalonServiceModel> data);

    void setUIFooter(Integer qty, Integer total);

    void setCityToUI(List<String> data);
}
