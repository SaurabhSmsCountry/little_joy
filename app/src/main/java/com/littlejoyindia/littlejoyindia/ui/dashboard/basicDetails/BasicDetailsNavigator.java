package com.littlejoyindia.littlejoyindia.ui.dashboard.basicDetails;


import com.littlejoyindia.littlejoyindia.data.model.Address;

import java.util.List;

public interface BasicDetailsNavigator {

    void showToastMessage(String message);

    void onClickNext();


    void setCityToUI(List<String> data);

    void updateUI(Address address);

    void updateCODUI(String message, boolean b);
}
