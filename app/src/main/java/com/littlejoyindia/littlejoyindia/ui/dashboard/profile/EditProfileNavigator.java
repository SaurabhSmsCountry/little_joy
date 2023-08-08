package com.littlejoyindia.littlejoyindia.ui.dashboard.profile;

import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.ProfileResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.UpdateNameResponse;

public interface EditProfileNavigator {
    void showToastMessage(String message);
    void onSuccessProfile(ProfileResponse response);
    void onSuccessMyEditProfile(UpdateNameResponse response);
    void onSaveProfile();
}
