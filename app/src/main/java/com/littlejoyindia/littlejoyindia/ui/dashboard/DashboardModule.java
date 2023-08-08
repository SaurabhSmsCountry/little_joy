
package com.littlejoyindia.littlejoyindia.ui.dashboard;

import android.app.Activity;

import com.littlejoyindia.littlejoyindia.ui.dashboard.appointment.AppointmentActivity;
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
public class DashboardModule {

    @Provides
    CategoryPagerAdapter provideCategoryPagerAdapter(SalonFragment activity) {
        return new CategoryPagerAdapter(activity.getActivity().getSupportFragmentManager());
    }

    @Provides
    ActivityPagerAdapter provideActivityPagerAdapter(AppointmentActivity activity) {
        return new ActivityPagerAdapter(activity.getSupportFragmentManager());
    }


    @Provides
    ServicesAdapter provideServicesAdapter() {
        return new ServicesAdapter(new ArrayList<>());
    }

    @Provides
    CityAdapter provideCityAdapter() {
        return new CityAdapter(new ArrayList<>());
    }

    @Provides
    TopBrandsAdapter provideTopBrandsAdapter() {
        return new TopBrandsAdapter(new ArrayList<>());
    }


    @Provides
    PopularServicesAdapter providePopularServicesAdapter() {
        return new PopularServicesAdapter(new ArrayList<>());
    }

    @Provides
    DealsBannerAdapter provideDealsBannerAdapter() {
        return new DealsBannerAdapter(new ArrayList<>());
    }

    @Provides
    MerchantsAdapter provideMerchantsAdapter() {
        return new MerchantsAdapter(new ArrayList<>());
    }


    @Provides
    MerchantBannerAdapter provideMerchantBannerAdapter() {
        return new MerchantBannerAdapter(new ArrayList<>());
    }






}
