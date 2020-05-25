package com.task.socialapp.ui.home;

import com.task.socialapp.network.APIConstants;
import com.task.socialapp.network.ApiResponseListener;
import com.task.socialapp.network.CommonNetworkCallback;
import com.task.socialapp.network.RestClient;
import com.task.socialapp.network.response.ApiResponse;
import com.task.socialapp.network.response.post.PostResponse;

import java.util.HashMap;

public class HomeModel {

    void getPostsCall(ApiResponseListener apiResponseListener, HashMap<String, String> queryMap) {
        RestClient.getApiService(APIConstants.BASE_URL)
                .getPosts(queryMap)
                .enqueue(new CommonNetworkCallback<PostResponse>() {
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
