package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.modals;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyShoppingOrderListResponse {

    @SerializedName("data")
    @Expose
    private List<ShoppingOrderData> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public List<ShoppingOrderData> getData() {
        return data;
    }

    public void setData(List<ShoppingOrderData> data) {
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

    public static class ShoppingOrderData {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("ship_name")
        @Expose
        private String shipName;
        @SerializedName("ship_email")
        @Expose
        private String shipEmail;
        @SerializedName("ship_address")
        @Expose
        private String shipAddress;
        @SerializedName("house_no")
        @Expose
        private String houseNo;
        @SerializedName("address_type")
        @Expose
        private String addressType;
        @SerializedName("ship_mobile")
        @Expose
        private String shipMobile;
        @SerializedName("ship_landmark")
        @Expose
        private String shipLandmark;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("ship_city")
        @Expose
        private String shipCity;
        @SerializedName("ship_pin")
        @Expose
        private String shipPin;
        @SerializedName("address_flag")
        @Expose
        private Integer addressFlag;
        @SerializedName("pay_mode")
        @Expose
        private String payMode;
        @SerializedName("total")
        @Expose
        private String total;
        @SerializedName("grand_total")
        @Expose
        private String grandTotal;
        @SerializedName("ship_charge")
        @Expose
        private String shipCharge;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("cart_id")
        @Expose
        private String cartId;
        @SerializedName("payment_status")
        @Expose
        private Integer paymentStatus;
        @SerializedName("order_status")
        @Expose
        private String orderStatus;
        @SerializedName("ship_courier_name")
        @Expose
        private String shipCourierName;
        @SerializedName("ship_tracking_id")
        @Expose
        private String shipTrackingId;
        @SerializedName("ship_tracking_link")
        @Expose
        private String shipTrackingLink;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("txn_id")
        @Expose
        private String txnId;
        @SerializedName("updation_date")
        @Expose
        private String updationDate;
        @SerializedName("canceled")
        @Expose
        private Integer canceled;
        @SerializedName("canceled_date")
        @Expose
        private String canceledDate;
        @SerializedName("cf_order_id")
        @Expose
        private String cfOrderId;
        @SerializedName("order_token")
        @Expose
        private String orderToken;
        @SerializedName("customer_number")
        @Expose
        private String customerNumber;
        @SerializedName("customer_email")
        @Expose
        private String customerEmail;
        @SerializedName("cart_item")
        @Expose
        private String cartItem;
        @SerializedName("customer_phone")
        @Expose
        private String customerPhone;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("landmark")
        @Expose
        private String landmark;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("paid_on")
        @Expose
        private String paidOn;
        @SerializedName("paid_amount")
        @Expose
        private String paidAmount;
        @SerializedName("total_amount")
        @Expose
        private String totalAmount;
        @SerializedName("product_details")
        @Expose
        private List<ProductDetail> productDetails = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getShipName() {
            return shipName;
        }

        public void setShipName(String shipName) {
            this.shipName = shipName;
        }

        public String getShipEmail() {
            return shipEmail;
        }

        public void setShipEmail(String shipEmail) {
            this.shipEmail = shipEmail;
        }

        public String getShipAddress() {
            return shipAddress;
        }

        public void setShipAddress(String shipAddress) {
            this.shipAddress = shipAddress;
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

        public String getShipMobile() {
            return shipMobile;
        }

        public void setShipMobile(String shipMobile) {
            this.shipMobile = shipMobile;
        }

        public String getShipLandmark() {
            return shipLandmark;
        }

        public void setShipLandmark(String shipLandmark) {
            this.shipLandmark = shipLandmark;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getShipCity() {
            return shipCity;
        }

        public void setShipCity(String shipCity) {
            this.shipCity = shipCity;
        }

        public String getShipPin() {
            return shipPin;
        }

        public void setShipPin(String shipPin) {
            this.shipPin = shipPin;
        }

        public Integer getAddressFlag() {
            return addressFlag;
        }

        public void setAddressFlag(Integer addressFlag) {
            this.addressFlag = addressFlag;
        }

        public String getPayMode() {
            return payMode;
        }

        public void setPayMode(String payMode) {
            this.payMode = payMode;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getGrandTotal() {
            return grandTotal;
        }

        public void setGrandTotal(String grandTotal) {
            this.grandTotal = grandTotal;
        }

        public String getShipCharge() {
            return shipCharge;
        }

        public void setShipCharge(String shipCharge) {
            this.shipCharge = shipCharge;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public Integer getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(Integer paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getShipCourierName() {
            return shipCourierName;
        }

        public void setShipCourierName(String shipCourierName) {
            this.shipCourierName = shipCourierName;
        }

        public String getShipTrackingId() {
            return shipTrackingId;
        }

        public void setShipTrackingId(String shipTrackingId) {
            this.shipTrackingId = shipTrackingId;
        }

        public String getShipTrackingLink() {
            return shipTrackingLink;
        }

        public void setShipTrackingLink(String shipTrackingLink) {
            this.shipTrackingLink = shipTrackingLink;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getTxnId() {
            return txnId;
        }

        public void setTxnId(String txnId) {
            this.txnId = txnId;
        }

        public String getUpdationDate() {
            return updationDate;
        }

        public void setUpdationDate(String updationDate) {
            this.updationDate = updationDate;
        }

        public Integer getCanceled() {
            return canceled;
        }

        public void setCanceled(Integer canceled) {
            this.canceled = canceled;
        }

        public String getCanceledDate() {
            return canceledDate;
        }

        public void setCanceledDate(String canceledDate) {
            this.canceledDate = canceledDate;
        }

        public String getCfOrderId() {
            return cfOrderId;
        }

        public void setCfOrderId(String cfOrderId) {
            this.cfOrderId = cfOrderId;
        }

        public String getOrderToken() {
            return orderToken;
        }

        public void setOrderToken(String orderToken) {
            this.orderToken = orderToken;
        }

        public String getCustomerNumber() {
            return customerNumber;
        }

        public void setCustomerNumber(String customerNumber) {
            this.customerNumber = customerNumber;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getCartItem() {
            return cartItem;
        }

        public void setCartItem(String cartItem) {
            this.cartItem = cartItem;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPaidOn() {
            return paidOn;
        }

        public void setPaidOn(String paidOn) {
            this.paidOn = paidOn;
        }

        public String getPaidAmount() {
            return paidAmount;
        }

        public void setPaidAmount(String paidAmount) {
            this.paidAmount = paidAmount;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<ProductDetail> getProductDetails() {
            return productDetails;
        }

        public void setProductDetails(List<ProductDetail> productDetails) {
            this.productDetails = productDetails;
        }

    }

    public static class ProductDetail implements Serializable {

        @SerializedName("pro_id")
        @Expose
        private String proId;
        @SerializedName("qty")
        @Expose
        private Integer qty;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("service")
        @Expose
        private String service;
        @SerializedName("product_image")
        @Expose
        private List<String> productImage;

        public String getProId() {
            return proId;
        }

        public void setProId(String proId) {
            this.proId = proId;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public List<String> getProductImage() {
            return productImage;
        }

        public void setProductImage(List<String> productImage) {
            this.productImage = productImage;
        }
    }

}

