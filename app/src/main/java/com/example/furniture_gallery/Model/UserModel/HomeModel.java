package com.example.furniture_gallery.Model.UserModel;

import com.example.furniture_gallery.Model.UserResponseModel.BranchTypeHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.CategoryHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.DiscountHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.OfferHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.SavesDiscountHomeResponseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModel {

    @SerializedName("data")
    @Expose
    private HomeResponseModel data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public HomeResponseModel getData() {
        return data;
    }

    public void setData(HomeResponseModel data) {
        this.data = data;
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
