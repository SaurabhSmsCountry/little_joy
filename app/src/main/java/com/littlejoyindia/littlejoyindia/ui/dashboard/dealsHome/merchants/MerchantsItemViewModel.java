package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.merchants;


import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;


public class MerchantsItemViewModel {

    public String imageUrl = "", rating = "", name = "", merchantDetails = "";
    public MerchantsItemViewModel(SalonResponse.DealsHomeResponse.Merchant dealsTopBrands) {
      this.imageUrl = "https://littlejoyindia.com/merchant_reg/"+dealsTopBrands.getMerchantImg();
      this.rating = ""+dealsTopBrands.getMerAvgRating();
      this.name = dealsTopBrands.getMerBusiness();
      this.merchantDetails = dealsTopBrands.getMerAddress();
    }

}


