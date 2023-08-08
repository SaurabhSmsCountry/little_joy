package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.banner;


import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;


public class BannerItemViewModel {

    public String imageUrl = "";
    public BannerItemViewModel(SalonResponse.DealsBannerResponse.DealsBanner dealsTopBrands) {
      this.imageUrl = "https://www.littlejoyindia.com/deals_banner/"+dealsTopBrands.getImage();
    }

}


