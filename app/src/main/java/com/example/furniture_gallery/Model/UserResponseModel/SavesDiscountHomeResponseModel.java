package com.example.furniture_gallery.Model.UserResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SavesDiscountHomeResponseModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("save_id")
    @Expose
    private Integer saveId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("diff_day")
    @Expose
    private Integer diffDay;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("hours")
    @Expose
    private Integer hours;
    @SerializedName("minutes")
    @Expose
    private Integer minutes;
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

    public Integer getSaveId() {
        return saveId;
    }

    public void setSaveId(Integer saveId) {
        this.saveId = saveId;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getDiffDay() {
        return diffDay;
    }

    public void setDiffDay(Integer diffDay) {
        this.diffDay = diffDay;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
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
