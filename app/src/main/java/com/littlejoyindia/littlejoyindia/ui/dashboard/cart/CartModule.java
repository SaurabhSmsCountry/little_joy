
package com.littlejoyindia.littlejoyindia.ui.dashboard.cart;

import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI.CartAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;


@Module
public class CartModule {

    @Provides
    CartAdapter provideCartAdapter() {
        return new CartAdapter(new ArrayList<>());
    }

}
