package com.example.furniture_gallery.Model.UserResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionResponseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return regionId;
    }

    public void setCountryId(Integer countryId) {
        this.regionId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
