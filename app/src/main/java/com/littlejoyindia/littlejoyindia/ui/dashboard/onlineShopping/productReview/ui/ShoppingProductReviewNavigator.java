package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.ui;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.models.ProductReviewResponse;

public interface ShoppingProductReviewNavigator {
    void showToastMessage(String message);
    void updateUI(ProductReviewResponse reviewResponse);
}
