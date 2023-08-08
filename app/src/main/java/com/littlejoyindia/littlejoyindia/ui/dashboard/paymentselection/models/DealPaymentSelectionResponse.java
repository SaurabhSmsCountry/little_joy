package com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealPaymentSelectionResponse {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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

    public static class Data {
        @SerializedName("customer_id")
        @Expose
        private Integer customerId;
        @SerializedName("grand_total")
        @Expose
        private String grandTotal;
        @SerializedName("txn_id")
        @Expose
        private String txnId;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("coupon_code")
        @Expose
        private String couponCode;

        public Integer getId() {
            return customerId;
        }

        public void setId(Integer customerId) {
            this.customerId = customerId;
        }

        public String getGrandTotal() {
            return grandTotal;
        }

        public void setGrandTotal(String grandTotal) {
            this.grandTotal = grandTotal;
        }

        public String getTxnId() {
            return txnId;
        }

        public void setTxnId(String txnId) {
            this.txnId = txnId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCouponCode() {
            return couponCode;
        }

        public void setCouponCode(String couponCode) {
            this.couponCode = couponCode;
        }

    }
}

