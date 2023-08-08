package com.littlejoyindia.littlejoyindia;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.auth.AuthViewModel;
import com.littlejoyindia.littlejoyindia.ui.auth.login.LoginViewModel;
import com.littlejoyindia.littlejoyindia.ui.auth.signup.SignUpViewModel;
import com.littlejoyindia.littlejoyindia.ui.auth.verify.VerifyOtpViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection.AddressSelectionViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.appointment.AppointmentCatViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.appointment.AppointmentViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.basicDetails.BasicDetailsViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.CartViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI.CartUIViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.DealsViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.ShippingDealsDetailsViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsCity.DealsCityViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.DelasHomeViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.home.HomeViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.map.MapUIViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.merchant.MerchantViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.MyWalletViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.ShoppingPaymentViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui.OnlineShoppingCartViewMOdel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm.ShoppingOrderConfirmationViewModal;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.OrderDetailsScreenViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.ShoppingOrderListScreenViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.addReview.AddProductReviewViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ShoppingProductViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.ui.main.MainViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productOverview.ProductDescriptionViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.ui.ShoppingProductReviewViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.OrderViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.ShippingOrderDetailsViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.ProfileViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.EditProfileViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referAndEarn.ReferAndEarnViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers.ReferralViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.review.MerchantReviewViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.SalonViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.SalonCatViewModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.timeslot.SelectTimeViewModel;
import com.littlejoyindia.littlejoyindia.ui.splash.SplashViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;


@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AuthViewModel.class)) {
            //noinspection unchecked
            return (T) new AuthViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
            //noinspection unchecked
            return (T) new SignUpViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(VerifyOtpViewModel.class)) {
            //noinspection unchecked
            return (T) new VerifyOtpViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DashboardViewModel.class)) {
            //noinspection unchecked
            return (T) new DashboardViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            //noinspection unchecked
            return (T) new HomeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SalonViewModel.class)) {
            //noinspection unchecked
            return (T) new SalonViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SalonCatViewModel.class)) {
            //noinspection unchecked
            return (T) new SalonCatViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CartViewModel.class)) {
            //noinspection unchecked
            return (T) new CartViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CartUIViewModel.class)) {
            //noinspection unchecked
            return (T) new CartUIViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MapUIViewModel.class)) {
            //noinspection unchecked
            return (T) new MapUIViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PaymentViewModel.class)) {
            //noinspection unchecked
            return (T) new PaymentViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AddressSelectionViewModel.class)) {
            //noinspection unchecked
            return (T) new AddressSelectionViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SelectTimeViewModel.class)) {
            //noinspection unchecked
            return (T) new SelectTimeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BasicDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new BasicDetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DealsCityViewModel.class)) {
            //noinspection unchecked
            return (T) new DealsCityViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DelasHomeViewModel.class)) {
            //noinspection unchecked
            return (T) new DelasHomeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MerchantViewModel.class)) {
            //noinspection unchecked
            return (T) new MerchantViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MerchantReviewViewModel.class)) {
            //noinspection unchecked
            return (T) new MerchantReviewViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AppointmentViewModel.class)) {
            //noinspection unchecked
            return (T) new AppointmentViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AppointmentCatViewModel.class)) {
            //noinspection unchecked
            return (T) new AppointmentCatViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OnlineShoppingViewModel.class)) {
            //noinspection unchecked
            return (T) new OnlineShoppingViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ShoppingProductViewModel.class)) {
            //noinspection unchecked
            return (T) new ShoppingProductViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ProductDescriptionViewModel.class)) {
            //noinspection unchecked
            return (T) new ProductDescriptionViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OnlineShoppingCartViewMOdel.class)) {
            //noinspection unchecked
            return (T) new OnlineShoppingCartViewMOdel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ShoppingPaymentViewModel.class)) {
            //noinspection unchecked
            return (T) new ShoppingPaymentViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OrderDetailsScreenViewModel.class)) {
            //noinspection unchecked
            return (T) new OrderDetailsScreenViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ShoppingOrderListScreenViewModel.class)) {
            //noinspection unchecked
            return (T) new ShoppingOrderListScreenViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ShoppingOrderConfirmationViewModal.class)) {
            //noinspection unchecked
            return (T) new ShoppingOrderConfirmationViewModal(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ShoppingProductReviewViewModel.class)) {
            //noinspection unchecked
            return (T) new ShoppingProductReviewViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AddProductReviewViewModel.class)) {
            //noinspection unchecked
            return (T) new AddProductReviewViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ReferralViewModel.class)) {
            //noinspection unchecked
            return (T) new ReferralViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OrderViewModel.class)) {
            //noinspection unchecked
            return (T) new OrderViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DealsViewModel.class)) {
            //noinspection unchecked
            return (T) new DealsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(EditProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new EditProfileViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ReferAndEarnViewModel.class)) {
            //noinspection unchecked
            return (T) new ReferAndEarnViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new ProfileViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MyWalletViewModel.class)) {
            //noinspection unchecked
            return (T) new MyWalletViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ShippingOrderDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new ShippingOrderDetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ShippingDealsDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new ShippingDealsDetailsViewModel(dataManager, schedulerProvider);
        }


        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}