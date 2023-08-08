package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsCity;


import java.util.List;

public interface DealsCityNavigator {

    void showToastMessage(String message);

    void setCityToUI(List<String> data);

    void onCurrentLocationSelect();

}
