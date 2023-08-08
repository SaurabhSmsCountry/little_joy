package com.littlejoyindia.littlejoyindia.data.model.saloon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class SalonRequest {

    private SalonRequest() {
        // This class is not publicly instantiable
    }

    public static class GetAllSalonServicesRequest {

        @Expose
        @SerializedName("userId")
        public String userId;


        public GetAllSalonServicesRequest(String userId) {
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

            GetAllSalonServicesRequest that = (GetAllSalonServicesRequest) object;

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


    public static class GetSaloonRequest {



        public GetSaloonRequest() {

        }

    }

    public static class GetAnyInfoBasedOnUserId {

        @Expose
        @SerializedName("userId")
        public String userId;


        public GetAnyInfoBasedOnUserId(String userId) {
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

            GetAnyInfoBasedOnUserId that = (GetAnyInfoBasedOnUserId) object;

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

    public static class GetAnyInfoBasedOnUser_Id {

        @Expose
        @SerializedName("user_id")
        public String userId;


        public GetAnyInfoBasedOnUser_Id(String userId) {
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

            GetAnyInfoBasedOnUser_Id that = (GetAnyInfoBasedOnUser_Id) object;

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

    public static class GetDealsHomeBasedOnLocation {

        @Expose
        @SerializedName("city")
        public String city;

        @Expose
        @SerializedName("lat")
        public String lat;

        @Expose
        @SerializedName("lng")
        public String lng;


        public GetDealsHomeBasedOnLocation(String city, String lat, String lng) {
            this.city = city;
            this.lat = lat;
            this.lng = lng;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            GetDealsHomeBasedOnLocation that = (GetDealsHomeBasedOnLocation) object;

            if (city != null ? !city.equals(that.city) : that.city != null) {
                return false;
            }
            return city != null ? city.equals(that.city) : that.city == null;
        }

        @Override
        public int hashCode() {
            int result = city != null ? city.hashCode() : 0;
            result = 31 * result + (city != null ? city.hashCode() : 0);
            return result;
        }
    }

    public static class GetMerchantDetailsById {

        @Expose
        @SerializedName("mer_id")
        public String mer_id;

        @Expose
        @SerializedName("lat")
        public String lat;

        @Expose
        @SerializedName("lng")
        public String lng;


        public GetMerchantDetailsById(String mer_id, String lat, String lng) {
            this.mer_id = mer_id;
            this.lat = lat;
            this.lng = lng;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            GetMerchantDetailsById that = (GetMerchantDetailsById) object;

            if (mer_id != null ? !mer_id.equals(that.mer_id) : that.mer_id != null) {
                return false;
            }
            return mer_id != null ? mer_id.equals(that.mer_id) : that.mer_id == null;
        }

        @Override
        public int hashCode() {
            int result = mer_id != null ? mer_id.hashCode() : 0;
            result = 31 * result + (mer_id != null ? mer_id.hashCode() : 0);
            return result;
        }
    }

    public static class GetMerchantDealsByMerchant {

        @Expose
        @SerializedName("mer_id")
        public String mer_id;

        @Expose
        @SerializedName("userId")
        public String userId;



        public GetMerchantDealsByMerchant(String mer_id, String userId) {
            this.mer_id = mer_id;
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

            GetMerchantDealsByMerchant that = (GetMerchantDealsByMerchant) object;

            if (mer_id != null ? !mer_id.equals(that.mer_id) : that.mer_id != null) {
                return false;
            }
            return mer_id != null ? mer_id.equals(that.mer_id) : that.mer_id == null;
        }

        @Override
        public int hashCode() {
            int result = mer_id != null ? mer_id.hashCode() : 0;
            result = 31 * result + (mer_id != null ? mer_id.hashCode() : 0);
            return result;
        }
    }


    public static class AddAddressOnServer {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("house_no")
        @Expose
        private String houseNo;
        @SerializedName("address_type")
        @Expose
        private String addressType;
        @SerializedName("landmark")
        @Expose
        private String landmark;
        @SerializedName("complete_address")
        @Expose
        private String completeAddress;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("longitude")
        @Expose
        private Double longitude;
        @SerializedName("pincode")
        @Expose
        private Integer pincode;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getHouseNo() {
            return houseNo;
        }

        public void setHouseNo(String houseNo) {
            this.houseNo = houseNo;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getCompleteAddress() {
            return completeAddress;
        }

        public void setCompleteAddress(String completeAddress) {
            this.completeAddress = completeAddress;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Integer getPincode() {
            return pincode;
        }

        public void setPincode(Integer pincode) {
            this.pincode = pincode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public AddAddressOnServer(Integer userId, String houseNo, String addressType, String landmark, String completeAddress, Double latitude, Double longitude, Integer pincode, String state, String city) {
            this.userId = userId;
            this.houseNo = houseNo;
            this.addressType = addressType;
            this.landmark = landmark;
            this.completeAddress = completeAddress;
            this.latitude = latitude;
            this.longitude = longitude;
            this.pincode = pincode;
            this.state = state;
            this.city = city;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            AddAddressOnServer that = (AddAddressOnServer) object;

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


    public static class AddUpdateSalonCartServicesRequest {

        @Expose
        @SerializedName("ser_id")
        public String ser_id;

        @Expose
        @SerializedName("sell_price")
        public String sell_price;

        @Expose
        @SerializedName("ser_qty")
        public String ser_qty;

        @Expose
        @SerializedName("customer_id")
        public String customer_id;


        public AddUpdateSalonCartServicesRequest(String customer_id,
                                                 String ser_id,
                                                 String sell_price,
                                                 String ser_qty) {
            this.customer_id = customer_id;
            this.ser_id = ser_id;
            this.sell_price = sell_price;
            this.ser_qty = ser_qty;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            AddUpdateSalonCartServicesRequest that = (AddUpdateSalonCartServicesRequest) object;

            if (customer_id != null ? !customer_id.equals(that.customer_id) : that.customer_id != null) {
                return false;
            }
            return customer_id != null ? customer_id.equals(that.customer_id) : that.customer_id == null;
        }

        @Override
        public int hashCode() {
            int result = customer_id != null ? customer_id.hashCode() : 0;
            result = 31 * result + (customer_id != null ? customer_id.hashCode() : 0);
            return result;
        }
    }




    public static class AddUpdateSalonDealsRequest {

        @Expose
        @SerializedName("pro_id")
        public String pro_id;

        @Expose
        @SerializedName("qty")
        public String qty;

        @Expose
        @SerializedName("customer_id")
        public String customer_id;


        public AddUpdateSalonDealsRequest(String customer_id,
                                                 String pro_id,
                                                 String qty) {
            this.customer_id = customer_id;
            this.pro_id = pro_id;
            this.qty = qty;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            AddUpdateSalonDealsRequest that = (AddUpdateSalonDealsRequest) object;

            if (customer_id != null ? !customer_id.equals(that.customer_id) : that.customer_id != null) {
                return false;
            }
            return customer_id != null ? customer_id.equals(that.customer_id) : that.customer_id == null;
        }

        @Override
        public int hashCode() {
            int result = customer_id != null ? customer_id.hashCode() : 0;
            result = 31 * result + (customer_id != null ? customer_id.hashCode() : 0);
            return result;
        }
    }


    public static class RemoveCartItemDealsRequest {

        @Expose
        @SerializedName("pro_id")
        public String pro_id;

        @Expose
        @SerializedName("cart_id")
        public String cart_id;




        public RemoveCartItemDealsRequest(String cart_id,
                                             String pro_id
        ) {
            this.cart_id = cart_id;
            this.pro_id = pro_id;

        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            RemoveCartItemDealsRequest that = (RemoveCartItemDealsRequest) object;

            if (cart_id != null ? !cart_id.equals(that.cart_id) : that.cart_id != null) {
                return false;
            }
            return cart_id != null ? cart_id.equals(that.cart_id) : that.cart_id == null;
        }

        @Override
        public int hashCode() {
            int result = cart_id != null ? cart_id.hashCode() : 0;
            result = 31 * result + (cart_id != null ? cart_id.hashCode() : 0);
            return result;
        }
    }

    public static class RemoveCartItemServicesRequest {

        @Expose
        @SerializedName("ser_id")
        public String ser_id;

        @Expose
        @SerializedName("cart_id")
        public String cart_id;




        public RemoveCartItemServicesRequest(String cart_id,
                                                 String ser_id
                                                ) {
            this.cart_id = cart_id;
            this.ser_id = ser_id;

        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            RemoveCartItemServicesRequest that = (RemoveCartItemServicesRequest) object;

            if (cart_id != null ? !cart_id.equals(that.cart_id) : that.cart_id != null) {
                return false;
            }
            return cart_id != null ? cart_id.equals(that.cart_id) : that.cart_id == null;
        }

        @Override
        public int hashCode() {
            int result = cart_id != null ? cart_id.hashCode() : 0;
            result = 31 * result + (cart_id != null ? cart_id.hashCode() : 0);
            return result;
        }
    }

    public static class RemoveDealCartItemRequest {

        @Expose
        @SerializedName("pro_id")
        public String pro_id;

        @Expose
        @SerializedName("cart_id")
        public String cart_id;




        public RemoveDealCartItemRequest(String cart_id,
                                             String pro_id
        ) {
            this.cart_id = cart_id;
            this.pro_id = pro_id;

        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            RemoveDealCartItemRequest that = (RemoveDealCartItemRequest) object;

            if (cart_id != null ? !cart_id.equals(that.cart_id) : that.cart_id != null) {
                return false;
            }
            return cart_id != null ? cart_id.equals(that.cart_id) : that.cart_id == null;
        }

        @Override
        public int hashCode() {
            int result = cart_id != null ? cart_id.hashCode() : 0;
            result = 31 * result + (cart_id != null ? cart_id.hashCode() : 0);
            return result;
        }
    }




}
