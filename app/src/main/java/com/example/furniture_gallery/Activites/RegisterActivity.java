package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelper;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.LoginModel;
import com.example.furniture_gallery.Model.UserResponseModel.LoginResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.RegisterViewModel;
import com.example.furniture_gallery.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityRegisterBinding registerBinding;
    private static final String KEY_EMPTY = "";
    RegisterViewModel registerViewModel;
    String name,email,mobilePhone,address,password,confirmPassword;
    PreferenceHelper preferenceHelper;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelper = PreferenceHelper.getInstans(this);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this,preferenceHelperChoseLanguage.getLang());
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerBinding.tvContinueSingUp.setOnClickListener(this);
        registerBinding.tvLoginByRegister.setOnClickListener(this);

    }

    public void RegisterUser(String name, String email, String phone, String password, String confirmPassword, String lang, String address){

        registerBinding.progressBarCyclicRegisterUser.setVisibility(View.VISIBLE);
        registerViewModel.RegisterUser(name,email,phone,password,confirmPassword,lang,address);
        registerViewModel.registerModelMutableLiveData.observe(RegisterActivity.this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                if (loginModel.getStatus()) {
                    registerBinding.progressBarCyclicRegisterUser.setVisibility(View.GONE);
                    LoginResponseModel loginResponseModel = loginModel.getLoginResponseModel();
                    preferenceHelper.putAccessToken(loginResponseModel.getToken());
                    startActivity(new Intent(RegisterActivity.this,SelectMyLocationActivity.class));
                    finish();

                }else {

                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login_byRegister:
                startActivity(new Intent(this,LoginActivity.class));
                break;
                case R.id.tv_Continue_SingUp:
                    name = registerBinding.etNameRegister.getText().toString().trim();
                    email = registerBinding.etEmailRegister.getText().toString().trim();
                    mobilePhone = registerBinding.etMobilePhoneRegister.getText().toString().trim();
                    address = registerBinding.etAddressRegister.getText().toString().trim();
                    password = registerBinding.etPasswordRegister.getText().toString().trim();
                    confirmPassword = registerBinding.etConfirmPasswordRegister.getText().toString().trim();

                   if(validateInputs()){
                       RegisterUser(name,email,mobilePhone,password,confirmPassword,preferenceHelperChoseLanguage.getLang(),address);
                   }
                    break;
        }
    }

    private boolean validateInputs() {
        if (KEY_EMPTY.equals(name)) {
            registerBinding.etNameRegister.setError("لا يمكن أن يكون  فارغا");
            registerBinding.etNameRegister.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(email)) {
            registerBinding.etEmailRegister.setError("لا يمكن أن يكون  فارغا");
            registerBinding.etEmailRegister.requestFocus();
            return false;
        }
        if (!isVaildEmail(mobilePhone)) {
            registerBinding.etMobilePhoneRegister.setError("الايميل غير صالح. حاول مرة اخري");
            registerBinding.etMobilePhoneRegister.requestFocus();
            return false;
        }
        if (!isVaildEmail(address)) {
            registerBinding.etEmailRegister.setError("الايميل غير صالح. حاول مرة اخري");
            registerBinding.etEmailRegister.requestFocus();
            return false;
        }
        if (!isVaildEmail(password)) {
            registerBinding.etPasswordRegister.setError("الايميل غير صالح. حاول مرة اخري");
            registerBinding.etPasswordRegister.requestFocus();
            return false;
        }
        if (!isVaildEmail(confirmPassword)) {
            registerBinding.etConfirmPasswordRegister.setError("الايميل غير صالح. حاول مرة اخري");
            registerBinding.etConfirmPasswordRegister.requestFocus();
            return false;
        }
        if (!(password.equals(confirmPassword))) {
            registerBinding.etConfirmPasswordRegister.setError("كلمة السر وتأكيد كلمة السر غير متطابقة");
            registerBinding.etConfirmPasswordRegister.requestFocus();
            return false;
        }
        if (!(password.length() > 6)) {
            registerBinding.etPasswordRegister.setError("كلمة السر غير صالح. حاول مرة اخري");
            registerBinding.etPasswordRegister.requestFocus();
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