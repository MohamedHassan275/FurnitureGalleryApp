package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelper;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.LoginModel;
import com.example.furniture_gallery.Model.UserResponseModel.LoginResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.Retrofit_Api.Retrofit_Api;
import com.example.furniture_gallery.ViewModel.LoginViewModel;
import com.example.furniture_gallery.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    private static final String KEY_EMPTY = "";
    String email, password;
    LoginViewModel loginViewModel;
    PreferenceHelper preferenceHelper;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelper = PreferenceHelper.getInstans(this);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this, preferenceHelperChoseLanguage.getLang());
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        if (preferenceHelper.getAccessToken() != null){
            startActivity(new Intent(LoginActivity.this,HomeMainActivity.class));
            finish();
        }else {

        }
        loginBinding.tvRegisterByLogin.setOnClickListener(this);
        loginBinding.tvContinueSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = loginBinding.etEmailLogin.getText().toString().trim();
                password = loginBinding.etPasswordLogin.getText().toString().trim();

                if (validateInputs()) {
                    LoginUser(email,password);

//                    loginBinding.progressBarCyclicLoginUser.setVisibility(View.VISIBLE);
//                    loginViewModel.LoginUser(email, password);
//                    loginViewModel.loginModelMutableLiveData.observe(LoginActivity.this, new Observer<LoginModel>() {
//                        @Override
//                        public void onChanged(LoginModel loginModel) {
//
//                            if (loginModel.getStatus()) {
//                                loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
//                                LoginResponseModel loginResponseModel = loginModel.getLoginResponseModel();
//                                preferenceHelper.putAccessToken(loginResponseModel.getToken());
//                                startActivity(new Intent(LoginActivity.this, HomeMainActivity.class));
//                                finish();
//
//                            } else {
//                                loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
//                                Toast.makeText(LoginActivity.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });

                }
            }
        });



        loginBinding.uiImageViewPasswordLogin.setOnClickListener(this);


    }

    public void LoginUser(String userName, String password){
        loginBinding.progressBarCyclicLoginUser.setVisibility(View.VISIBLE);
        Call<LoginModel> call = Retrofit_Api.RETROFIT_API_INSTANCE().LoginUser(userName,password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.code() == 200) {
                    if (response.body().getStatus()) {
                        loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                        LoginResponseModel loginResponseModel = null;
                        if (response.body() != null) {
                            loginResponseModel = response.body().getLoginResponseModel();
                        }
                        preferenceHelper.putAccessToken(loginResponseModel.getToken());
                        startActivity(new Intent(LoginActivity.this, HomeMainActivity.class));
                        finish();
                    }else {

                    }
                } else if (response.code() == 422) {
                    loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Continue_SingIn:

                break;
            case R.id.tv_register_byLogin:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.ui_ImageViewPasswordLogin:

                break;
        }
    }

    private boolean validateInputs() {
        if (KEY_EMPTY.equals(email)) {
            loginBinding.etEmailLogin.setError("لا يمكن أن يكون  فارغا");
            loginBinding.etEmailLogin.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            loginBinding.etPasswordLogin.setError("لا يمكن أن يكون  فارغا");
            loginBinding.etPasswordLogin.requestFocus();
            return false;
        }
        if (!isVaildEmail(email)) {
            loginBinding.etEmailLogin.setError("الايميل غير صالح. حاول مرة اخري");
            loginBinding.etEmailLogin.requestFocus();
            return false;
        }
        if (!(password.length() > 5)) {
            loginBinding.etPasswordLogin.setError("كلمة السر غير صالح. حاول مرة اخري");
            loginBinding.etPasswordLogin.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isVaildEmail(String emailHolder) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(emailHolder);
        return m.matches();
    }

}