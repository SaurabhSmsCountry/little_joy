package com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentCheckoutResponse {
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

        @SerializedName("cf_order_id")
        @Expose
        private Integer cfOrderId;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("order_token")
        @Expose
        private String orderToken;
        @SerializedName("payment_link")
        @Expose
        private String paymentLink;

        public Integer getCfOrderId() {
            return cfOrderId;
        }

        public void setCfOrderId(Integer cfOrderId) {
            this.cfOrderId = cfOrderId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderToken() {
            return orderToken;
        }

        public void setOrderToken(String orderToken) {
            this.orderToken = orderToken;
        }

        public String getPaymentLink() {
            return paymentLink;
        }

        public void setPaymentLink(String paymentLink) {
            this.paymentLink = paymentLink;
        }

    }
}
