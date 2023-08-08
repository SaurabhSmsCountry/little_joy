package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome;


import com.littlejoyindia.littlejoyindia.data.model.DealsTopBrands;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;

import java.util.List;

public interface DealsHomeNavigator {

    void showToastMessage(String message);

    void setTopBrands(List<DealsTopBrands> data);

    void setPopularServices(List<SalonResponse.DealsHomeResponse.Popular> popular);

    void setDealMerchants(List<SalonResponse.DealsHomeResponse.Merchant> merchants);

    void setDealBanners(List<SalonResponse.DealsBannerResponse.DealsBanner> data);
}
