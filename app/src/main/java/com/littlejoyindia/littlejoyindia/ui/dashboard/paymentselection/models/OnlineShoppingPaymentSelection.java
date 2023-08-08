package com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnlineShoppingPaymentSelection {

    @SerializedName("cart_id")
    @Expose
    private Integer cartId;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("mode_pay")
    @Expose
    private String modePay;

    @SerializedName("coupon")
    @Expose
    private String coupon;

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getModePay() {
        return modePay;
    }

    public void setModePay(String modePay) {
        this.modePay = modePay;
    }

}
