package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

public class ShoppingOrderListScreenViewModel extends BaseViewModel<ShoppingOrderListScreenNavigator> {
    public ShoppingOrderListScreenViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getShoppingOrderList() {
        String customerId = getDataManager().getUid();
        Map<String, String> map = new HashMap<>();
        map.put("userId", customerId);
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getShoppingOrderDetails(ApiEndPoint.ONLINE_SHOPPING_ORDER_LIST, map)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateUI(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
