package com.littlejoyindia.littlejoyindia.ui.auth.signup;


import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.User;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class SignUpViewModel  extends BaseViewModel<SignupNavigator> {

    public SignUpViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onSignUpClick(){
        getNavigator().onSignUpClick();
    }

    public void showLoginUI(){
        getNavigator().showLoginUI();
    }


    public void signUpOnServer(String fullName,
                               String emailId,
                               String gender,
                               String referralCode) {
                setIsLoading(true);
                getCompositeDisposable().add(getDataManager()
                        .doUserRegister(new AuthRequest.ServerCreateProfileRequest( getDataManager().getMobileNumber(),
                                fullName, emailId, gender, referralCode))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if(response.getSuccess()){
                                User user = response.getData().get(0);
                                getDataManager().setUserInfoToPrefs(user);
                                getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
                                    // [ START Navigate to HOME ]
                                    setIsLoading(false);
                                  //  getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
                                    getNavigator().navigateToDashboardActivity();
                                    // [ END Navigate to HOME ]
                            } else {
                                setIsLoading(false);
                                getNavigator().showToastMessage(response.getMessage());
                            }
                        }, throwable -> {
                            setIsLoading(false);
                        }));
        }
}
