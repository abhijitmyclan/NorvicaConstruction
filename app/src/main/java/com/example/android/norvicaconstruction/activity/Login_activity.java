package com.example.android.norvicaconstruction.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.norvicaconstruction.R;
import com.example.android.norvicaconstruction.models.Logindata;
import com.example.android.norvicaconstruction.network.ApiService;
import com.example.android.norvicaconstruction.network.RetroClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_activity extends AppCompatActivity {
    private ApiService mAPIService;
    View focusView = null;
    private String usernm;
    private String password;
    EditText edtusernm;
    EditText edpassword;
    private TextView failedLoginMessage;
    Button sign_in_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        ActionBar actionBar=getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Login");
        }

        mAPIService = RetroClient.getApiService();
        edpassword = (EditText) findViewById(R.id.edtpassword);
        edtusernm = (EditText) findViewById(R.id.edtemail);
        failedLoginMessage = (TextView)findViewById(R.id.failed_login);
        sign_in_button=(Button)findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
//    public static boolean isValidPhoneNumber(String phone)
//    {
//        if (phone.length() == 0)
//        {
//            return Patterns.PHONE.matcher(phone).matches();
//        }
//        else if (phone==null)
//        {
//            return false;
//        }
//
//        return false;
//    }
    private boolean loginValidation() {
        // Reset errors.

        // Store values at the time of the login attempt.
        usernm = edtusernm.getText().toString();
        password = edpassword.getText().toString();
        boolean cancel = false;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            edpassword.setError(getString(R.string.error_invalid_password));
            focusView = edpassword;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(usernm)) {
            edtusernm.setError(getString(R.string.error_field_required));
            focusView = edtusernm;
            cancel = true;
        } else if (usernm.equals("")) {
            edtusernm.setError(getString(R.string.error_invalid_email));
            focusView = edtusernm;
            cancel = true;
        }
        return cancel;
    }
    private void attemptLogin(){
        boolean mCancel = this.loginValidation();
        if (mCancel) {
            focusView.requestFocus();
        } else {
            loginProcessWithRetrofit(usernm, password);
        }
    }
    private void loginProcessWithRetrofit(final String mobile, String password){
        Call<Logindata> mService = mAPIService.getLogin(mobile, password);
        mService.enqueue(new Callback<Logindata>() {
            @Override
            public void onResponse(Call<Logindata> call, Response<Logindata> response) {
                Logindata mLoginObject = response.body();
                String returnedResponse = mLoginObject.getSuccess();
//                Toast.makeText(Login_activity.this, "Returned " + returnedResponse, Toast.LENGTH_LONG).show();
                //showProgress(false);
                if(returnedResponse.trim().equals("1")){
                    Toast.makeText(Login_activity.this, "You have Successfully Login " + returnedResponse, Toast.LENGTH_SHORT).show();

                    // redirect to Main Activity page
                    Intent loginIntent = new Intent(Login_activity.this, Home_activity.class);
                    loginIntent.putExtra("EMAIL", mobile);
                    startActivity(loginIntent);
                }
                if(returnedResponse.trim().equals("0")){
                    Toast.makeText(Login_activity.this, "Check Login Details " + returnedResponse, Toast.LENGTH_SHORT).show();

                    // use the registration button to register
                    failedLoginMessage.setText(getResources().getString(R.string.registration_message));
                    edtusernm.requestFocus();
                }
            }
            @Override
            public void onFailure(Call<Logindata> call, Throwable t) {
                call.cancel();
                Toast.makeText(Login_activity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

}
