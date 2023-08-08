package com.littlejoyindia.littlejoyindia.data.model.onlineShopping;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductListResponse {


    public static class Datum implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("product_title")
        @Expose
        private String productTitle;
        @SerializedName("product_category")
        @Expose
        private String productCategory;
        @SerializedName("product_subcat")
        @Expose
        private String productSubcat;
        @SerializedName("product_quantity")
        @Expose
        private String productQuantity;
        @SerializedName("product_size_qty")
        @Expose
        private String productSizeQty;
        @SerializedName("product_actual_price")
        @Expose
        private Integer productActualPrice;
        @SerializedName("product_selling_price")
        @Expose
        private Integer productSellingPrice;
        @SerializedName("product_image")
        @Expose
        private List<String> productImage = null;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("product_slug")
        @Expose
        private String productSlug;
        @SerializedName("product_code")
        @Expose
        private String productCode;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("salon_cod")
        @Expose
        private String salonCod;
        @SerializedName("is_offer")
        @Expose
        private Integer isOffer;
        @SerializedName("catalog_id")
        @Expose
        private Object catalogId;
        @SerializedName("avg_rating")
        @Expose
        private String avgRating;
        @SerializedName("related_product")
        @Expose
        private List<Datum> relatedProduct = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProductTitle() {
            return productTitle;
        }

        public void setProductTitle(String productTitle) {
            this.productTitle = productTitle;
        }

        public String getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(String productCategory) {
            this.productCategory = productCategory;
        }

        public String getProductSubcat() {
            return productSubcat;
        }

        public void setProductSubcat(String productSubcat) {
            this.productSubcat = productSubcat;
        }

        public String getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(String productQuantity) {
            this.productQuantity = productQuantity;
        }

        public String getProductSizeQty() {
            return productSizeQty;
        }

        public void setProductSizeQty(String productSizeQty) {
            this.productSizeQty = productSizeQty;
        }

        public Integer getProductActualPrice() {
            return productActualPrice;
        }

        public void setProductActualPrice(Integer productActualPrice) {
            this.productActualPrice = productActualPrice;
        }

        public Integer getProductSellingPrice() {
            return productSellingPrice;
        }

        public void setProductSellingPrice(Integer productSellingPrice) {
            this.productSellingPrice = productSellingPrice;
        }

        public List<String> getProductImage() {
            return productImage;
        }

        public void setProductImage(List<String> productImage) {
            this.productImage = productImage;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProductSlug() {
            return productSlug;
        }

        public void setProductSlug(String productSlug) {
            this.productSlug = productSlug;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSalonCod() {
            return salonCod;
        }

        public void setSalonCod(String salonCod) {
            this.salonCod = salonCod;
        }

        public Integer getIsOffer() {
            return isOffer;
        }

        public void setIsOffer(Integer isOffer) {
            this.isOffer = isOffer;
        }

        public Object getCatalogId() {
            return catalogId;
        }

        public void setCatalogId(Object catalogId) {
            this.catalogId = catalogId;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public List<Datum> getRelatedProduct() {
            return relatedProduct;
        }

        public void setRelatedProduct(List<Datum> relatedProduct) {
            this.relatedProduct = relatedProduct;
        }

    }


    public static class ProductList {

        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
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
