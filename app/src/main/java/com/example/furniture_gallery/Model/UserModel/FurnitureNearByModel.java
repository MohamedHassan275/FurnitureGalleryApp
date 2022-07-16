package com.example.furniture_gallery.Model.UserModel;

import com.example.furniture_gallery.Model.UserResponseModel.FurnitureNearByResponseModel;
import com.example.furniture_gallery.Model.UserResponseModel.PaginatorResponseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FurnitureNearByModel {

    @SerializedName("data")
    @Expose
    private List<FurnitureNearByResponseModel> furnitureNearByResponseModels = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("paginator")
    @Expose
    private PaginatorResponseModel paginatorResponseModel;

    public List<FurnitureNearByResponseModel> getFurnitureNearByResponseModels() {
        return furnitureNearByResponseModels;
    }

    public void setFurnitureNearByResponseModels(List<FurnitureNearByResponseModel> furnitureNearByResponseModels) {
        this.furnitureNearByResponseModels = furnitureNearByResponseModels;
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

    public PaginatorResponseModel getPaginatorResponseModel() {
        return paginatorResponseModel;
    }

    public void setPaginatorResponseModel(PaginatorResponseModel paginatorResponseModel) {
        this.paginatorResponseModel = paginatorResponseModel;
    }

}
