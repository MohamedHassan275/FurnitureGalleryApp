package com.example.furniture_gallery.Model.UserResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FurnitureNearByResponseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("furniture_id")
    @Expose
    private Integer furnitureId;
    @SerializedName("branch_type_id")
    @Expose
    private Integer branchTypeId;
    @SerializedName("branch_type_name")
    @Expose
    private String branchTypeName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("country")
    @Expose
    private CountryResponseModel country;
    @SerializedName("governorate")
    @Expose
    private GovernmentResponseModel governorate;
    @SerializedName("region")
    @Expose
    private RegionResponseModel region;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("is_fav")
    @Expose
    private Boolean isFav;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("rate_count")
    @Expose
    private Object rateCount;
    @SerializedName("open")
    @Expose
    private Boolean open;
    @SerializedName("link_google_play")
    @Expose
    private String linkGooglePlay;
    @SerializedName("link_apple_store")
    @Expose
    private String linkAppleStore;
    @SerializedName("qr_image")
    @Expose
    private String qrImage;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("distance")
    @Expose
    private Integer distance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Integer furnitureId) {
        this.furnitureId = furnitureId;
    }

    public Integer getBranchTypeId() {
        return branchTypeId;
    }

    public void setBranchTypeId(Integer branchTypeId) {
        this.branchTypeId = branchTypeId;
    }

    public String getBranchTypeName() {
        return branchTypeName;
    }

    public void setBranchTypeName(String branchTypeName) {
        this.branchTypeName = branchTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public CountryResponseModel getCountry() {
        return country;
    }

    public void setCountry(CountryResponseModel country) {
        this.country = country;
    }

    public GovernmentResponseModel getGovernorate() {
        return governorate;
    }

    public void setGovernorate(GovernmentResponseModel governorate) {
        this.governorate = governorate;
    }

    public RegionResponseModel getRegion() {
        return region;
    }

    public void setRegion(RegionResponseModel region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
        this.isFav = isFav;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Object getRateCount() {
        return rateCount;
    }

    public void setRateCount(Object rateCount) {
        this.rateCount = rateCount;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getLinkGooglePlay() {
        return linkGooglePlay;
    }

    public void setLinkGooglePlay(String linkGooglePlay) {
        this.linkGooglePlay = linkGooglePlay;
    }

    public String getLinkAppleStore() {
        return linkAppleStore;
    }

    public void setLinkAppleStore(String linkAppleStore) {
        this.linkAppleStore = linkAppleStore;
    }

    public String getQrImage() {
        return qrImage;
    }

    public void setQrImage(String qrImage) {
        this.qrImage = qrImage;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }


}
