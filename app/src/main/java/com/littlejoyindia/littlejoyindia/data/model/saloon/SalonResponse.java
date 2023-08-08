package com.littlejoyindia.littlejoyindia.data.model.saloon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.data.model.DealsTopBrands;
import com.littlejoyindia.littlejoyindia.data.model.Errors;
import com.littlejoyindia.littlejoyindia.data.model.MerchantData;
import com.littlejoyindia.littlejoyindia.data.model.Meta;
import com.littlejoyindia.littlejoyindia.data.model.User;

import java.util.List;

public class SalonResponse {

    private SalonResponse(){

    }

    public static class SalonAllServicesResponse {
        @SerializedName("data")
        @Expose
        private List<SalonServiceModel> data = null;
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
        @SerializedName("Total")
        @Expose
        private Integer total;
        @SerializedName("Qty")
        @Expose
        private Integer qty;

        public List<SalonServiceModel> getData() {
            return data;
        }

        public void setData(List<SalonServiceModel> data) {
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

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }
    }


    public static class SaloonServiceCityResponse {
        @SerializedName("data")
        @Expose
        private List<String> data = null;
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



        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
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

    public static class CartListingServicesResponse {
        @SerializedName("data")
        @Expose
        private List<CartModel> data = null;
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
        @SerializedName("Total")
        @Expose
        private Integer total;

        public List<CartModel> getData() {
            return data;
        }

        public void setData(List<CartModel> data) {
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

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }
    }

    public static class SavedAddressResponse {
        @SerializedName("data")
        @Expose
        private List<Address> data = null;
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
        @SerializedName("Total")
        @Expose
        private Integer total;

        public List<Address> getData() {
            return data;
        }

        public void setData(List<Address> data) {
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

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }
    }

    public static class WalletTotalResponse {
        @SerializedName("data")
        @Expose
        private List<Wallet> data = null;
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

        public List<Wallet> getData() {
            return data;
        }

        public void setData(List<Wallet> data) {
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

    public static class DealsTopBrandsResponse {
        @SerializedName("data")
        @Expose
        private List<DealsTopBrands> data = null;
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

        public List<DealsTopBrands> getData() {
            return data;
        }

        public void setData(List<DealsTopBrands> data) {
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

    public static class DealsBannerResponse {
        @SerializedName("data")
        @Expose
        private List<DealsBanner> data = null;
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

        public List<DealsBanner> getData() {
            return data;
        }

        public void setData(List<DealsBanner> data) {
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




        public class DealsBanner {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("url")
            @Expose
            private String url;
            @SerializedName("date")
            @Expose
            private String date;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

        }

    }


    public static class MerchantDeatilsByIdResponse {
        @SerializedName("data")
        @Expose
        private List<MerchantData> data = null;
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

        public List<MerchantData> getData() {
            return data;
        }

        public void setData(List<MerchantData> data) {
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

        public class MerchantData {

            @SerializedName("mer_id")
            @Expose
            private Integer merId;
            @SerializedName("mer_address")
            @Expose
            private String merAddress;
            @SerializedName("mer_city")
            @Expose
            private String merCity;
            @SerializedName("mer_latitude")
            @Expose
            private String merLatitude;
            @SerializedName("mer_longitude")
            @Expose
            private String merLongitude;
            @SerializedName("contact")
            @Expose
            private String contact;
            @SerializedName("contact2")
            @Expose
            private String contact2;
            @SerializedName("business_hour")
            @Expose
            private String businessHour;
            @SerializedName("mer_ac")
            @Expose
            private String merAc;
            @SerializedName("mer_wifi")
            @Expose
            private String merWifi;
            @SerializedName("mer_parking")
            @Expose
            private String merParking;
            @SerializedName("mer_home_ser")
            @Expose
            private String merHomeSer;
            @SerializedName("category")
            @Expose
            private String category;
            @SerializedName("top_brand")
            @Expose
            private String topBrand;
            @SerializedName("merAvgRating")
            @Expose
            private Integer merAvgRating;
            @SerializedName("distance")
            @Expose
            private Double distance;
            @SerializedName("merchant_img")
            @Expose
            private String merchantImg;

            public Integer getMerId() {
                return merId;
            }

            public void setMerId(Integer merId) {
                this.merId = merId;
            }

            public String getMerAddress() {
                return merAddress;
            }

            public void setMerAddress(String merAddress) {
                this.merAddress = merAddress;
            }

            public String getMerCity() {
                return merCity;
            }

            public void setMerCity(String merCity) {
                this.merCity = merCity;
            }

            public String getMerLatitude() {
                return merLatitude;
            }

            public void setMerLatitude(String merLatitude) {
                this.merLatitude = merLatitude;
            }

            public String getMerLongitude() {
                return merLongitude;
            }

            public void setMerLongitude(String merLongitude) {
                this.merLongitude = merLongitude;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getContact2() {
                return contact2;
            }

            public void setContact2(String contact2) {
                this.contact2 = contact2;
            }

            public String getBusinessHour() {
                return businessHour;
            }

            public void setBusinessHour(String businessHour) {
                this.businessHour = businessHour;
            }

            public String getMerAc() {
                return merAc;
            }

            public void setMerAc(String merAc) {
                this.merAc = merAc;
            }

            public String getMerWifi() {
                return merWifi;
            }

            public void setMerWifi(String merWifi) {
                this.merWifi = merWifi;
            }

            public String getMerParking() {
                return merParking;
            }

            public void setMerParking(String merParking) {
                this.merParking = merParking;
            }

            public String getMerHomeSer() {
                return merHomeSer;
            }

            public void setMerHomeSer(String merHomeSer) {
                this.merHomeSer = merHomeSer;
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

            public Integer getMerAvgRating() {
                return merAvgRating;
            }

            public void setMerAvgRating(Integer merAvgRating) {
                this.merAvgRating = merAvgRating;
            }

            public Double getDistance() {
                return distance;
            }

            public void setDistance(Double distance) {
                this.distance = distance;
            }

            public String getMerchantImg() {
                return merchantImg;
            }

            public void setMerchantImg(String merchantImg) {
                this.merchantImg = merchantImg;
            }

        }

    }

    public static class MerchantDealsByIdResponse {

        @SerializedName("data")
        @Expose
        private List<MerchantData> data = null;
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
        @SerializedName("Total")
        @Expose
        private Integer total;
        @SerializedName("Qty")
        @Expose
        private Integer qty;

        public List<MerchantData> getData() {
            return data;
        }

        public void setData(List<MerchantData> data) {
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

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }




    }

    public static class DealsHomeResponse {
        @SerializedName("data")
        @Expose
        private Data data = null;
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

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
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


        public class Data {

            @SerializedName("popular")
            @Expose
            private List<Popular> popular = null;
            @SerializedName("merchants")
            @Expose
            private List<Merchant> merchants = null;

            public List<Popular> getPopular() {
                return popular;
            }

            public void setPopular(List<Popular> popular) {
                this.popular = popular;
            }

            public List<Merchant> getMerchants() {
                return merchants;
            }

            public void setMerchants(List<Merchant> deals) {
                this.merchants = deals;
            }

        }

        public class Popular {

            @SerializedName("merchant_img")
            @Expose
            private String merchantImg;
            @SerializedName("mer_id")
            @Expose
            private Integer merId;
            @SerializedName("mer_address")
            @Expose
            private String merAddress;
            @SerializedName("mer_city")
            @Expose
            private String merCity;
            @SerializedName("mer_name")
            @Expose
            private String merName;
            @SerializedName("mer_business")
            @Expose
            private String merBusiness;
            @SerializedName("top_brand")
            @Expose
            private String topBrand;
            @SerializedName("popular_service")
            @Expose
            private Integer popularService;
            @SerializedName("mer_latitude")
            @Expose
            private String merLatitude;
            @SerializedName("mer_longitude")
            @Expose
            private String merLongitude;
            @SerializedName("distance")
            @Expose
            private Integer distance;
            @SerializedName("merAvgRating")
            @Expose
            private Integer merAvgRating;

            public String getMerchantImg() {
                return merchantImg;
            }

            public void setMerchantImg(String merchantImg) {
                this.merchantImg = merchantImg;
            }

            public Integer getMerId() {
                return merId;
            }

            public void setMerId(Integer merId) {
                this.merId = merId;
            }

            public String getMerAddress() {
                return merAddress;
            }

            public void setMerAddress(String merAddress) {
                this.merAddress = merAddress;
            }

            public String getMerCity() {
                return merCity;
            }

            public void setMerCity(String merCity) {
                this.merCity = merCity;
            }

            public String getMerName() {
                return merName;
            }

            public void setMerName(String merName) {
                this.merName = merName;
            }

            public String getMerBusiness() {
                return merBusiness;
            }

            public void setMerBusiness(String merBusiness) {
                this.merBusiness = merBusiness;
            }

            public String getTopBrand() {
                return topBrand;
            }

            public void setTopBrand(String topBrand) {
                this.topBrand = topBrand;
            }

            public Integer getPopularService() {
                return popularService;
            }

            public void setPopularService(Integer popularService) {
                this.popularService = popularService;
            }

            public String getMerLatitude() {
                return merLatitude;
            }

            public void setMerLatitude(String merLatitude) {
                this.merLatitude = merLatitude;
            }

            public String getMerLongitude() {
                return merLongitude;
            }

            public void setMerLongitude(String merLongitude) {
                this.merLongitude = merLongitude;
            }

            public Integer getDistance() {
                return distance;
            }

            public void setDistance(Integer distance) {
                this.distance = distance;
            }

            public Integer getMerAvgRating() {
                return merAvgRating;
            }

            public void setMerAvgRating(Integer merAvgRating) {
                this.merAvgRating = merAvgRating;
            }

        }


        public class Merchant {

            @SerializedName("merchant_img")
            @Expose
            private String merchantImg;
            @SerializedName("mer_id")
            @Expose
            private Integer merId;
            @SerializedName("mer_address")
            @Expose
            private String merAddress;
            @SerializedName("mer_city")
            @Expose
            private String merCity;
            @SerializedName("mer_name")
            @Expose
            private String merName;
            @SerializedName("mer_business")
            @Expose
            private String merBusiness;
            @SerializedName("top_brand")
            @Expose
            private String topBrand;
            @SerializedName("popular_service")
            @Expose
            private Integer popularService;
            @SerializedName("mer_latitude")
            @Expose
            private String merLatitude;
            @SerializedName("mer_longitude")
            @Expose
            private String merLongitude;
            @SerializedName("distance")
            @Expose
            private Double distance;
            @SerializedName("merAvgRating")
            @Expose
            private Integer merAvgRating;

            public String getMerchantImg() {
                return merchantImg;
            }

            public void setMerchantImg(String merchantImg) {
                this.merchantImg = merchantImg;
            }

            public Integer getMerId() {
                return merId;
            }

            public void setMerId(Integer merId) {
                this.merId = merId;
            }

            public String getMerAddress() {
                return merAddress;
            }

            public void setMerAddress(String merAddress) {
                this.merAddress = merAddress;
            }

            public String getMerCity() {
                return merCity;
            }

            public void setMerCity(String merCity) {
                this.merCity = merCity;
            }

            public String getMerName() {
                return merName;
            }

            public void setMerName(String merName) {
                this.merName = merName;
            }

            public String getMerBusiness() {
                return merBusiness;
            }

            public void setMerBusiness(String merBusiness) {
                this.merBusiness = merBusiness;
            }

            public String getTopBrand() {
                return topBrand;
            }

            public void setTopBrand(String topBrand) {
                this.topBrand = topBrand;
            }

            public Integer getPopularService() {
                return popularService;
            }

            public void setPopularService(Integer popularService) {
                this.popularService = popularService;
            }

            public String getMerLatitude() {
                return merLatitude;
            }

            public void setMerLatitude(String merLatitude) {
                this.merLatitude = merLatitude;
            }

            public String getMerLongitude() {
                return merLongitude;
            }

            public void setMerLongitude(String merLongitude) {
                this.merLongitude = merLongitude;
            }

            public Double getDistance() {
                return distance;
            }

            public void setDistance(Double distance) {
                this.distance = distance;
            }

            public Integer getMerAvgRating() {
                return merAvgRating;
            }

            public void setMerAvgRating(Integer merAvgRating) {
                this.merAvgRating = merAvgRating;
            }

        }



    }

}
