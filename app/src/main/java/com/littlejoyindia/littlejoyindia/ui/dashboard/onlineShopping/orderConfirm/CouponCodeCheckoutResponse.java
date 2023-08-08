package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CouponCodeCheckoutResponse {
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("coupon_name")
        @Expose
        private String couponName;
        @SerializedName("discount_percent")
        @Expose
        private Integer discountPercent;
        @SerializedName("discount_amount")
        @Expose
        private Integer discountAmount;
        @SerializedName("service_tax")
        @Expose
        private Integer serviceTax;
        @SerializedName("isActive")
        @Expose
        private Integer isActive;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public Integer getDiscountPercent() {
            return discountPercent;
        }

        public void setDiscountPercent(Integer discountPercent) {
            this.discountPercent = discountPercent;
        }

        public Integer getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(Integer discountAmount) {
            this.discountAmount = discountAmount;
        }

        public Integer getServiceTax() {
            return serviceTax;
        }

        public void setServiceTax(Integer serviceTax) {
            this.serviceTax = serviceTax;
        }

        public Integer getIsActive() {
            return isActive;
        }

        public void setIsActive(Integer isActive) {
            this.isActive = isActive;
        }

    }
}
