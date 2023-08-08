package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing;

import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardModule;
import com.littlejoyindia.littlejoyindia.ui.dashboard.home.HomeFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ui.main.ShoppingProductFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ShoppingProductFragmentProvider {

    @ContributesAndroidInjector(modules = ShoppingProductModule.class)
    abstract ShoppingProductFragment provideShoppingProductFragmentFactory();
}
