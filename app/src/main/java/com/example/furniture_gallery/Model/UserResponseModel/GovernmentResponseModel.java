package com.example.furniture_gallery.Model.UserResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GovernmentResponseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("governorate_id")
    @Expose
    private Integer governorateId;
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
        return governorateId;
    }

    public void setgovernorateId(Integer countryId) {
        this.governorateId = countryId;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
