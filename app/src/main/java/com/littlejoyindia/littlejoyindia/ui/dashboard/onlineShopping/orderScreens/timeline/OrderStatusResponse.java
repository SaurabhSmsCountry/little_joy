package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.timeline;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Keep
public class OrderStatusResponse {
    @SerializedName("data")
    @Expose
    private StatusData data;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public StatusData getData() {
        return data;
    }

    public void setData(StatusData data) {
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

    @Keep
    public static class StatusData {

        public StatusData() {
        }

        @SerializedName("order_status")
        @Expose
        private String orderStatus;
        @SerializedName("updation_date")
        @Expose
        private String updationDate;
        @SerializedName("ship_courier_name")
        @Expose
        private String shipCourierName;
        @SerializedName("ship_tracking_id")
        @Expose
        private String shipTrackingId;
        @SerializedName("ship_tracking_link")
        @Expose
        private String shipTrackingLink;
        @SerializedName("order_accepted")
        @Expose
        private Boolean orderAccepted;
        @SerializedName("order_progress")
        @Expose
        private Boolean orderProgress;
        @SerializedName("order_shipped")
        @Expose
        private Boolean orderShipped;
        @SerializedName("order_delivered")
        @Expose
        private Boolean orderDelivered;
        @SerializedName("order_completed")
        @Expose
        private Boolean orderCompleted;
        @SerializedName("order_canceled")
        @Expose
        private Boolean orderCanceled;

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getUpdationDate() {
            return updationDate;
        }

        public void setUpdationDate(String updationDate) {
            this.updationDate = updationDate;
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

        public Boolean getOrderAccepted() {
            return orderAccepted;
        }

        public void setOrderAccepted(Boolean orderAccepted) {
            this.orderAccepted = orderAccepted;
        }

        public Boolean getOrderProgress() {
            return orderProgress;
        }

        public void setOrderProgress(Boolean orderProgress) {
            this.orderProgress = orderProgress;
        }

        public Boolean getOrderShipped() {
            return orderShipped;
        }

        public void setOrderShipped(Boolean orderShipped) {
            this.orderShipped = orderShipped;
        }

        public Boolean getOrderDelivered() {
            return orderDelivered;
        }

        public void setOrderDelivered(Boolean orderDelivered) {
            this.orderDelivered = orderDelivered;
        }

        public Boolean getOrderCompleted() {
            return orderCompleted;
        }

        public void setOrderCompleted(Boolean orderCompleted) {
            this.orderCompleted = orderCompleted;
        }

        public Boolean getOrderCanceled() {
            return orderCanceled;
        }

        public void setOrderCanceled(Boolean orderCanceled) {
            this.orderCanceled = orderCanceled;
        }

        @Override
        public String toString() {
            return "StatusData{" +
                    "orderStatus='" + orderStatus + '\'' +
                    ", updationDate='" + updationDate + '\'' +
                    ", shipCourierName='" + shipCourierName + '\'' +
                    ", shipTrackingId='" + shipTrackingId + '\'' +
                    ", shipTrackingLink='" + shipTrackingLink + '\'' +
                    ", orderAccepted=" + orderAccepted +
                    ", orderProgress=" + orderProgress +
                    ", orderShipped=" + orderShipped +
                    ", orderDelivered=" + orderDelivered +
                    ", orderCompleted=" + orderCompleted +
                    ", orderCanceled=" + orderCanceled +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderStatusResponse{" +
                "data=" + data.toString() +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
