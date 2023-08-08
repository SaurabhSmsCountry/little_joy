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

package com.littlejoyindia.littlejoyindia.data.remote;


import io.reactivex.Completable;
import io.reactivex.Single;

import com.littlejoyindia.littlejoyindia.data.model.AddAddessResponse;
import com.littlejoyindia.littlejoyindia.data.model.CommonResponse;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthRequest;
import com.littlejoyindia.littlejoyindia.data.model.auth.AuthResponse;
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

import java.util.Map;


public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<AuthResponse.UserRegisterResponse> doUserRegister(AuthRequest.ServerCreateProfileRequest request);

    Single<AuthResponse.UserRegisterResponse> verifyUserOtp(AuthRequest.ServerVerifyOtpRequest request);

    Single<CommonResponse> resendUserOtp(AuthRequest.ServerResendOtpRequest request);

    Single<AuthResponse.UserRegisterResponse> createProfile(AuthRequest.ServerCreateProfileRequest request);

    Single<AuthResponse.UserRegisterResponse> doLogin(AuthRequest.ServerLoginRequest request);

    Single<AuthResponse.UserRegisterResponse> updateUserAddress(AuthRequest.ServerUpdateAddressRequest request);

    Single<SalonResponse.SalonAllServicesResponse> getAllSalonServices(SalonRequest.GetAllSalonServicesRequest request);

    Single<CommonResponse> addUpdateToCartSalonService(SalonRequest.AddUpdateSalonCartServicesRequest request);

    Single<CommonResponse> removeCartItemSalonService(SalonRequest.RemoveCartItemServicesRequest request);

    Single<SalonResponse.CartListingServicesResponse> getAllSalonServiceCartItems(SalonRequest.GetAnyInfoBasedOnUserId request);

    Single<SalonResponse.SavedAddressResponse> getAllSavedAddress(SalonRequest.GetAnyInfoBasedOnUser_Id request);


    Single<AddAddessResponse> addAddressByUser(SalonRequest.AddAddressOnServer request);

    Single<SalonResponse.SaloonServiceCityResponse> getSalonServiceCity(SalonRequest.GetSaloonRequest request);

    Single<SalonResponse.WalletTotalResponse> getWalletAmount(SalonRequest.GetAnyInfoBasedOnUser_Id request);

    // Saloon Deals

    Single<SalonResponse.SaloonServiceCityResponse> getSaloonDealsCity(SalonRequest.GetSaloonRequest request);

    Single<SalonResponse.DealsTopBrandsResponse> getHomeTopBrands(SalonRequest.GetSaloonRequest request);

    Single<SalonResponse.DealsHomeResponse> getHomeDeals(SalonRequest.GetDealsHomeBasedOnLocation request);

    Single<SalonResponse.DealsBannerResponse> getDealsBanners(SalonRequest.GetSaloonRequest request);

    Single<SalonResponse.MerchantDeatilsByIdResponse> getMerchantDetailsById(SalonRequest.GetMerchantDetailsById request);

    Single<SalonResponse.MerchantDealsByIdResponse> getMerchantDealsByMerchant(SalonRequest.GetMerchantDealsByMerchant request);

    Single<CommonResponse> addUpdateToCartDeals(SalonRequest.AddUpdateSalonDealsRequest request);

    Single<SalonResponse.CartListingServicesResponse> getAllDealsCartItems(SalonRequest.GetAnyInfoBasedOnUserId request);

    Single<CommonResponse> removeCartItemDeal(SalonRequest.RemoveDealCartItemRequest request);

    Single<CommonResponse> deleteAddressFromServer(String apiEndPoint);

    Single<OnlineShoppingResponse.ProductCategoryResponse> getOnlineShoppingCategory(String apiEndPoint);

    Single<ProductListResponse.ProductList> getOnlineShoppingProductsByCategoryId(String apiEndPoint);

    Single<CommonResponse> checkCODIsAvailable(String apiEndPoint);

    Single<CommonResponse> addToCartForOnlineSHopping(String apiEndPoint, CartModel request);

    Single<CommonResponse> removeItemCartForOnlineSHopping(String apiEndPoint, SalonRequest.RemoveDealCartItemRequest request);

    Single<OnlineShoppingCartResponse> getOnlineShoppingCart(String apiEndPoint);

    Single<ProductReviewResponse> getProductReviews(String apiEndPoint);

    Single<OnlineShoppingPaymentSelectionResponse> onlineShoppingPaymentMethodSubmitted(String url, OnlineShoppingPaymentSelection request);

    Single<PaymentCheckoutResponse> onlineShoppingCheckout(String url, OnlineShoppingCheckout request);

    Single<MyShoppingOrderListResponse> getShoppingOrderDetails(String url, Map<String, String> map);
    Single<CouponCodeCheckoutResponse> checkCouponCode(String url, Map<String, String> map);

    Single<OrderStatusResponse> getShoppingOrderStatus(String url, Map<String, String> map);
    Single<CommonResponse> addProductReview(String url, Map<String, String> map);

    Single<ReferralMemberResponse> getReferralMembers(String apiEndPoint);

    Single<ProfileResponse> getMyProfile(String apiEndPoint);

    Single<MyWalletResponse> getMyWallet(String apiEndPoint);

    Single<MyWalletHistoryResponse> getMyWalletHistory(String apiEndPoint);

    Single<DealsResponse> getMyDeals(String apiEndPoint);

    Single<DealDetailsResponse> getMyDealDetails(String apiEndPoint);

    Single<PaymentCheckoutResponse> getDealOrderCheckout(String url, DealOrderCheckout request);

    Single<DealPaymentSelectionResponse> dealPaymentMethodSubmitted(String url, DealPaymentSelection request);

    Single<PaymentCheckoutResponse> getSalonOrderCheckout(String url, SalonOrderCheckout request);

    Single<ThankYouResponse> salonThankYouCheckout(String url, ThankYouCheckout request);

    Single<SalonPaymentSelectionResponse> salonPaymentMethodSubmitted(String url, SalonPaymentSelection request);

    Single<OrderResponse> getMyOrders(String apiEndPoint);

    Single<UpdateNameResponse> updateUserName(String apiEndPoint, Map<String, String> map);
}
