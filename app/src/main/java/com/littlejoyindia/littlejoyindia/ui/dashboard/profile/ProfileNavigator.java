package com.littlejoyindia.littlejoyindia.ui.dashboard.profile;

import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.ProfileResponse;

public interface ProfileNavigator {
    void showToastMessage(String message);
    void onSuccessProfile(ProfileResponse response);
    void onEditProfile();
}
