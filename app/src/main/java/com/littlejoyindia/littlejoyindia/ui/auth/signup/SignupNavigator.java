package com.littlejoyindia.littlejoyindia.ui.auth.signup;

public interface SignupNavigator {
    void goBack();



    void onSignUpClick();

    void showLoginUI();

    void showToastMessage(String message);

    void navigateToVerifyOtp();

    void navigateToDashboardActivity();
}
