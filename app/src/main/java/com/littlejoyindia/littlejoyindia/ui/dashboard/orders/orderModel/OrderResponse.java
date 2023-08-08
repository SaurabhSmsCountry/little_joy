package com.littlejoyindia.littlejoyindia.ui.dashboard.orders.orderModel;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class OrderResponse {
    @SerializedName("errors")
    @Expose
    private Errors errors;

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<Data> data;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Errors {}

    @Keep
    public static class Data {
        @SerializedName("order_id")
        @Expose
        private String order_id;

        @SerializedName("customer_phone")
        @Expose
        private String customer_phone;

        @SerializedName("customer_email")
        @Expose
        private String customer_email;

        @SerializedName("service_date") //2020-07-27
        @Expose
        private String service_date;

        @SerializedName("service_time") //09:00 AM - 09:30 AM
        @Expose
        private String service_time;

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

        @SerializedName("house_no")
        @Expose
        private String house_no;

        @SerializedName("address_type")
        @Expose
        private String address_type;

        @SerializedName("landmark")
        @Expose
        private String landmark;

        @SerializedName("latitude")
        @Expose
        private String latitude;

        @SerializedName("longitude")
        @Expose
        private String longitude;

        @SerializedName("city")
        @Expose
        private String city;

        @SerializedName("address_flag")
        @Expose
        private String address_flag;

        @SerializedName("paid_on") //2021-08-09T05:59:19.000Z
        @Expose
        private String paid_on;

        @SerializedName("pay_mode")
        @Expose
        private String pay_mode;

        @SerializedName("paid_amount")
        @Expose
        private String paid_amount;

        @SerializedName("total_amount")
        @Expose
        private String total_amount;

        @SerializedName("discount_percent")
        @Expose
        private String discount_percent;

        @SerializedName("discount_amount")
        @Expose
        private String discount_amount;

        @SerializedName("tax_percent")
        @Expose
        private String tax_percent;

        @SerializedName("tax_amount")
        @Expose
        private String tax_amount;

        @SerializedName("txn_id")
        @Expose
        private String txn_id;

        @SerializedName("canceled")
        @Expose
        private String canceled;

        @SerializedName("order_status")
        @Expose
        private String order_status;

        @SerializedName("product_details")
        @Expose
        private List<ProductDetails> product_details;

        @SerializedName("payment_status")
        @Expose
        private Integer payment_status;

        public Integer getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(Integer payment_status) {
            this.payment_status = payment_status;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getCustomer_phone() {
            return customer_phone;
        }

        public void setCustomer_phone(String customer_phone) {
            this.customer_phone = customer_phone;
        }

        public String getCustomer_email() {
            return customer_email;
        }

        public void setCustomer_email(String customer_email) {
            this.customer_email = customer_email;
        }

        public String getService_date() {
            return service_date;
        }

        public void setService_date(String service_date) {
            this.service_date = service_date;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
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

        public String getHouse_no() {
            return house_no;
        }

        public void setHouse_no(String house_no) {
            this.house_no = house_no;
        }

        public String getAddress_type() {
            return address_type;
        }

        public void setAddress_type(String address_type) {
            this.address_type = address_type;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress_flag() {
            return address_flag;
        }

        public void setAddress_flag(String address_flag) {
            this.address_flag = address_flag;
        }

        public String getPaid_on() {
            return paid_on;
        }

        public void setPaid_on(String paid_on) {
            this.paid_on = paid_on;
        }

        public String getPay_mode() {
            return pay_mode;
        }

        public void setPay_mode(String pay_mode) {
            this.pay_mode = pay_mode;
        }

        public String getPaid_amount() {
            return paid_amount;
        }

        public void setPaid_amount(String paid_amount) {
            this.paid_amount = paid_amount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getDiscount_percent() {
            return discount_percent;
        }

        public void setDiscount_percent(String discount_percent) {
            this.discount_percent = discount_percent;
        }

        public String getDiscount_amount() {
            return discount_amount;
        }

        public void setDiscount_amount(String discount_amount) {
            this.discount_amount = discount_amount;
        }

        public String getTax_percent() {
            return tax_percent;
        }

        public void setTax_percent(String tax_percent) {
            this.tax_percent = tax_percent;
        }

        public String getTax_amount() {
            return tax_amount;
        }

        public void setTax_amount(String tax_amount) {
            this.tax_amount = tax_amount;
        }

        public String getTxn_id() {
            return txn_id;
        }

        public void setTxn_id(String txn_id) {
            this.txn_id = txn_id;
        }

        public String getCanceled() {
            return canceled;
        }

        public void setCanceled(String canceled) {
            this.canceled = canceled;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public List<ProductDetails> getProduct_details() {
            return product_details;
        }

        public void setProduct_details(List<ProductDetails> product_details) {
            this.product_details = product_details;
        }
    }

    @Keep
    public static class ProductDetails {
        @SerializedName("ser_id")
        @Expose
        private String ser_id;

        @SerializedName("qty")
        @Expose
        private String qty;

        @SerializedName("price")
        @Expose
        private String price;

        @SerializedName("service")
        @Expose
        private String service;

        public String getSer_id() {
            return ser_id;
        }

        public void setSer_id(String ser_id) {
            this.ser_id = ser_id;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }
    }
}