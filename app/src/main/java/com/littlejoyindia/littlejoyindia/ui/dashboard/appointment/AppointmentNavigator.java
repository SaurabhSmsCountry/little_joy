package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;


import com.littlejoyindia.littlejoyindia.data.model.MerchantData;

import java.util.List;

public interface AppointmentNavigator {

    void showToastMessage(String message);

    void setCategoryUI(List<MerchantData> data);

    void setUIFooter(Integer qty, Integer total);
}
