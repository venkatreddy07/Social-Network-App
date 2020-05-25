package com.task.socialapp.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.task.socialapp.MyApplication;
import com.task.socialapp.R;
import com.task.socialapp.databinding.ActivityRegisterBinding;
import com.task.socialapp.network.response.ApiResponse;
import com.task.socialapp.network.response.user.UserResponse;
import com.task.socialapp.ui.home.HomeActivity;
import com.task.socialapp.util.Constants;
import com.task.socialapp.util.Utils;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    private AuthViewModel authViewModel;

    private String fName, lName, email, gender;

    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (binding == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        }

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        init();

    }

    private void init() {

        binding.radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                radioButton =  findViewById(checkedId);
            }
        });

        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(RegisterActivity.this)) {
                    getUserData();
                    if(validateDetails()){
                        binding.progressBar.setVisibility(View.VISIBLE);
                        signUp();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, R.string.check_network, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getUserData() {
        fName = binding.firstName.getText().toString().trim();
        lName = binding.lastName.getText().toString().trim();
        email = binding.email.getText().toString().trim();
        gender = radioButton.getText().toString();
    }

    private boolean validateDetails() {
        if (!TextUtils.isEmpty(fName)) {
            if (!TextUtils.isEmpty(lName)) {
                if (!TextUtils.isEmpty(email)) {
                    if (!TextUtils.isEmpty(gender)) {
                        return true;
                    } else {
                        Toast.makeText(this, R.string.gender_err, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.email_err, Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, R.string.lname_err, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, R.string.fname_err, Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private void signUp() {
        authViewModel.userSignUp(fName,lName,email,gender).observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                binding.progressBar.setVisibility(View.GONE);
                if (apiResponse != null) {
                    if (apiResponse.getResponseBody() instanceof UserResponse) {
                        UserResponse userResponse = (UserResponse) apiResponse.getResponseBody();
                        parseUserData(userResponse);
                    }
                }
            }
        });
    }

    //set logged in user details into preference
    private void parseUserData(UserResponse userResponse) {

        if (userResponse.getMeta().getCode() == Constants.SUCCESS_CODE) {

            Constants.setUserDetails(userResponse);

            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();

        } else {
            MyApplication.sharedPreferences.setLoginDetails(Constants.Pref.LOGGED_IN, false);
            Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();
        }
    }
}
