package com.littlejoyindia.littlejoyindia.ui.auth.login;


import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.User;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthRequest;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class LoginViewModel  extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onSignInClick(){
        getNavigator().onSignInClick();
    }



    public void signInOnServer(
            String loginType,
            String userMobile) {


        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doLogin(new AuthRequest.ServerLoginRequest(userMobile))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if(response.getSuccess()){
                        if (response.getData() != null && response.getData().size() > 0){
                            User user = response.getData().get(0);
                            getDataManager().setUserInfoToPrefs(user);
                            // [ Navigate to Verify OTP ]
                            setIsLoading(false);
                            getNavigator().navigateToVerifyOtp();

                        } else {
                            setIsLoading(false);
                            getNavigator().showToastMessage(response.getMessage());
                        }
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
