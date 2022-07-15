package com.example.furniture_gallery.Model.UserModel;

import com.example.furniture_gallery.Model.UserResponseModel.LoginResponseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("data")
    @Expose
    private LoginResponseModel loginResponseModel;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public LoginResponseModel getLoginResponseModel() {
        return loginResponseModel;
    }

    public void setLoginResponseModel(LoginResponseModel loginResponseModel) {
        this.loginResponseModel = loginResponseModel;
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
