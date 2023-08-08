package com.littlejoyindia.littlejoyindia.ui.dashboard.cart;


import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI.CartFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class CartFragmentProvider {

    @ContributesAndroidInjector(modules = CartModule.class)
    abstract CartFragment provideCartFragmentFactory();


}
