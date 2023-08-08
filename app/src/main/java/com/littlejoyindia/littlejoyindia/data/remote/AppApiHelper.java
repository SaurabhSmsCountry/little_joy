package com.littlejoyindia.littlejoyindia.data.remote;

import com.littlejoyindia.littlejoyindia.data.model.AddAddessResponse;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.OnlineShoppingResponse;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.dealsMode.DealDetailsResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.dealsMode.DealsResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.myWalletModel.MyWalletHistoryResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.myWalletModel.MyWalletResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.modal.OnlineShoppingCartResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm.CouponCodeCheckoutResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.modals.MyShoppingOrderListResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.timeline.OrderStatusResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.models.ProductReviewResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.orderModel.OrderResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealOrderCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.DealPaymentSelectionResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.OnlineShoppingPaymentSelectionResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.PaymentCheckoutResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonOrderCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonPaymentSelection;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.SalonPaymentSelectionResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.ThankYouCheckout;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models.ThankYouResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.ProfileResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.UpdateNameResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers.referralModel.ReferralMemberResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import com.littlejoyindia.littlejoyindia.data.model.CommonResponse;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthRequest;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthResponse;

import java.util.Map;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> doUserRegister(AuthRequest.ServerCreateProfileRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SERVER_UPDATE_USER_ADDRESS)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(AuthResponse.UserRegisterResponse.class);
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> verifyUserOtp(AuthRequest.ServerVerifyOtpRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SERVER_VERIFY_OTP)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(AuthResponse.UserRegisterResponse.class);
    }

    @Override
    public Single<CommonResponse> resendUserOtp(AuthRequest.ServerResendOtpRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SERVER_RESEND_OTP)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> createProfile(AuthRequest.ServerCreateProfileRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SERVER_REGISTER)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(AuthResponse.UserRegisterResponse.class);
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> doLogin(AuthRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SERVER_LOGIN)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(AuthResponse.UserRegisterResponse.class);
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> updateUserAddress(AuthRequest.ServerUpdateAddressRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SERVER_UPDATE_USER_ADDRESS)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(AuthResponse.UserRegisterResponse.class);
    }

    @Override
    public Single<SalonResponse.SalonAllServicesResponse> getAllSalonServices(SalonRequest.GetAllSalonServicesRequest request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_ALL_SALON_SERVICES)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.SalonAllServicesResponse.class);
    }

    @Override
    public Single<CommonResponse> addUpdateToCartSalonService(SalonRequest.AddUpdateSalonCartServicesRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ADD_UPDATE_CART_SALON_SERVICES)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<CommonResponse> removeCartItemSalonService(SalonRequest.RemoveCartItemServicesRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.REMOVE_CART_ITEM_SALON_SERVICE)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<SalonResponse.CartListingServicesResponse> getAllSalonServiceCartItems(SalonRequest.GetAnyInfoBasedOnUserId request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_SALON_SERVICE_CART)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.CartListingServicesResponse.class);
    }

    @Override
    public Single<SalonResponse.SavedAddressResponse> getAllSavedAddress(SalonRequest.GetAnyInfoBasedOnUser_Id request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_SAVED_ADDRESS)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.SavedAddressResponse.class);
    }

    @Override
    public Single<AddAddessResponse> addAddressByUser(SalonRequest.AddAddressOnServer request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.Add_ADDRESS_ON_SERVER)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(AddAddessResponse.class);
    }

    @Override
    public Single<SalonResponse.SaloonServiceCityResponse> getSalonServiceCity(SalonRequest.GetSaloonRequest request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_SALOON_SERVICE_CITY)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.SaloonServiceCityResponse.class);
    }

    @Override
    public Single<SalonResponse.WalletTotalResponse> getWalletAmount(SalonRequest.GetAnyInfoBasedOnUser_Id request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_USER_WALLET_AMOUNT)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.WalletTotalResponse.class);
    }

    @Override
    public Single<SalonResponse.SaloonServiceCityResponse> getSaloonDealsCity(SalonRequest.GetSaloonRequest request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_DEALS_SALOON_CITIES)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.SaloonServiceCityResponse.class);
    }

    @Override
    public Single<SalonResponse.DealsTopBrandsResponse> getHomeTopBrands(SalonRequest.GetSaloonRequest request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_DEALS_TOP_BRANDS)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.DealsTopBrandsResponse.class);
    }

    @Override
    public Single<SalonResponse.DealsHomeResponse> getHomeDeals(SalonRequest.GetDealsHomeBasedOnLocation request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_DEALS_HOME_DATA)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.DealsHomeResponse.class);
    }

    @Override
    public Single<SalonResponse.DealsBannerResponse> getDealsBanners(SalonRequest.GetSaloonRequest request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_DEALS_HOME_BANNERS)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.DealsBannerResponse.class);
    }

    @Override
    public Single<SalonResponse.MerchantDeatilsByIdResponse> getMerchantDetailsById(SalonRequest.GetMerchantDetailsById request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_MERCHANT_DETAILS_BY_ID)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.MerchantDeatilsByIdResponse.class);
    }

    @Override
    public Single<SalonResponse.MerchantDealsByIdResponse> getMerchantDealsByMerchant(SalonRequest.GetMerchantDealsByMerchant request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_MERCHANT_DEALS_BY_MERCHANT)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.MerchantDealsByIdResponse.class);
    }

    @Override
    public Single<CommonResponse> addUpdateToCartDeals(SalonRequest.AddUpdateSalonDealsRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ADD_UPDATE_TO_CART_DEALS)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<SalonResponse.CartListingServicesResponse> getAllDealsCartItems(SalonRequest.GetAnyInfoBasedOnUserId request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_ALL_DEALS_CART_ITEMS)
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SalonResponse.CartListingServicesResponse.class);
    }

    @Override
    public Single<CommonResponse> removeCartItemDeal(SalonRequest.RemoveDealCartItemRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.DELETE_DEAL_CART_ITEM)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<CommonResponse> deleteAddressFromServer(String apiEndPoint) {
        return Rx2AndroidNetworking.delete(apiEndPoint)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<OnlineShoppingResponse.ProductCategoryResponse> getOnlineShoppingCategory(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(OnlineShoppingResponse.ProductCategoryResponse.class);
    }

    @Override
    public Single<ProductListResponse.ProductList> getOnlineShoppingProductsByCategoryId(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(ProductListResponse.ProductList.class);
    }

    @Override
    public Single<CommonResponse> checkCODIsAvailable(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<CommonResponse> addToCartForOnlineSHopping(String apiEndPoint, CartModel model) {
        return Rx2AndroidNetworking.post(apiEndPoint)
                .addBodyParameter(model)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<CommonResponse> removeItemCartForOnlineSHopping(String apiEndPoint, SalonRequest.RemoveDealCartItemRequest model) {
        return Rx2AndroidNetworking.post(apiEndPoint)
                .addBodyParameter(model)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<OnlineShoppingCartResponse> getOnlineShoppingCart(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(OnlineShoppingCartResponse.class);
    }

    @Override
    public Single<ProductReviewResponse> getProductReviews(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(ProductReviewResponse.class);
    }

    @Override
    public Single<OnlineShoppingPaymentSelectionResponse> onlineShoppingPaymentMethodSubmitted(String apiEndPoint, OnlineShoppingPaymentSelection request) {
        return Rx2AndroidNetworking.post(apiEndPoint)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(OnlineShoppingPaymentSelectionResponse.class);
    }

    @Override
    public Single<PaymentCheckoutResponse> onlineShoppingCheckout(String apiEndPoint, OnlineShoppingCheckout request) {
        return Rx2AndroidNetworking.post(apiEndPoint)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(PaymentCheckoutResponse.class);
    }

    @Override
    public Single<MyShoppingOrderListResponse> getShoppingOrderDetails(String apiEndPoint, Map<String,String> map) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .addQueryParameter(map)
                .build()
                .getObjectSingle(MyShoppingOrderListResponse.class);
    }

    @Override
    public Single<CommonResponse> addProductReview(String apiEndPoint, Map<String, String> map) {
        return Rx2AndroidNetworking.post(apiEndPoint)
                .addBodyParameter(map)
                .build()
                .getObjectSingle(CommonResponse.class);
    }

    @Override
    public Single<OrderStatusResponse> getShoppingOrderStatus(String apiEndPoint, Map<String, String> map) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .addQueryParameter(map)
                .build()
                .getObjectSingle(OrderStatusResponse.class);
    }

    @Override
    public Single<CouponCodeCheckoutResponse> checkCouponCode(String apiEndPoint, Map<String, String> map) {
        return Rx2AndroidNetworking.post(apiEndPoint)
                .addBodyParameter(map)
                .build()
                .getObjectSingle(CouponCodeCheckoutResponse.class);
    }

    @Override
    public Single<ReferralMemberResponse> getReferralMembers(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(ReferralMemberResponse.class);
    }

    @Override
    public Single<ProfileResponse> getMyProfile(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(ProfileResponse.class);
    }

    @Override
    public Single<MyWalletResponse> getMyWallet(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(MyWalletResponse.class);
    }

    @Override
    public Single<MyWalletHistoryResponse> getMyWalletHistory(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(MyWalletHistoryResponse.class);
    }

    @Override
    public Single<DealsResponse> getMyDeals(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(DealsResponse.class);
    }

    @Override
    public Single<DealDetailsResponse> getMyDealDetails(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(DealDetailsResponse.class);
    }

    @Override
    public Single<PaymentCheckoutResponse> getDealOrderCheckout(String url, DealOrderCheckout request) {
        return Rx2AndroidNetworking.post(url)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(PaymentCheckoutResponse.class);
    }

    @Override
    public Single<DealPaymentSelectionResponse> dealPaymentMethodSubmitted(String url, DealPaymentSelection request) {
        return Rx2AndroidNetworking.post(url)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(DealPaymentSelectionResponse.class);
    }

    @Override
    public Single<PaymentCheckoutResponse> getSalonOrderCheckout(String url, SalonOrderCheckout request) {
        return Rx2AndroidNetworking.post(url)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(PaymentCheckoutResponse.class);
    }

    @Override
    public Single<ThankYouResponse> salonThankYouCheckout(String url, ThankYouCheckout request) {
        return Rx2AndroidNetworking.post(url)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(ThankYouResponse.class);
    }

    @Override
    public Single<SalonPaymentSelectionResponse> salonPaymentMethodSubmitted(String url, SalonPaymentSelection request) {
        return Rx2AndroidNetworking.post(url)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(SalonPaymentSelectionResponse.class);
    }

    @Override
    public Single<OrderResponse> getMyOrders(String apiEndPoint) {
        return Rx2AndroidNetworking.get(apiEndPoint)
                .build()
                .getObjectSingle(OrderResponse.class);
    }

    @Override
    public Single<UpdateNameResponse> updateUserName(String apiEndPoint, Map<String, String> map) {
        return Rx2AndroidNetworking.put(apiEndPoint)
                .addBodyParameter(map)
                .build()
                .getObjectSingle(UpdateNameResponse.class);
    }
}
