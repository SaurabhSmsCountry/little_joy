package com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection;


import com.littlejoyindia.littlejoyindia.data.model.Address;

import java.util.List;

public interface AddressSelectionNavigator {

    void showToastMessage(String message);

    void setDataToUI(List<Address> data, boolean isAvailable);

    void clcikOnAddNewAddress();
    void clickOnDeleteAddress();

}
