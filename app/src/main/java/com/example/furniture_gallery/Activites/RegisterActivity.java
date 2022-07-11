package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.furniture_gallery.R;
import com.example.furniture_gallery.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityRegisterBinding registerBinding;
    private static final String KEY_EMPTY = "";
    String name,email,mobilePhone,address,password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());

        registerBinding.tvContinueSingUp.setOnClickListener(this);
        registerBinding.tvLoginByRegister.setOnClickListener(this);

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
                       startActivity(new Intent(this,HomeMainActivity.class));
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
            registerBinding.etAddressRegister.setError("الايميل غير صالح. حاول مرة اخري");
            registerBinding.etAddressRegister.requestFocus();
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