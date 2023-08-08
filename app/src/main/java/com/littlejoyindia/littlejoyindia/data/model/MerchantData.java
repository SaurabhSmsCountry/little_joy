package com.littlejoyindia.littlejoyindia.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MerchantData implements Serializable {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("active_icon")
    @Expose
    private String activeIcon;
    @SerializedName("inactive_icon")
    @Expose
    private String inactiveIcon;
    @SerializedName("deals")
    @Expose
    private List<Deal> deals = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActiveIcon() {
        return activeIcon;
    }

    public void setActiveIcon(String activeIcon) {
        this.activeIcon = activeIcon;
    }

    public String getInactiveIcon() {
        return inactiveIcon;
    }

    public void setInactiveIcon(String inactiveIcon) {
        this.inactiveIcon = inactiveIcon;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

}