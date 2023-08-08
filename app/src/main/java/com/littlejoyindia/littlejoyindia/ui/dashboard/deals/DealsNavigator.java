package com.littlejoyindia.littlejoyindia.ui.dashboard.deals;

import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.dealsMode.*;

public interface DealsNavigator {
    void showToastMessage(String message);
    void onSuccessMyDeals(DealsResponse response);
    void onSuccessMyDealDetails(DealDetailsResponse response);
    void success();
    void cancelled();
    void failed();
}
