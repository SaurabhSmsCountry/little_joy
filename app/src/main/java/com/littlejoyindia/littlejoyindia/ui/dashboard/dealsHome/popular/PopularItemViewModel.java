package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.popular;


import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;


public class PopularItemViewModel {


    public SalonResponse.DealsHomeResponse.Popular dealsTopBrands ;
    public String imageUrl = "";
    public String rating = "";

    public PopularItemViewModel(SalonResponse.DealsHomeResponse.Popular  dealsTopBrands) {
      this.dealsTopBrands = dealsTopBrands;
      this.imageUrl = "https://littlejoyindia.com/merchant_reg/"+dealsTopBrands.getMerchantImg();
      this.rating = ""+dealsTopBrands.getMerAvgRating();
    }

}


