package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.ui;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.data.remote.ApiEndPoint;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class ShoppingProductReviewViewModel extends BaseViewModel<ShoppingProductReviewNavigator> {
    public ShoppingProductReviewViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getProductReviews(String proId) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getProductReviews(ApiEndPoint.PRODUCT_REVIEW + proId)
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
