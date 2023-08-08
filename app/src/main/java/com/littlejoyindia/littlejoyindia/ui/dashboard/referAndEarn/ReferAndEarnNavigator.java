package com.littlejoyindia.littlejoyindia.ui.dashboard.referAndEarn;

import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.ProfileResponse;

public interface ReferAndEarnNavigator {
    void showToastMessage(String message);
    void onSuccessProfile(ProfileResponse response);
    void onShare();
}
