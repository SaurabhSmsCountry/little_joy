package com.littlejoyindia.littlejoyindia.ui.dashboard.orders;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class ShippingOrderDetailsViewModel extends BaseViewModel<ShippingOrderDetailsNavigator> {
    public ShippingOrderDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
