package com.littlejoyindia.littlejoyindia.ui.dashboard;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.MyDealsFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.ShippingDealsDetailsFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.home.HomeFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.MyWalletFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.OrderFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.ShippingOrderDetailsFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.EditProfileFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.ProfileFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referAndEarn.ReferAndEarnFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers.ReferralFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.SalonFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.SalonCatFragment;

@Module
public abstract class DashboardFragmentProvider {

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract HomeFragment provideHomeFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract SalonFragment provideSalonFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract SalonCatFragment provideSalonCatFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract OnlineShoppingFragment provideOnlineShoppingFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract ReferAndEarnFragment provideReferAndEarnFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract MyWalletFragment provideMyWalletFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract ProfileFragment provideProfileFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract ReferralFragment provideReferralFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract OrderFragment provideOrderFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract MyDealsFragment provideMyDealsFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract EditProfileFragment provideEditProfileFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract ShippingOrderDetailsFragment provideShippingOrderDetailsFragmentFactory();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract ShippingDealsDetailsFragment provideShippingDealsDetailsFragmentFactory();


}
