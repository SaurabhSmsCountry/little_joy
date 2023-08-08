package com.littlejoyindia.littlejoyindia.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import com.littlejoyindia.littlejoyindia.data.local.prefs.PreferencesHelper;
import com.littlejoyindia.littlejoyindia.data.model.AddAddessResponse;
import com.littlejoyindia.littlejoyindia.data.model.CommonResponse;
import com.littlejoyindia.littlejoyindia.data.model.User;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthRequest;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthResponse;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.OnlineShoppingResponse;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;
import com.littlejoyindia.littlejoyindia.data.remote.ApiHeader;
import com.littlejoyindia.littlejoyindia.data.remote.ApiHelper;
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

import java.util.Map;


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public void setUserAsLoggedOut() {

    }

    @Override
    public void updateApiHeader(Integer userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void setUserInfoToPrefs(User user) {

        setFullName(user.getFullName());
        setMobileNumber(user.getPhoneNo());
        Log.e("LOCAL", "FullName : " + getFullName());
        Log.e("LOCAL", "Mobile : " + getMobileNumber());
        setUid(String.valueOf(user.getId()));

    }


    @Override
    public ApiHeader getApiHeader() {
        return null;
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> doUserRegister(AuthRequest.ServerCreateProfileRequest request) {
        return mApiHelper.doUserRegister(request);
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> verifyUserOtp(AuthRequest.ServerVerifyOtpRequest request) {
        return mApiHelper.verifyUserOtp(request);
    }

    @Override
    public Single<CommonResponse> resendUserOtp(AuthRequest.ServerResendOtpRequest request) {
        return mApiHelper.resendUserOtp(request);
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> createProfile(AuthRequest.ServerCreateProfileRequest request) {
        return mApiHelper.createProfile(request);
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> doLogin(AuthRequest.ServerLoginRequest request) {
        return mApiHelper.doLogin(request);
    }

    @Override
    public Single<AuthResponse.UserRegisterResponse> updateUserAddress(AuthRequest.ServerUpdateAddressRequest request) {
        return mApiHelper.updateUserAddress(request);
    }

    @Override
    public Single<SalonResponse.SalonAllServicesResponse> getAllSalonServices(SalonRequest.GetAllSalonServicesRequest request) {
        return mApiHelper.getAllSalonServices(request);
    }

    @Override
    public Single<CommonResponse> addUpdateToCartSalonService(SalonRequest.AddUpdateSalonCartServicesRequest request) {
        return mApiHelper.addUpdateToCartSalonService(request);
    }

    @Override
    public Single<CommonResponse> removeCartItemSalonService(SalonRequest.RemoveCartItemServicesRequest request) {
        return mApiHelper.removeCartItemSalonService(request);
    }

    @Override
    public Single<SalonResponse.CartListingServicesResponse> getAllSalonServiceCartItems(SalonRequest.GetAnyInfoBasedOnUserId request) {
        return mApiHelper.getAllSalonServiceCartItems(request);
    }

    @Override
    public Single<SalonResponse.SavedAddressResponse> getAllSavedAddress(SalonRequest.GetAnyInfoBasedOnUser_Id request) {
        return mApiHelper.getAllSavedAddress(request);
    }

    @Override
    public Single<AddAddessResponse> addAddressByUser(SalonRequest.AddAddressOnServer request) {
        return mApiHelper.addAddressByUser(request);
    }

    @Override
    public Single<SalonResponse.SaloonServiceCityResponse> getSalonServiceCity(SalonRequest.GetSaloonRequest request) {
        return mApiHelper.getSalonServiceCity(request);
    }

    @Override
    public Single<SalonResponse.WalletTotalResponse> getWalletAmount(SalonRequest.GetAnyInfoBasedOnUser_Id request) {
        return mApiHelper.getWalletAmount(request);
    }

    @Override
    public Single<SalonResponse.SaloonServiceCityResponse> getSaloonDealsCity(SalonRequest.GetSaloonRequest request) {
        return mApiHelper.getSaloonDealsCity(request);
    }

    @Override
    public Single<SalonResponse.DealsTopBrandsResponse> getHomeTopBrands(SalonRequest.GetSaloonRequest request) {
        return mApiHelper.getHomeTopBrands(request);
    }

    @Override
    public Single<SalonResponse.DealsHomeResponse> getHomeDeals(SalonRequest.GetDealsHomeBasedOnLocation request) {
        return mApiHelper.getHomeDeals(request);
    }

    @Override
    public Single<SalonResponse.DealsBannerResponse> getDealsBanners(SalonRequest.GetSaloonRequest request) {
        return mApiHelper.getDealsBanners(request);
    }

    @Override
    public Single<SalonResponse.MerchantDeatilsByIdResponse> getMerchantDetailsById(SalonRequest.GetMerchantDetailsById request) {
        return mApiHelper.getMerchantDetailsById(request);
    }

    @Override
    public Single<SalonResponse.MerchantDealsByIdResponse> getMerchantDealsByMerchant(SalonRequest.GetMerchantDealsByMerchant request) {
        return mApiHelper.getMerchantDealsByMerchant(request);
    }

    @Override
    public Single<CommonResponse> addUpdateToCartDeals(SalonRequest.AddUpdateSalonDealsRequest request) {
        return mApiHelper.addUpdateToCartDeals(request);
    }

    @Override
    public Single<SalonResponse.CartListingServicesResponse> getAllDealsCartItems(SalonRequest.GetAnyInfoBasedOnUserId request) {
        return mApiHelper.getAllDealsCartItems(request);
    }

    @Override
    public Single<CommonResponse> removeCartItemDeal(SalonRequest.RemoveDealCartItemRequest request) {
        return mApiHelper.removeCartItemDeal(request);
    }

    @Override
    public Single<CommonResponse> deleteAddressFromServer(String apiEndPoint) {
        return mApiHelper.deleteAddressFromServer(apiEndPoint);
    }

    @Override
    public Single<OnlineShoppingResponse.ProductCategoryResponse> getOnlineShoppingCategory(String apiEndPoint) {
        return mApiHelper.getOnlineShoppingCategory(apiEndPoint);
    }

    @Override
    public Single<ProductListResponse.ProductList> getOnlineShoppingProductsByCategoryId(String apiEndPoint) {
        return mApiHelper.getOnlineShoppingProductsByCategoryId(apiEndPoint);
    }

    @Override
    public Single<CommonResponse> checkCODIsAvailable(String apiEndPoint) {
        return mApiHelper.checkCODIsAvailable(apiEndPoint);
    }

    @Override
    public Single<CommonResponse> addToCartForOnlineSHopping(String apiEndPoint, CartModel model) {
        return mApiHelper.addToCartForOnlineSHopping(apiEndPoint, model);
    }

    @Override
    public Single<CommonResponse> removeItemCartForOnlineSHopping(String apiEndPoint, SalonRequest.RemoveDealCartItemRequest model) {
        return mApiHelper.removeItemCartForOnlineSHopping(apiEndPoint, model);
    }

    @Override
    public Single<OnlineShoppingCartResponse> getOnlineShoppingCart(String apiEndPoint) {
        return mApiHelper.getOnlineShoppingCart(apiEndPoint);
    }

    @Override
    public Single<ProductReviewResponse> getProductReviews(String apiEndPoint) {
        return mApiHelper.getProductReviews(apiEndPoint);
    }

    @Override
    public Single<CommonResponse> addProductReview(String apiEndPoint,Map<String,String> map) {
        return mApiHelper.addProductReview(apiEndPoint,map);
    }

    @Override
    public Single<OnlineShoppingPaymentSelectionResponse> onlineShoppingPaymentMethodSubmitted(String apiEndPoint, OnlineShoppingPaymentSelection request) {
        return mApiHelper.onlineShoppingPaymentMethodSubmitted(apiEndPoint, request);
    }

    @Override
    public Single<PaymentCheckoutResponse> onlineShoppingCheckout(String apiEndPoint, OnlineShoppingCheckout request) {
        return mApiHelper.onlineShoppingCheckout(apiEndPoint, request);
    }

    @Override
    public Single<MyShoppingOrderListResponse> getShoppingOrderDetails(String apiEndPoint, Map<String,String> map) {
        return mApiHelper.getShoppingOrderDetails(apiEndPoint,map);
    }

    @Override
    public Single<OrderStatusResponse> getShoppingOrderStatus(String apiEndPoint, Map<String,String> map) {
        return mApiHelper.getShoppingOrderStatus(apiEndPoint,map);
    }

    @Override
    public Single<CouponCodeCheckoutResponse> checkCouponCode(String apiEndPoint, Map<String, String> map) {
        return mApiHelper.checkCouponCode(apiEndPoint, map);
    }


    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }


    @Override
    public String getUid() {
        return mPreferencesHelper.getUid();
    }

    @Override
    public void setUid(String uid) {
        mPreferencesHelper.setUid(uid);
    }

    @Override
    public void setFullName(String fullName) {
        mPreferencesHelper.setFullName(fullName);
    }

    @Override
    public String getFullName() {
        return mPreferencesHelper.getFullName();
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        mPreferencesHelper.setMobileNumber(mobileNumber);
    }

    @Override
    public String getMobileNumber() {
        return mPreferencesHelper.getMobileNumber();
    }

    @Override
    public void setOtpCode(String otpCode) {
        mPreferencesHelper.setOtpCode(otpCode);
    }

    @Override
    public String getOtpCode() {
        return mPreferencesHelper.getOtpCode();
    }

    @Override
    public void setUserType(String userType) {
        mPreferencesHelper.setUserType(userType);
    }

    @Override
    public String getUserType() {
        return mPreferencesHelper.getUserType();
    }

    @Override
    public void setLoginType(String loginType) {
        mPreferencesHelper.setLoginType(loginType);
    }

    @Override
    public String getLoginType() {
        return mPreferencesHelper.getLoginType();
    }

    @Override
    public void setUserToken(String userToken) {
        mPreferencesHelper.setUserToken(userToken);
    }

    @Override
    public String getUserToken() {
        return mPreferencesHelper.getUserToken();
    }

    @Override
    public void setUserAddressAvailable(boolean isAvailable) {
        mPreferencesHelper.setUserAddressAvailable(isAvailable);
    }

    @Override
    public boolean getUserAddressAvailable() {
        return mPreferencesHelper.getUserAddressAvailable();
    }

    @Override
    public void setUserTsawId(String tsawId) {
        mPreferencesHelper.setUserTsawId(tsawId);
    }

    @Override
    public String getUserTsawId() {
        return mPreferencesHelper.getUserTsawId();
    }

    @Override
    public void setWelcomeMessageStatus(boolean isWelcomeMessageStatus) {
        mPreferencesHelper.setWelcomeMessageStatus(isWelcomeMessageStatus);
    }

    @Override
    public boolean getWelcomeMessageStatus() {
        return mPreferencesHelper.getWelcomeMessageStatus();
    }

    @Override
    public Single<ReferralMemberResponse> getReferralMembers(String apiEndPoint) {
        return mApiHelper.getReferralMembers(apiEndPoint);
    }

    @Override
    public Single<ProfileResponse> getMyProfile(String apiEndPoint) {
        return mApiHelper.getMyProfile(apiEndPoint);
    }

    @Override
    public Single<MyWalletResponse> getMyWallet(String apiEndPoint) {
        return mApiHelper.getMyWallet(apiEndPoint);
    }

    @Override
    public Single<MyWalletHistoryResponse> getMyWalletHistory(String apiEndPoint) {
        return mApiHelper.getMyWalletHistory(apiEndPoint);
    }

    @Override
    public Single<DealsResponse> getMyDeals(String apiEndPoint) {
        return mApiHelper.getMyDeals(apiEndPoint);
    }

    @Override
    public Single<DealDetailsResponse> getMyDealDetails(String apiEndPoint) {
        return mApiHelper.getMyDealDetails(apiEndPoint);
    }

    @Override
    public Single<PaymentCheckoutResponse> getDealOrderCheckout(String apiEndPoint, DealOrderCheckout request) {
        return mApiHelper.getDealOrderCheckout(apiEndPoint,request);
    }

    @Override
    public Single<DealPaymentSelectionResponse> dealPaymentMethodSubmitted(String apiEndPoint, DealPaymentSelection request) {
        return mApiHelper.dealPaymentMethodSubmitted(apiEndPoint, request);
    }

    @Override
    public Single<OrderResponse> getMyOrders(String apiEndPoint) {
        return mApiHelper.getMyOrders(apiEndPoint);
    }

    @Override
    public Single<UpdateNameResponse> updateUserName(String apiEndPoint, Map<String, String> map) {
        return mApiHelper.updateUserName(apiEndPoint, map);
    }

    @Override
    public Single<PaymentCheckoutResponse> getSalonOrderCheckout(String apiEndPoint, SalonOrderCheckout request) {
        return mApiHelper.getSalonOrderCheckout(apiEndPoint,request);
    }

    @Override
    public Single<ThankYouResponse> salonThankYouCheckout(String url, ThankYouCheckout request) {
        return mApiHelper.salonThankYouCheckout(url,request);
    }

    @Override
    public Single<SalonPaymentSelectionResponse> salonPaymentMethodSubmitted(String apiEndPoint, SalonPaymentSelection request) {
        return mApiHelper.salonPaymentMethodSubmitted(apiEndPoint, request);
    }
}
