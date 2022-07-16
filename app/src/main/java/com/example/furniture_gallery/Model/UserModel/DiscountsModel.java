package com.example.furniture_gallery.Model.UserModel;

import com.example.furniture_gallery.Model.UserResponseModel.DiscountHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscountsModel {

    @SerializedName("data")
    @Expose
    private List<DiscountHomeResponseModel> discounts = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<DiscountHomeResponseModel> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountHomeResponseModel> discounts) {
        this.discounts = discounts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
