package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.addReview;

import com.littlejoyindia.littlejoyindia.data.model.CommonResponse;

public interface AddProductReviewNavigator {
    void showToastMessage(String message);

    void updateUI(int position, CommonResponse response);
}
