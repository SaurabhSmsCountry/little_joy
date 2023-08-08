package com.littlejoyindia.littlejoyindia.data.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import com.littlejoyindia.littlejoyindia.data.model.Errors;
import com.littlejoyindia.littlejoyindia.data.model.Meta;
import com.littlejoyindia.littlejoyindia.data.model.User;

import java.util.List;

public class AuthResponse {

    private AuthResponse(){

    }

    public static class UserRegisterResponse {



        @SerializedName("data")
        @Expose
        private List<User> data = null;

        @SerializedName("errors")
        @Expose
        private Errors errors;
        @SerializedName("meta")
        @Expose
        private Meta meta;
        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("message")
        @Expose
        private String message;

        public List<User> getData() {
            return data;
        }

        public void setData(List<User> data) {
            this.data = data;
        }


        public Errors getErrors() {
            return errors;
        }

        public void setErrors(Errors errors) {
            this.errors = errors;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
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
    }

    public static class UserFinishResponse {

//        @SerializedName("data")
//        @Expose
//        private CompleteProfileModel data = null;

        @SerializedName("errors")
        @Expose
        private Errors errors;
        @SerializedName("meta")
        @Expose
        private Meta meta;
        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("message")
        @Expose
        private String message;


//        public CompleteProfileModel getUser() {
//            return data;
//        }
//
//        public void setUser(CompleteProfileModel data) {
//            this.data = data;
//        }


        public Errors getErrors() {
            return errors;
        }

        public void setErrors(Errors errors) {
            this.errors = errors;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
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
    }
}
