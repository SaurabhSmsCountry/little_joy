package com.littlejoyindia.littlejoyindia.ui.dashboard.map;

import android.content.Intent;

public interface MapUINavigator {

    void onClickSetLocation();

    void onClickConfirm();

    void onClickEdit();

    void onClickSearch();

    void updateCODUI(String msg, boolean isAvailable, Intent returnIntent);

}
