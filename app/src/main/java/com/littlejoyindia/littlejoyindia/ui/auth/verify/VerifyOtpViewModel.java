package com.littlejoyindia.littlejoyindia.ui.auth.verify;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;


public class VerifyOtpViewModel extends BaseViewModel<VerifyOtpNavigator> {


    public ObservableField<String> getShowTextWithMobile() {
        return showTextWithMobile;
    }

    private ObservableField<String> showTextWithMobile = new ObservableField<>();


    public void showRegisterUI(){
        getNavigator().goBack();
    }

    public VerifyOtpViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void onVerifySuccess(){
        getNavigator().navigateToDashboardActivity();
    }


    public void onVerifyClick(){
        getNavigator().onVerifyClicked();
    }

    public void onResendClick(){
        getNavigator().onResendOtpClicked();
    }

    public void verifyOtpOnServer(String otp) {
        getNavigator().showRegisterUI();
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .verifyUserOtp(new AuthRequest.ServerVerifyOtpRequest(getDataManager().getMobileNumber(),
                        otp,
                        "1212",
                        "dsds",
                        "android"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        // [ START Navigate to HOME ]
                        if(response.getData().get(0).getFullName() != null &&
                                response.getData().get(0).getFullName().length() > 0){
                            setIsLoading(false);
                            getDataManager().setUserInfoToPrefs(response.getData().get(0));
                            getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);

                            getNavigator().navigateToDashboardActivity();
                        } else {
                            setIsLoading(false);
                            getNavigator().showRegisterUI();
                        }

                        // [ END Navigate to HOME ]
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void requestOtpAgain() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .resendUserOtp(new AuthRequest.ServerResendOtpRequest(getDataManager().getUid()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public void setupInitialUI() {
      //  Log.e("getMobileNumber()", getDataManager().getMobileNumber());

       // showTextWithMobile.set("+91" + getDataManager().getMobileNumber());
    }


    public void onTextChanged1(CharSequence s, int start, int before, int count) {
        if(s.length()>0) getNavigator().setDigitLocation(1);
        else getNavigator().setDigitLocation(0);
    }

    public void onTextChanged2(CharSequence s, int start, int before, int count) {
        if(s.length()>0) getNavigator().setDigitLocation(2);
        else getNavigator().setDigitLocation(1);
    }

    public void onTextChanged3(CharSequence s, int start, int before, int count) {
        if(s.length()>0) getNavigator().setDigitLocation(3);
        else getNavigator().setDigitLocation(2);
    }

    public void onTextChanged4(CharSequence s, int start, int before, int count) {
        if(s.length()>0) getNavigator().setDigitLocation(4);
        else getNavigator().setDigitLocation(3);
    }
}
