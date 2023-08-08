package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppointmentFragmentProvider {

    @ContributesAndroidInjector(modules = AppointmentModule.class)
    abstract DealsFragment provideDealsFragmentFactory();


}
