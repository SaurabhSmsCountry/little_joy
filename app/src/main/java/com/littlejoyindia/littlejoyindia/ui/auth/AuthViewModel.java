package com.littlejoyindia.littlejoyindia.ui.auth;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;


public class AuthViewModel extends BaseViewModel<AuthNavigator> {


    // Setting Observeables
    private ObservableField<Boolean> isSignShowing = new ObservableField<>(true);

    private ObservableField<Boolean> isSignUpShowing = new ObservableField<>(false);

    private ObservableField<Boolean> isAuthControllerShown = new ObservableField<>(true);

    public AuthViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onSignInClick(){
        getNavigator().showCurrentUI(0);
        isSignShowing.set(true);
        isSignUpShowing.set(false);
    }

    public void onSignUpClick(){
        getNavigator().showCurrentUI(1);
        isSignUpShowing.set(true);
        isSignShowing.set(false);
    }

    public void hideAuthController(boolean isVisibile){
        isAuthControllerShown.set(isVisibile);
    }

    // Getter and Setters
    public ObservableField<Boolean> getIsSignShowing() {
        return isSignShowing;
    }

    public ObservableField<Boolean> getIsSignUpShowing() {
        return isSignUpShowing;
    }

    public ObservableField<Boolean> getIsAuthControllerShown() { return isAuthControllerShown; }
}
