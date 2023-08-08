package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;


public class ShoppingProductViewModel extends BaseViewModel<ShoppingProductNavigator> {
    public ShoppingProductViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}



