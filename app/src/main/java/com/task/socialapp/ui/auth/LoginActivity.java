package com.task.socialapp.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.task.socialapp.MyApplication;
import com.task.socialapp.R;
import com.task.socialapp.databinding.ActivityLoginBinding;
import com.task.socialapp.network.response.ApiResponse;
import com.task.socialapp.network.response.user.UserResponse;
import com.task.socialapp.ui.home.HomeActivity;
import com.task.socialapp.util.Constants;
import com.task.socialapp.util.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (binding == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        }

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        clickListeners();
    }

    private void clickListeners() {
        binding.loginBtn.setOnClickListener(this);
        binding.signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                Utils.hideKeyboard(this);
                if (Utils.isNetworkAvailable(this)) {
                    String userId = binding.userId.getText().toString().trim();
                    if (!TextUtils.isEmpty(userId)) {
                        binding.progressBar.setVisibility(View.VISIBLE);

                        getUserDetails(userId);
                    } else {
                        Toast.makeText(this, R.string.enter_userid, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.check_network, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.sign_up:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

        }
    }

    private void getUserDetails(String userId) {
        authViewModel.getUser(userId).observe(this, new Observer<ApiResponse>() {
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

            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();

        } else {
            MyApplication.sharedPreferences.setLoginDetails(Constants.Pref.LOGGED_IN, false);
            Toast.makeText(this, R.string.user_not_found, Toast.LENGTH_SHORT).show();
        }
    }
}
