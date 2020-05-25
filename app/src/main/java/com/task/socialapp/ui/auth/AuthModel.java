package com.task.socialapp.ui.auth;

import com.task.socialapp.network.APIConstants;
import com.task.socialapp.network.ApiResponseListener;
import com.task.socialapp.network.CommonNetworkCallback;
import com.task.socialapp.network.RestClient;
import com.task.socialapp.network.request.SignUpRequest;
import com.task.socialapp.network.response.ApiResponse;
import com.task.socialapp.network.response.user.UserResponse;

import java.util.HashMap;

public class AuthModel {

    void getUserCall(final ApiResponseListener apiResponseListener, String userId) {
        RestClient.getApiService(APIConstants.BASE_URL)
                .getUserDetails(APIConstants.bearer + " " + APIConstants.TOKEN, userId)
                .enqueue(new CommonNetworkCallback<UserResponse>() {
                    @Override
                    public void onApiResponseSuccess(ApiResponse apiResponse) {
                        apiResponseListener.onSuccess(apiResponse);
                    }

                    @Override
                    public void onApiResponseFailure(ApiResponse apiFailure) {
                        apiResponseListener.onFailure(apiFailure);
                    }
                });
    }

    void signUpUserCall(ApiResponseListener apiResponseListener, SignUpRequest signUpRequest) {
        RestClient.getApiService(APIConstants.BASE_URL)
                .signUpUser(APIConstants.bearer + " " + APIConstants.TOKEN, signUpRequest)
                .enqueue(new CommonNetworkCallback<UserResponse>() {
                    @Override
                    public void onApiResponseSuccess(ApiResponse apiResponse) {
                        apiResponseListener.onSuccess(apiResponse);
                    }

                    @Override
                    public void onApiResponseFailure(ApiResponse apiFailure) {
                        apiResponseListener.onFailure(apiFailure);
                    }
                });
    }
}
