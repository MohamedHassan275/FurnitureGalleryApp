package com.example.furniture_gallery.Model.UserResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferHomeResponseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("offer_id")
    @Expose
    private Integer offerId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("images")
    @Expose
    private List<ImageSliderHomeResponeModel> images = null;
    @SerializedName("furniture_id")
    @Expose
    private Integer furnitureId;
    @SerializedName("furniture_name")
    @Expose
    private String furnitureName;
    @SerializedName("furniture_logo")
    @Expose
    private String furnitureLogo;
    @SerializedName("products")
    @Expose
    private List<ProductOfferHomeResponesModel> products = null;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("rate_count")
    @Expose
    private Integer rateCount;
    @SerializedName("model_type")
    @Expose
    private String modelType;
    @SerializedName("qty_cart")
    @Expose
    private String qtyCart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<ImageSliderHomeResponeModel> getImages() {
        return images;
    }

    public void setImages(List<ImageSliderHomeResponeModel> images) {
        this.images = images;
    }

    public Integer getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Integer furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getFurnitureLogo() {
        return furnitureLogo;
    }

    public void setFurnitureLogo(String furnitureLogo) {
        this.furnitureLogo = furnitureLogo;
    }

    public List<ProductOfferHomeResponesModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOfferHomeResponesModel> products) {
        this.products = products;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getQtyCart() {
        return qtyCart;
    }

    public void setQtyCart(String qtyCart) {
        this.qtyCart = qtyCart;
    }

}
