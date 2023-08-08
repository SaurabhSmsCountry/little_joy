
package com.littlejoyindia.littlejoyindia.data.model.saloon;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class SalonServiceModel implements Serializable {

    @SerializedName("cat")
    @Expose
    private String cat;
    @SerializedName("get_sub")
    @Expose
    private List<String> getSub = null;
    @SerializedName("services")
    @Expose
    private List<Service> services = null;

    public String getCat_normal_icon() {
        return cat_normal_icon;
    }

    public void setCat_normal_icon(String cat_normal_icon) {
        this.cat_normal_icon = cat_normal_icon;
    }

    public String getCat_active_icon() {
        return cat_active_icon;
    }

    public void setCat_active_icon(String cat_active_icon) {
        this.cat_active_icon = cat_active_icon;
    }

    @SerializedName("cat_normal_icon")
    @Expose
    private String cat_normal_icon;

    @SerializedName("cat_active_icon")
    @Expose
    private String cat_active_icon;



    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public List<String> getGetSub() {
        return getSub;
    }

    public void setGetSub(List<String> getSub) {
        this.getSub = getSub;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }


    public class Service  implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("sub_category")
        @Expose
        private String subCategory;
        @SerializedName("actual_price")
        @Expose
        private String actualPrice;
        @SerializedName("selling_price")
        @Expose
        private String sellingPrice;
        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("title_slug")
        @Expose
        private String titleSlug;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("status")
        @Expose
        private Integer status;

        public Integer getIsAddedInCart() {
            return isAddedInCart;
        }

        public void setIsAddedInCart(Integer isAddedInCart) {
            this.isAddedInCart = isAddedInCart;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        @SerializedName("isAddedInCart")
        @Expose
        private Integer isAddedInCart;
        @SerializedName("quantity")
        @Expose
        private Integer quantity;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(String subCategory) {
            this.subCategory = subCategory;
        }

        public String getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(String actualPrice) {
            this.actualPrice = actualPrice;
        }

        public String getSellingPrice() {
            return sellingPrice;
        }

        public void setSellingPrice(String sellingPrice) {
            this.sellingPrice = sellingPrice;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitleSlug() {
            return titleSlug;
        }

        public void setTitleSlug(String titleSlug) {
            this.titleSlug = titleSlug;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }



    }

}
