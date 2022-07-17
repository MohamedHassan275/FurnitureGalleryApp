package com.example.furniture_gallery.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelper;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.Model.UserModel.LoginModel;
import com.example.furniture_gallery.Model.UserResponseModel.LoginResponseModel;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.ViewModel.LoginViewModel;
import com.example.furniture_gallery.databinding.ActivityLoginBinding;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    private static final String KEY_EMPTY = "";
    String email, password;
    LoginViewModel loginViewModel;
    GoogleSignInClient mGoogleSignInClient;
    private static final int google_SIGN_IN = 9001;
    SignInButton Googlelogin_button;
    CallbackManager callbackManager;
    private LoginButton facbooklogin_button;
    PreferenceHelper preferenceHelper;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelper = PreferenceHelper.getInstans(this);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this, preferenceHelperChoseLanguage.getLang());
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(loginBinding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        if (!preferenceHelper.getAccessToken().equals("")) {
            startActivity(new Intent(LoginActivity.this, SelectMyLocationActivity.class));
            finish();
        } else {

        }

    //    getLocation(LoginActivity.this);

        callbackManager = CallbackManager.Factory.create();

        loginBinding.tvSingInFacebook.setOnClickListener(this);
        loginBinding.tvSingInGoogle.setOnClickListener(this);
        loginBinding.tvRegisterByLogin.setOnClickListener(this);
        loginBinding.tvContinueSingIn.setOnClickListener(this);
        loginBinding.uiImageViewPasswordLogin.setOnClickListener(this);


    }

    private void LoginWithFaceBook() {

        facbooklogin_button.performClick();
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // show_toast(loginResult.getAccessToken().getToken() );
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {

                                try {


                                    String social_id = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String SocialType = "facebook";
                                    String image_url = "https://graph.facebook.com/" + social_id + "/picture?type=normal";

                                    Toast.makeText(LoginActivity.this, social_id, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(LoginActivity.this, name, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();

                                    SetLoginByFaceBook( "facebook ", social_id, name,email);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

                Toast.makeText(LoginActivity.this, "الغاء تسجيل الدخول", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void SetLoginUser(String email, String password) {

        loginBinding.progressBarCyclicLoginUser.setVisibility(View.VISIBLE);
        loginViewModel.LoginUser(email, password);
        loginViewModel.loginModelMutableLiveData.observe(LoginActivity.this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {

                if (loginModel.getStatus()) {
                    loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                    LoginResponseModel loginResponseModel = loginModel.getLoginResponseModel();
                    preferenceHelper.putAccessToken(loginResponseModel.getToken());
                    Intent intent = new Intent(LoginActivity.this, HomeMainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void SetLoginByFaceBook(String providerType, String providerId, String name, String email) {

        loginBinding.progressBarCyclicLoginUser.setVisibility(View.VISIBLE);
        loginViewModel.LoginUserBySocial(providerType,providerId,name,email);
        loginViewModel.loginModelMutableLiveData.observe(LoginActivity.this, new Observer<LoginModel>() {
           @Override
           public void onChanged(LoginModel loginModel) {

               if (loginModel.getStatus()){
                   loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                   CheckLoginBySocial(providerType,providerId);

               }else {
                   loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                   Toast.makeText(LoginActivity.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    public void SetLoginByGoogle(String providerType, String providerId, String name, String email) {

        loginBinding.progressBarCyclicLoginUser.setVisibility(View.VISIBLE);
        loginViewModel.LoginUserBySocial(providerType,providerId,name,email);
        loginViewModel.loginModelMutableLiveData.observe(LoginActivity.this, new Observer<LoginModel>() {
           @Override
           public void onChanged(LoginModel loginModel) {

               if (loginModel.getStatus()){
                   loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                   CheckLoginBySocial(providerType,providerId);

               }else {
                   loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                   Toast.makeText(LoginActivity.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    public void CheckLoginBySocial(String providerType, String providerId) {

        loginBinding.progressBarCyclicLoginUser.setVisibility(View.VISIBLE);
        loginViewModel.CheckLoginBySocial(providerType,providerId);
        loginViewModel.loginModelMutableLiveData.observe(LoginActivity.this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {

                if (loginModel.getStatus()){
                    LoginResponseModel loginResponseModel = loginModel.getLoginResponseModel();
                    preferenceHelper.putAccessToken(loginResponseModel.getToken());
                    Intent intent = new Intent(LoginActivity.this, SelectMyLocationActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    loginBinding.progressBarCyclicLoginUser.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void LoginWithGoogle() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //     Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, google_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == google_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);

        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            //  String social_type = "google";
            String social_id = account.getId();
            String name = account.getDisplayName();
            String email = account.getEmail();
            Uri imageProfile = account.getPhotoUrl();
            String SocialType = "google";
//            token = FirebaseInstanceId.getInstance().getToken();
//            Log.d("MYTAG", "" + token);
            Toast.makeText(LoginActivity.this, name, Toast.LENGTH_SHORT).show();
            Toast.makeText(LoginActivity.this, social_id, Toast.LENGTH_SHORT).show();
            Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();


            SetLoginByGoogle("google", social_id , name,email);


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("GoogleSignIn", "signInResult:failed code=" + e.getStatusCode());
            //   Toast.makeText(this, "حاول مرة اخري ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Continue_SingIn:
                email = loginBinding.etEmailLogin.getText().toString().trim();
                password = loginBinding.etPasswordLogin.getText().toString().trim();
                if (validateInputs()) {
                    SetLoginUser(email,password);
                }
                break;
            case R.id.tv_register_byLogin:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.ui_ImageViewPasswordLogin:

                break;
            case R.id.tv_SingIn_facebook:
                if (view == loginBinding.tvSingInFacebook) {
                    // facbooklogin_button.setReadPermissions(Arrays.asList("email"));
                    //  LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));

                    LoginWithFaceBook();
                }
                break;
            case R.id.tv_SingIn_google:
                LoginWithGoogle();
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