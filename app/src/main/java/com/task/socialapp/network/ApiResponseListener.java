package com.task.socialapp.network;


import com.task.socialapp.network.response.ApiResponse;

public interface ApiResponseListener {
    void onSuccess(ApiResponse apiResponse);
    void onFailure(ApiResponse apiResponseFail);}
