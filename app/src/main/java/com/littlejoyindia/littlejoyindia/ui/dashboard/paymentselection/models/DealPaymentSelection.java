package com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealPaymentSelection {

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
    @SerializedName("email_id")
    @Expose
    private String email_id;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("mode_pay")
    @Expose
    private String modePay;

    @SerializedName("total_paid")
    @Expose
    private String total_paid;

    @SerializedName("total")
    @Expose
    private String total;

    public String getEmail() {
        return email_id;
    }

    public void setEmail(String email_id) {
        this.email_id = email_id;
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

    public String getTotalPaid() {
        return total_paid;
    }

    public void setTotalPaid(String total_paid) {
        this.total_paid = total_paid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
