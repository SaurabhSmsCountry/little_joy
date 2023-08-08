package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingPaymentWebview.ShoppingPaymentNavigator;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentNavigator;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingPaymentSelection;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class ShoppingPaymentViewModel extends BaseViewModel<ShoppingPaymentNavigator> {

    public ShoppingPaymentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void payWalletClick() {
    }

    public void payOnlineClick() {
    }

    public void payHomeClick() {
    }

    public void clickOnSubmit() {
    }
}