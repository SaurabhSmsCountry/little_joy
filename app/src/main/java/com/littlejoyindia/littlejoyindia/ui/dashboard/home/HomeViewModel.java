package com.littlejoyindia.littlejoyindia.ui.dashboard.home;



import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {




    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onClickSalonAtHome(){
        getNavigator().navigateToSalonServices();
    }

    public void onClickOnlineShopping(){
        getNavigator().navigateToOnlineShoppingServices();
    }

    public void onClickDeals(){
        getNavigator().navigateToSalonDeals();
    }
//
//    public void onClickAccountDetails(){
//        getNavigator().onClickAccountDetails();
//    }


}