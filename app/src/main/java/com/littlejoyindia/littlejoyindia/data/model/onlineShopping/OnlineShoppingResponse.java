package com.littlejoyindia.littlejoyindia.data.model.onlineShopping;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnlineShoppingResponse {

    public OnlineShoppingResponse() {
    }

    public static class Datum {

        @SerializedName("category_id")
        @Expose
        private Integer categoryId;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("category_image")
        @Expose
        private String categoryImage;
        @SerializedName("sub_category")
        @Expose
        private List<SubCategory> subCategory = null;

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCategoryImage() {
            return categoryImage;
        }

        public void setCategoryImage(String categoryImage) {
            this.categoryImage = categoryImage;
        }

        public List<SubCategory> getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(List<SubCategory> subCategory) {
            this.subCategory = subCategory;
        }

    }


    public class Errors {
    }

    public static class Meta {

        @SerializedName("api_version")
        @Expose
        private String apiVersion;

        public String getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
        }

    }

    public static class ProductCategoryResponse {

        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
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

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
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

    public static class SubCategory {

        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("sub_category_image")
        @Expose
        private Object subCategoryImage;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Object getSubCategoryImage() {
            return subCategoryImage;
        }

        public void setSubCategoryImage(Object subCategoryImage) {
            this.subCategoryImage = subCategoryImage;
        }

    }
}