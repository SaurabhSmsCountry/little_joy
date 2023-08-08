package com.littlejoyindia.littlejoyindia.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Deal implements Serializable {

    @SerializedName("isAddedInCart")
    @Expose
    private Integer isAddedInCart;
    @SerializedName("deal_title")
    @Expose
    private String dealTitle;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("popular_service")
    @Expose
    private Integer popularService;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("top_brand")
    @Expose
    private String topBrand;
    @SerializedName("actual_price")
    @Expose
    private Integer actualPrice;
    @SerializedName("selling_price")
    @Expose
    private Integer sellingPrice;
    @SerializedName("deals_image")
    @Expose
    private String dealsImage;
    @SerializedName("avgRating")
    @Expose
    private Double avgRating;
    @SerializedName("paid_amount")
    @Expose
    private Double paidAmount;
    @SerializedName("pay_merchant")
    @Expose
    private Double payMerchant;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public Integer getIsAddedInCart() {
        return isAddedInCart;
    }

    public void setIsAddedInCart(Integer isAddedInCart) {
        this.isAddedInCart = isAddedInCart;
    }

    public String getDealTitle() {
        return dealTitle;
    }

    public void setDealTitle(String dealTitle) {
        this.dealTitle = dealTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPopularService() {
        return popularService;
    }

    public void setPopularService(Integer popularService) {
        this.popularService = popularService;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTopBrand() {
        return topBrand;
    }

    public void setTopBrand(String topBrand) {
        this.topBrand = topBrand;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getDealsImage() {
        return dealsImage;
    }

    public void setDealsImage(String dealsImage) {
        this.dealsImage = dealsImage;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getPayMerchant() {
        return payMerchant;
    }

    public void setPayMerchant(Double payMerchant) {
        this.payMerchant = payMerchant;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}