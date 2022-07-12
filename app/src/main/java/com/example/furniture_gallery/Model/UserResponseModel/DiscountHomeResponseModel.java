package com.example.furniture_gallery.Model.UserResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscountHomeResponseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("discount_id")
    @Expose
    private Integer discountId;
    @SerializedName("price_before")
    @Expose
    private Integer priceBefore;
    @SerializedName("price_after")
    @Expose
    private Integer priceAfter;
    @SerializedName("percent")
    @Expose
    private Integer percent;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
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

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Integer getPriceBefore() {
        return priceBefore;
    }

    public void setPriceBefore(Integer priceBefore) {
        this.priceBefore = priceBefore;
    }

    public Integer getPriceAfter() {
        return priceAfter;
    }

    public void setPriceAfter(Integer priceAfter) {
        this.priceAfter = priceAfter;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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
