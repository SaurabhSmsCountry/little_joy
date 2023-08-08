package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.addReview;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui.OnlineShoppingCartNavigator;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

import java.util.Map;

public class AddProductReviewViewModel extends BaseViewModel<AddProductReviewNavigator> {
    public AddProductReviewViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void submitReview(int position, Map<String, String> map) {
        setIsLoading(true);
        String customerId = getDataManager().getUid();
        map.put("cust_id",customerId);
        getCompositeDisposable().add(getDataManager()
                .addProductReview(ApiEndPoint.ADD_PRODUCT_REVIEW, map)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response.getSuccess()) {
                        setIsLoading(false);
                        getNavigator().updateUI(position, response);
                    } else {
                        setIsLoading(false);
                        getNavigator().showToastMessage(response.getMessage());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
}
