package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Switch;

import com.example.furniture_gallery.R;
import com.example.furniture_gallery.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    private static final String KEY_EMPTY = "";
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        loginBinding.tvRegisterByLogin.setOnClickListener(this);
        loginBinding.tvContinueSingIn.setOnClickListener(this);
        loginBinding.uiImageViewPasswordLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_Continue_SingIn:
                if(validateInputs()){
                    startActivity(new Intent(this,HomeMainActivity.class));
                }
                break;
            case R.id.tv_register_byLogin:
                startActivity(new Intent(this,RegisterActivity.class));
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
        if (!(password.length() > 6)) {
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