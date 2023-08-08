package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.topbrands;


import com.littlejoyindia.littlejoyindia.data.model.DealsTopBrands;


public class BrandsItemViewModel {


    private DealsTopBrands dealsTopBrands ;
    public String imageUrl = "";
    public BrandsItemViewModel(DealsTopBrands dealsTopBrands) {
      this.dealsTopBrands = dealsTopBrands;
      this.imageUrl = "https://littlejoyindia.com/deals_top_brands/"+dealsTopBrands.getImage();
    }

}


