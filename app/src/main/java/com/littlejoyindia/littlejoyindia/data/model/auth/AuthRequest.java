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

package com.littlejoyindia.littlejoyindia.data.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitshekhar on 07/07/17.
 */

public final class AuthRequest {

    private AuthRequest() {
        // This class is not publicly instantiable
    }

    public static class ServerUpdateProfilePicRequest {

        @Expose
        @SerializedName("profilePic")
        private String profilePic;



        @Expose
        @SerializedName("opId")
        public String userId;





        public ServerUpdateProfilePicRequest(String profilePic,
                                             String userId) {
            this.profilePic = profilePic;
            this.userId = userId;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerUpdateProfilePicRequest that = (ServerUpdateProfilePicRequest) object;

            if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
                return false;
            }
            return userId != null ? userId.equals(that.userId) : that.userId == null;
        }

        @Override
        public int hashCode() {
            int result = userId != null ? userId.hashCode() : 0;
            result = 31 * result + (userId != null ? userId.hashCode() : 0);
            return result;
        }
    }

    public static class ServerLoginRequest {

        @Expose
        @SerializedName("mobile")
        private String mobile;


        public ServerLoginRequest(String mobile) {
            this.mobile = mobile;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerLoginRequest that = (ServerLoginRequest) object;

            if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) {
                return false;
            }
            return mobile != null ? mobile.equals(that.mobile) : that.mobile == null;
        }

        @Override
        public int hashCode() {
            int result = mobile != null ? mobile.hashCode() : 0;
            result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
            return result;
        }
    }

    public static class ServerRegisterRequest {

        @Expose
        @SerializedName("mobile")
        private String mobile;


        public ServerRegisterRequest(
                String mobile) {
            this.mobile = mobile;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerRegisterRequest that = (ServerRegisterRequest) object;

            if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) {
                return false;
            }
            return mobile != null ? mobile.equals(that.mobile) : that.mobile == null;
        }

        @Override
        public int hashCode() {
            int result = mobile != null ? mobile.hashCode() : 0;
            result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
            return result;
        }
    }

    public static class ServerVerifyOtpRequest {

        @Expose
        @SerializedName("mobile")
        private String mobile;

        @Expose
        @SerializedName("otp")
        private String otp;

        @Expose
        @SerializedName("token_no")
        public String token_no;

        @Expose
        @SerializedName("device_id")
        public String device_id;

        @Expose
        @SerializedName("os_type")
        public String os_type;

        public ServerVerifyOtpRequest(String mobile, String otp, String token_no,
                                      String device_id, String os_type) {
            this.mobile = mobile;
            this.otp = otp;
            this.token_no = token_no;
            this.device_id = device_id;
            this.os_type = os_type;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerVerifyOtpRequest that = (ServerVerifyOtpRequest) object;

            if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) {
                return false;
            }
            return mobile != null ? mobile.equals(that.mobile) : that.mobile == null;
        }

        @Override
        public int hashCode() {
            int result = mobile != null ? mobile.hashCode() : 0;
            result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
            return result;
        }
    }

    public static class ServerResendOtpRequest {

        @Expose
        @SerializedName("id")
        private String id;


        public ServerResendOtpRequest(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerResendOtpRequest that = (ServerResendOtpRequest) object;

            if (id != null ? !id.equals(that.id) : that.id != null) {
                return false;
            }
            return id != null ? id.equals(that.id) : that.id == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (id != null ? id.hashCode() : 0);
            return result;
        }
    }

    public static class ServerCreateProfileRequest {

        @Expose
        @SerializedName("mobile")
        private String mobile;

        @Expose
        @SerializedName("full_name")
        private String full_name;

        @Expose
        @SerializedName("email_id")
        private String email_id;

        @Expose
        @SerializedName("gender")
        private String gender;

        @Expose
        @SerializedName("referral_code")
        private String referral_code;




        public ServerCreateProfileRequest(
                String mobile,
                String full_name,
                String email_id,
                String gender,
                String referral_code
                ) {
            this.mobile = mobile;
            this.full_name = full_name;
            this.email_id = email_id;
            this.gender = gender;
            this.referral_code = referral_code;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerCreateProfileRequest that = (ServerCreateProfileRequest) object;

            if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) {
                return false;
            }
            return mobile != null ? mobile.equals(that.mobile) : that.mobile == null;
        }

        @Override
        public int hashCode() {
            int result = mobile != null ? mobile.hashCode() : 0;
            result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
            return result;
        }
    }


    public static class ServerUpdatePasswordRequest {

        @Expose
        @SerializedName("oldPassword")
        private String oldPassword;

        @Expose
        @SerializedName("newPassword")
        private String newPassword;

        @Expose
        @SerializedName("opId")
        public String opId;





        public ServerUpdatePasswordRequest(  String oldPassword,
                                            String newPassword,
                                            String opId) {
            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
            this.opId = opId;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerUpdatePasswordRequest that = (ServerUpdatePasswordRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }

    public static class ServerUpdateFullNameRequest {

        @Expose
        @SerializedName("fullName")
        private String fullName;



        @Expose
        @SerializedName("opId")
        public String opId;





        public ServerUpdateFullNameRequest(  String fullName,
                                             String opId) {
            this.fullName = fullName;
            this.opId = opId;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerUpdateFullNameRequest that = (ServerUpdateFullNameRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }

    public static class ServerFinishProfileRequest {

        @Expose
        @SerializedName("opId")
        private String opId;

        @Expose
        @SerializedName("dob")
        private String dob;

        @Expose
        @SerializedName("pan_number")
        private String pan_number;

        @Expose
        @SerializedName("pan_image")
        private String pan_image;

        @Expose
        @SerializedName("adhaar_number")
        private String adhaar_number;

        @Expose
        @SerializedName("adhaar_front")
        private String adhaar_front;

        @Expose
        @SerializedName("adhaar_back")
        private String adhaar_back;

        @Expose
        @SerializedName("gender")
        private String gender;

        @Expose
        @SerializedName("profilePic")
        private String profilePic;




        public ServerFinishProfileRequest(  String opId,
                                            String dob,
                                            String pan_number,
                                            String pan_image,
                                            String adhaar_number,
                                            String adhaar_front,
                                            String adhaar_back,
                                            String gender,
                                            String profilePic
        ) {
            this.opId = opId;
            this.dob = dob;
            this.pan_number = pan_number;
            this.pan_image = pan_image;
            this.adhaar_number = adhaar_number;
            this.adhaar_front = adhaar_front;
            this.adhaar_back = adhaar_back;
            this.gender = gender;
            this.profilePic = profilePic;

        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerFinishProfileRequest that = (ServerFinishProfileRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }

    public static class ServerUpdatePersonalProfileRequest {

        @Expose
        @SerializedName("opId")
        private String opId;

        @Expose
        @SerializedName("dob")
        private String dob;

        @Expose
        @SerializedName("pan_number")
        private String pan_number;

        @Expose
        @SerializedName("pan_image")
        private String pan_image;

        @Expose
        @SerializedName("adhaar_number")
        private String adhaar_number;

        @Expose
        @SerializedName("adhaar_front")
        private String adhaar_front;

        @Expose
        @SerializedName("adhaar_back")
        private String adhaar_back;

        @Expose
        @SerializedName("gender")
        private String gender;



        public ServerUpdatePersonalProfileRequest(  String opId,
                                            String dob,
                                            String pan_number,
                                            String pan_image,
                                            String adhaar_number,
                                            String adhaar_front,
                                            String adhaar_back,
                                            String gender
        ) {
            this.opId = opId;
            this.dob = dob;
            this.pan_number = pan_number;
            this.pan_image = pan_image;
            this.adhaar_number = adhaar_number;
            this.adhaar_front = adhaar_front;
            this.adhaar_back = adhaar_back;
            this.gender = gender;

        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerUpdatePersonalProfileRequest that = (ServerUpdatePersonalProfileRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }

    public static class ServerUpdateAddressRequest {

        @Expose
        @SerializedName("opId")
        private String opId;

        @Expose
        @SerializedName("address")
        private String address;

        @Expose
        @SerializedName("address_lat")
        private String address_lat;

        @Expose
        @SerializedName("address_long")
        private String address_long;

        @Expose
        @SerializedName("address_type")
        private String address_type;


        public ServerUpdateAddressRequest(  String opId,
                                            String address,
                                            String address_lat,
                                            String address_long,
                                            String address_type) {
            this.opId = opId;
            this.address = address;
            this.address_lat = address_lat;
            this.address_long = address_long;
            this.address_type = address_type;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerUpdateAddressRequest that = (ServerUpdateAddressRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }

    public static class ServerForgotPasswordRequest {

        @Expose
        @SerializedName("mobile")
        private String mobile;


        public ServerForgotPasswordRequest(String mobile) {
            this.mobile = mobile;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerForgotPasswordRequest that = (ServerForgotPasswordRequest) object;

            if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) {
                return false;
            }
            return mobile != null ? mobile.equals(that.mobile) : that.mobile == null;
        }

        @Override
        public int hashCode() {
            int result = mobile != null ? mobile.hashCode() : 0;
            result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
            return result;
        }
    }


    public static class ServerUpdateBankAccountRequest {

        @Expose
        @SerializedName("opId")
        private String opId;

        @Expose
        @SerializedName("account_no")
        private String account_no;

        @Expose
        @SerializedName("account_ifsc")
        private String account_ifsc;

        @Expose
        @SerializedName("account_name")
        private String account_name;


        public ServerUpdateBankAccountRequest(String opId,
                                            String account_no,
                                            String account_ifsc,
                                            String account_name) {
            this.opId = opId;
            this.account_no = account_no;
            this.account_ifsc = account_ifsc;
            this.account_name = account_name;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerUpdateBankAccountRequest that = (ServerUpdateBankAccountRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }

    public static class GetUserProfileRequest {

        @Expose
        @SerializedName("opId")
        private String opId;


        public GetUserProfileRequest(String opId) {
            this.opId = opId;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            GetUserProfileRequest that = (GetUserProfileRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }

    public static class ServerUpdateTshirtRequest {

        @Expose
        @SerializedName("opId")
        private String opId;

        @Expose
        @SerializedName("tshirt_size")
        private String tshirt_size;


        public ServerUpdateTshirtRequest(String opId,
                                              String tshirt_size) {
            this.opId = opId;
            this.tshirt_size = tshirt_size;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerUpdateTshirtRequest that = (ServerUpdateTshirtRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }

    public static class ServerUpdateJobAddressRequest {

        @Expose
        @SerializedName("opId")
        private String opId;

        @Expose
        @SerializedName("address_type")
        private String address_type;


        public ServerUpdateJobAddressRequest(String opId,
                                         String address_type) {
            this.opId = opId;
            this.address_type = address_type;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerUpdateJobAddressRequest that = (ServerUpdateJobAddressRequest) object;

            if (opId != null ? !opId.equals(that.opId) : that.opId != null) {
                return false;
            }
            return opId != null ? opId.equals(that.opId) : that.opId == null;
        }

        @Override
        public int hashCode() {
            int result = opId != null ? opId.hashCode() : 0;
            result = 31 * result + (opId != null ? opId.hashCode() : 0);
            return result;
        }
    }


    public static class ServerGetWeatherRequest {

        @Expose
        @SerializedName("lat")
        private String lat;

        @Expose
        @SerializedName("long")
        private String longitude;


        public ServerGetWeatherRequest(String lat, String longitude) {
            this.lat = lat;
            this.longitude = longitude;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServerGetWeatherRequest that = (ServerGetWeatherRequest) object;

            if (lat != null ? !lat.equals(that.lat) : that.lat != null) {
                return false;
            }
            return lat != null ? lat.equals(that.lat) : that.lat == null;
        }

        @Override
        public int hashCode() {
            int result = lat != null ? lat.hashCode() : 0;
            result = 31 * result + (lat != null ? lat.hashCode() : 0);
            return result;
        }
    }



}
