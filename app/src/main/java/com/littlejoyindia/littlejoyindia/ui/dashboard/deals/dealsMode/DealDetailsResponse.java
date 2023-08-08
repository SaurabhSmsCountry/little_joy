package com.littlejoyindia.littlejoyindia.ui.dashboard.deals.dealsMode;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DealDetailsResponse {
    @SerializedName("errors")
    @Expose
    private Errors errors;

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<Data> data;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Errors {}

    @Keep
    public static class Data {
        @SerializedName("payment_status")
        @Expose
        private Integer payment_status;

        @SerializedName("order_id")
        @Expose
        private String order_id;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("paid_amount")
        @Expose
        private String paid_amount;

        @SerializedName("canceled")
        @Expose
        private Integer canceled;

        @SerializedName("order_status")
        @Expose
        private String order_status;

        @SerializedName("pay_mode")
        @Expose
        private String pay_mode;

        @SerializedName("created_at")
        @Expose
        private String created_at;

        @SerializedName("canceled_at")
        @Expose
        private String canceled_at;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("email_id")
        @Expose
        private String email_id;

        @SerializedName("mobile")
        @Expose
        private String mobile;

        @SerializedName("customer_id")
        @Expose
        private String customer_id;

        public Integer getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(Integer payment_status) {
            this.payment_status = payment_status;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPaid_amount() {
            return paid_amount;
        }

        public void setPaid_amount(String paid_amount) {
            this.paid_amount = paid_amount;
        }

        public Integer getCanceled() {
            return canceled;
        }

        public void setCanceled(Integer canceled) {
            this.canceled = canceled;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPay_mode() {
            return pay_mode;
        }

        public void setPay_mode(String pay_mode) {
            this.pay_mode = pay_mode;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getCanceled_at() {
            return canceled_at;
        }

        public void setCanceled_at(String canceled_at) {
            this.canceled_at = canceled_at;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email_id;
        }

        public void setEmail(String email) {
            this.email_id = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCustomerId() {
            return customer_id;
        }

        public void setCustomerId(String customer_id) {
            this.customer_id = customer_id;
        }
    }
}
