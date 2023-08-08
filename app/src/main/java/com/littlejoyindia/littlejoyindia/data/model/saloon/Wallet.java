
package com.littlejoyindia.littlejoyindia.data.model.saloon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Wallet {
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
