package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingNavigator;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

public class OrderDetailsScreenViewModel extends BaseViewModel<OrderDetailsScreenNavigator> {
    public OrderDetailsScreenViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getOrderByOrderId(String orderId) {
        String customerId = getDataManager().getUid();
        Map<String, String> map = new HashMap<>();
        map.put("userId", customerId);
        map.put("order_id", orderId);
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getShoppingOrderDetails(ApiEndPoint.ONLINE_SHOPPING_ORDER_DETAILS, map)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateUI(response.getData().get(0));
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    void getOrderStatusByOrderId(String orderId) {
        String customerId = getDataManager().getUid();
        Map<String, String> map = new HashMap<>();
        map.put("userId", customerId);
        map.put("order_id", orderId);
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getShoppingOrderStatus(ApiEndPoint.ONLINE_SHOPPING_ORDER_STATUS, map)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateTimeLineUI(response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
