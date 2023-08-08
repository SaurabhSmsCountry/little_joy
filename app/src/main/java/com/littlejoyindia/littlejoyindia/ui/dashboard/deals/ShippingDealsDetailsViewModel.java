package com.littlejoyindia.littlejoyindia.ui.dashboard.deals;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class ShippingDealsDetailsViewModel extends BaseViewModel<ShippingDealsDetailsNavigator> {
    public ShippingDealsDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
