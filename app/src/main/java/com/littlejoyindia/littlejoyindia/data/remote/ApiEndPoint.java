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
import com.littlejoyindia.littlejoyindia.BuildConfig;

public final class ApiEndPoint {


    public static final String SERVER_LOGIN = BuildConfig.BASE_URL + "auth/user-registration";
    public static final String SERVER_REGISTER = BuildConfig.BASE_URL + "auth/operator-registration";
    public static final String SERVER_VERIFY_OTP = BuildConfig.BASE_URL + "auth/otp-verify";
    public static final String SERVER_RESEND_OTP = BuildConfig.BASE_URL + "auth/otp-resend";

    public static final String SERVER_UPDATE_USER_ADDRESS = BuildConfig.BASE_URL + "auth/update-user-info";



    public static final String GET_DASHBOARD_BANNER = BuildConfig.BASE_URL + "banner/get-home-banner";

    // Salon Services
    public static final String GET_ALL_SALON_SERVICES = BuildConfig.BASE_URL + "service/getAllSalonService";
    public static final String ADD_UPDATE_CART_SALON_SERVICES = BuildConfig.BASE_URL + "cart/add-to-cart";
    public static final String GET_SALON_SERVICE_CART = BuildConfig.BASE_URL + "cart/getCartUserById";
    public static final String REMOVE_CART_ITEM_SALON_SERVICE = BuildConfig.BASE_URL + "cart/removeCartItem";
    public static final String GET_SAVED_ADDRESS = BuildConfig.BASE_URL + "user/getUserAddress";
    public static final String Add_ADDRESS_ON_SERVER = BuildConfig.BASE_URL + "user/addUserAddress";
    public static final String Delete_ADDRESS_ON_SERVER = BuildConfig.BASE_URL + "user/removeUserAddress/";
    public static final String GET_SALOON_SERVICE_CITY = BuildConfig.BASE_URL + "service/getSalonServiceCity";
    public static final String GET_MY_ORDERS = BuildConfig.BASE_URL + "service/getSalonServiceOrder?userId=";
    public static final String PAYMENT_METHOD_SUBMITTED_SALON = BuildConfig.BASE_URL + "service/salonServiceShipping";
    public static final String SALON_PAYMENT_CHECKOUT = BuildConfig.BASE_URL + "service/salonServiceCheckout";
    public static final String SALON_THANK_YOU = BuildConfig.BASE_URL + "service/thankyou-salon-service";

    // Saloon Deals
    public static final String GET_DEALS_SALOON_CITIES = BuildConfig.BASE_URL + "deals/getSalonDealsCity";
    public static final String GET_DEALS_TOP_BRANDS = BuildConfig.BASE_URL + "deals/getDealsBrands";
    public static final String GET_DEALS_HOME_DATA = BuildConfig.BASE_URL + "deals/getSalonMerchants";
    public static final String GET_DEALS_HOME_BANNERS = BuildConfig.BASE_URL + "banner/get-deals-banner";
    public static final String GET_MERCHANT_DETAILS_BY_ID = BuildConfig.BASE_URL + "deals/getSalonMerchantById";
    public static final String GET_MERCHANT_DEALS_BY_MERCHANT = BuildConfig.BASE_URL + "deals/getSalonDealsByMerchant";
    public static final String ADD_UPDATE_TO_CART_DEALS = BuildConfig.BASE_URL + "cart/add-to-cart-deals";
    public static final String GET_ALL_DEALS_CART_ITEMS = BuildConfig.BASE_URL + "cart/getDealsCartUserById";
    public static final String DELETE_DEAL_CART_ITEM = BuildConfig.BASE_URL + "cart/removeDealsCartItem";
    public static final String GET_MY_DEALS = BuildConfig.BASE_URL + "deals/getSalonDealsOrders?user_id=";
    public static final String GET_MY_DEAL_DETAILS = BuildConfig.BASE_URL + "deals/getSalonDealsOrdersById?user_id=";
    public static final String PAYMENT_METHOD_SUBMITTED_DEAL = BuildConfig.BASE_URL + "deals/salonDealsShipping";
    public static final String DEAL_PAYMENT_CHECKOUT = BuildConfig.BASE_URL + "deals/salonDealsPayment";
    public static final String DEAL_THANK_YOU = BuildConfig.BASE_URL + "deals/thankyou-salon-deals";

    /// Online Shopping Apis
    public static final String GET_SHOPPING_PRODUCT_CATEGORY = BuildConfig.BASE_URL + "shopping/getProductCategory";
    public static final String GET_SHOPPING_PRODUCT_BY_CATEGORY_ID = BuildConfig.BASE_URL + "shopping/getProductById?id=";
    public static final String GET_SHOPPING_PRODUCT_BY_CATEGORY_NAME = BuildConfig.BASE_URL + "shopping/getProduct?";
    public static final String ADD_TO_CART_ONLINE_SHOPPING = BuildConfig.BASE_URL + "cart/add-to-cart-online-shopping";
    public static final String GET_CART_ONLINE_SHOPPING = BuildConfig.BASE_URL + "cart/getOnlineShoppingCartUserById?userId=";
    public static final String REMOVE_CART_ONLINE_SHOPPING = BuildConfig.BASE_URL + "cart/removeOnlineShoppingCartItem";
    public static final String CHECK_COD_IS_AVAILABLE = BuildConfig.BASE_URL + "shopping/checkPin?pincode=";
    public static final String CHECK_COD_IS_AVAILABLE_FOR_A_USER = BuildConfig.BASE_URL + "user/checkUserForCod?userId=";
    public static final String PAYMENT_METHOD_SUBMITTED_ONLINE_SHOPPING = BuildConfig.BASE_URL + "shopping/onlineShoppingShipping";
    public static final String ONLINE_SHOPPING_CHECKOUT = BuildConfig.BASE_URL + "shopping/onlineShoppingCheckout";
    public static final String ONLINE_SHOPPING_ORDER_DETAILS = BuildConfig.BASE_URL + "shopping/getProductOrderDetailsByOrderId";
    public static final String ONLINE_SHOPPING_ORDER_LIST = BuildConfig.BASE_URL + "shopping/getProductOrderDetails";
    public static final String ONLINE_SHOPPING_ORDER_STATUS = BuildConfig.BASE_URL + "shopping/getOrderStatusByOrderId";
//    public static final String ONLINE_SHOPPING_COUPON_CHECK = BuildConfig.BASE_URL + "coupon/check-coupon";
    public static final String ONLINE_SHOPPING_COUPON_CHECK = BuildConfig.BASE_URL + "coupon/check-coupon-for-online-shopping";
    public static final String SHOPPING_HOME_PRODUCTS = BuildConfig.BASE_URL + "shopping/getHomeProduct";
    public static final String PRODUCT_REVIEW = BuildConfig.BASE_URL + "review/getProductReviewByProId?proId=";
    public static final String ADD_PRODUCT_REVIEW = BuildConfig.BASE_URL + "review/addProductReview";

    //OTHERS
    public static final String GET_USER_WALLET_AMOUNT = BuildConfig.BASE_URL + "user/getWallet";
    public static final String GET_REFERRAL_MEMBER = BuildConfig.BASE_URL + "auth/getReferralMember?user_id=";
    public static final String GET_MY_PROFILE = BuildConfig.BASE_URL + "auth/getMyProfile?user_id=";
    public static final String GET_MY_WALLET = BuildConfig.BASE_URL + "user/getWallet?user_id=";
    public static final String GET_MY_WALLET_HISTORY = BuildConfig.BASE_URL + "user/getWalletHistory?user_id=";
    public static final String UPDATE_PROFILE_NAME = BuildConfig.BASE_URL + "auth/UserUpdateProfileName";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
