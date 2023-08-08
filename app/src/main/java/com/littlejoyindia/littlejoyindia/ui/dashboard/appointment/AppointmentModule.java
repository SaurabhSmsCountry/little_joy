
package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;

import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsCity.CityAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.banner.DealsBannerAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.merchants.MerchantsAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.popular.PopularServicesAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.topbrands.TopBrandsAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.merchant.MerchantBannerAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.CategoryPagerAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.SalonFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.ServicesAdapter;
import com.littlejoyindia.littlejoyindia.utils.ActivityPagerAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;


@Module
public class AppointmentModule {


    @Provides
    DealsAdapter provideDealsAdapter() {
        return new DealsAdapter(new ArrayList<>());
    }

}
