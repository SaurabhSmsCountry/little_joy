package com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThankYouResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
}
