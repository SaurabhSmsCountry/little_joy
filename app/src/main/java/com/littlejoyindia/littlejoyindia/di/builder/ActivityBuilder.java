/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.littlejoyindia.littlejoyindia.di.builder;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import com.littlejoyindia.littlejoyindia.ui.auth.AuthActivity;
import com.littlejoyindia.littlejoyindia.ui.auth.AuthActivityModule;
import com.littlejoyindia.littlejoyindia.ui.auth.login.LoginFragmentProvider;
import com.littlejoyindia.littlejoyindia.ui.auth.signup.SignupFragmentProvider;
import com.littlejoyindia.littlejoyindia.ui.auth.verify.VerifyOtpFragmentProvider;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardFragmentProvider;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardModule;
import com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection.AddressModule;
import com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection.AddressSelectionActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.appointment.AppointmentActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.appointment.AppointmentFragmentProvider;
import com.littlejoyindia.littlejoyindia.ui.dashboard.basicDetails.BasicDetailsActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.CartActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.CartFragmentProvider;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsCity.DealsCityActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.DealsHomeActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.map.MapUIActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.merchant.MerchantDetailsActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingPaymentWebview.OnlineShoppingPaymentWebviewActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui.OnlineShoppingCartActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui.OnlineShoppingCartModule;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm.ShoppingOrderConfirmationScreen;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.OrderDetailsScreenActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.ShoppingOrderListScreenActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.addReview.AddProductReviewActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ShoppingProductActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ShoppingProductFragmentProvider;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productOverview.ProductDescriptionActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productOverview.ProductDescriptionModule;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.ui.ActivityShoppingProductReview;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.review.MerchantReviewActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.timeslot.SelectTimeActivity;
import com.littlejoyindia.littlejoyindia.ui.splash.SplashActivity;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();


    @ContributesAndroidInjector(modules = {
            AuthActivityModule.class,
            LoginFragmentProvider.class,
            SignupFragmentProvider.class,
            VerifyOtpFragmentProvider.class
    })
    abstract AuthActivity bindAuthActivity();

    @ContributesAndroidInjector(modules = {
            DashboardFragmentProvider.class})
    abstract DashboardActivity bindDashboardActivity();

    @ContributesAndroidInjector(modules = {
            ShoppingProductFragmentProvider.class})
    abstract ShoppingProductActivity bindShoppingProductFragmentActivity();

    @ContributesAndroidInjector(modules = {
            CartFragmentProvider.class})
    abstract CartActivity bindCartActivity();

    @ContributesAndroidInjector
    abstract MapUIActivity bindMapUIActivity();

    @ContributesAndroidInjector
    abstract PaymentActivity bindPaymentActivity();

    @ContributesAndroidInjector(modules = {
            AddressModule.class})
    abstract AddressSelectionActivity bindAddressSelectionActivity();

    @ContributesAndroidInjector
    abstract SelectTimeActivity bindSelectTimeActivity();

    @ContributesAndroidInjector
    abstract BasicDetailsActivity bindBasicDetailsActivity();

    @ContributesAndroidInjector(modules = {
            DashboardModule.class})
    abstract DealsCityActivity bindDealsCityActivity();

    @ContributesAndroidInjector(modules = {
            DashboardModule.class})
    abstract DealsHomeActivity bindDealsHomeActivity();

    @ContributesAndroidInjector(modules = {
            DashboardModule.class})
    abstract MerchantDetailsActivity bindMerchantDetailsActivity();

    @ContributesAndroidInjector(modules = {
            DashboardModule.class})
    abstract MerchantReviewActivity bindMerchantReviewActivity();

    @ContributesAndroidInjector(modules = {
            AppointmentFragmentProvider.class})
    abstract AppointmentActivity bindAppointmentActivity();

    @ContributesAndroidInjector(modules = {
            ProductDescriptionModule.class})
    abstract ProductDescriptionActivity bindProductDescriptionActivity();

    @ContributesAndroidInjector(modules = {
            OnlineShoppingCartModule.class})
    abstract OnlineShoppingCartActivity bindOnlineShoppingCartActivity();

    @ContributesAndroidInjector
    abstract OnlineShoppingPaymentWebviewActivity bindOnlineShoppingPaymentWebviewActivity();

    @ContributesAndroidInjector
    abstract OrderDetailsScreenActivity bindOrderDetailsScreenActivity();

    @ContributesAndroidInjector
    abstract ShoppingOrderListScreenActivity bindShoppingOrderListScreenActivity();

    @ContributesAndroidInjector
    abstract ShoppingOrderConfirmationScreen bindShoppingOrderConfirmationScreen();

    @ContributesAndroidInjector
    abstract ActivityShoppingProductReview bindActivityShoppingProductReview();

    @ContributesAndroidInjector
    abstract AddProductReviewActivity bindAddProductReviewActivity();

}
