package com.task.socialapp.ui.auth;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.task.socialapp.network.ApiResponseListener;
import com.task.socialapp.network.request.SignUpRequest;
import com.task.socialapp.network.response.ApiResponse;

public class AuthViewModel extends ViewModel {

    private MutableLiveData<ApiResponse> userLiveData;
    private MutableLiveData<ApiResponse> userSignUpLiveData;

    private AuthModel authModel;

    public AuthViewModel() {
        authModel = new AuthModel();
    }

    MutableLiveData<ApiResponse> getUser(String userId) {
        userLiveData = new MutableLiveData<>();

        authModel.getUserCall(new ApiResponseListener() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                userLiveData.postValue(apiResponse);
            }

            @Override
            public void onFailure(ApiResponse apiResponseFail) {
                userLiveData.postValue(apiResponseFail);
            }
        }, userId);

        return userLiveData;
    }

    MutableLiveData<ApiResponse> userSignUp(String fName, String lName, String email, String gender) {
        userSignUpLiveData = new MutableLiveData<>();

        SignUpRequest signUpRequest = new SignUpRequest(fName,lName,email,gender,"active");

        authModel.signUpUserCall(new ApiResponseListener() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                userSignUpLiveData.postValue(apiResponse);
            }

            @Override
            public void onFailure(ApiResponse apiResponseFail) {
                userSignUpLiveData.postValue(apiResponseFail);
            }
        },signUpRequest);

        return userSignUpLiveData;
    }
}
