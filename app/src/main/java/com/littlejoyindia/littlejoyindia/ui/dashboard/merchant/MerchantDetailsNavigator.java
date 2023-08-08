package com.littlejoyindia.littlejoyindia.ui.dashboard.merchant;


import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;

import java.util.List;

public interface MerchantDetailsNavigator {

    void showToastMessage(String message);

    void setDataToUI(List<SalonResponse.MerchantDeatilsByIdResponse.MerchantData> data);

    void navigateToAppointment();

}
