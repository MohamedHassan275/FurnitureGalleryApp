package com.example.furniture_gallery.Model.UserResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaginatorResponseModel {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("lastPage")
    @Expose
    private Integer lastPage;
    @SerializedName("hasMorePages")
    @Expose
    private Boolean hasMorePages;
    @SerializedName("nextPageUrl")
    @Expose
    private Object nextPageUrl;
    @SerializedName("previousPageUrl")
    @Expose
    private Object previousPageUrl;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Boolean getHasMorePages() {
        return hasMorePages;
    }

    public void setHasMorePages(Boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }

    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public Object getPreviousPageUrl() {
        return previousPageUrl;
    }

    public void setPreviousPageUrl(Object previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

}
