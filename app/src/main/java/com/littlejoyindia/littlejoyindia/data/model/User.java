package com.littlejoyindia.littlejoyindia.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("referral_code")
    @Expose
    private String referralCode;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("busniess_account")
    @Expose
    private Integer busniessAccount;
    @SerializedName("upgrade_date")
    @Expose
    private String upgradeDate;
    @SerializedName("token_no")
    @Expose
    private String tokenNo;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("otpCode")
    @Expose
    private String otpCode;
    @SerializedName("os_type")
    @Expose
    private String osType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBusniessAccount() {
        return busniessAccount;
    }

    public void setBusniessAccount(Integer busniessAccount) {
        this.busniessAccount = busniessAccount;
    }

    public String getUpgradeDate() {
        return upgradeDate;
    }

    public void setUpgradeDate(String upgradeDate) {
        this.upgradeDate = upgradeDate;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }
}
