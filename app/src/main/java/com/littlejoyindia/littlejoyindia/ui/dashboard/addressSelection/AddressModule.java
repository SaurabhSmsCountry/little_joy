
package com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection;
import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;


@Module
public class AddressModule {

    @Provides
    AddressAdapter provideAddressAdapter() {
        return new AddressAdapter(new ArrayList<>());
    }

}
