package com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {
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
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Errors {}

    @Keep
    public static class Data {
        @SerializedName("full_name")
        @Expose
        private String full_name;

        @SerializedName("phone_no")
        @Expose
        private String phone_no;

        @SerializedName("email_id")
        @Expose
        private String email_id;

        @SerializedName("date")
        @Expose
        private String date;

        @SerializedName("referral_code")
        @Expose
        private String referral_code;

        @SerializedName("busniess_account")
        @Expose
        private String busniess_account;

        @SerializedName("upgrade_date")
        @Expose
        private String upgrade_date;

        @SerializedName("amount")
        @Expose
        private String amount;

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getPhone_no() {
            return phone_no;
        }

        public void setPhone_no(String phone_no) {
            this.phone_no = phone_no;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
        }

        public String getReferral_code() {
            return referral_code;
        }

        public void setReferral_code(String referral_code) {
            this.referral_code = referral_code;
        }

        public String getBusniess_account() {
            return busniess_account;
        }

        public void setBusniess_account(String busniess_account) {
            this.busniess_account = busniess_account;
        }

        public String getUpgrade_date() {
            return upgrade_date;
        }

        public void setUpgrade_date(String upgrade_date) {
            this.upgrade_date = upgrade_date;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
