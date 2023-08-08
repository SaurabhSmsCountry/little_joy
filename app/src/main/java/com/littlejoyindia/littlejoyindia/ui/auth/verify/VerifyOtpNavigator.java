package com.littlejoyindia.littlejoyindia.ui.auth.verify;

public interface VerifyOtpNavigator {
    void goBack();

    void setDigitLocation(int location);

    void navigateToDashboardActivity();

    void onVerifyClicked();

    void onResendOtpClicked();

    void showToastMessage(String message);

    void showRegisterUI();
}
