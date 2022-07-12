package com.example.furniture_gallery.Model.UserResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponseModel {

    @SerializedName("categories")
    @Expose
    private List<CategoryHomeResponseModel> categories = null;
    @SerializedName("offers")
    @Expose
    private List<OfferHomeResponseModel> offers = null;
    @SerializedName("saves")
    @Expose
    private List<SavesDiscountHomeResponseModel> saves = null;
    @SerializedName("discounts")
    @Expose
    private List<DiscountHomeResponseModel> discounts = null;
    @SerializedName("branch_type")
    @Expose
    private List<BranchTypeHomeResponseModel> branchType = null;

    public List<CategoryHomeResponseModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryHomeResponseModel> categories) {
        this.categories = categories;
    }

    public List<OfferHomeResponseModel> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferHomeResponseModel> offers) {
        this.offers = offers;
    }

    public List<SavesDiscountHomeResponseModel> getSaves() {
        return saves;
    }

    public void setSaves(List<SavesDiscountHomeResponseModel> saves) {
        this.saves = saves;
    }

    public List<DiscountHomeResponseModel> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountHomeResponseModel> discounts) {
        this.discounts = discounts;
    }

    public List<BranchTypeHomeResponseModel> getBranchType() {
        return branchType;
    }

    public void setBranchType(List<BranchTypeHomeResponseModel> branchType) {
        this.branchType = branchType;
    }

}
