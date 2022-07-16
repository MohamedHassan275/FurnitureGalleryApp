package com.example.furniture_gallery.Model.UserModel;

import com.example.furniture_gallery.Model.UserResponseModel.HomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.OfferHomeResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.SavesDiscountHomeResponseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveOfferModel {

    @SerializedName("data")
    @Expose
    private List<SavesDiscountHomeResponseModel> saves = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<SavesDiscountHomeResponseModel> getSaves() {
        return saves;
    }

    public void setSaves(List<SavesDiscountHomeResponseModel> saves) {
        this.saves = saves;
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
