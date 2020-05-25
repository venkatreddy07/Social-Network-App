package com.task.socialapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.task.socialapp.network.APIConstants;
import com.task.socialapp.network.ApiResponseListener;
import com.task.socialapp.network.response.ApiResponse;
import com.task.socialapp.network.response.post.PostResult;
import com.task.socialapp.repositrories.PostRepository;

import java.util.HashMap;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private HomeModel homeModel;

    private MutableLiveData<ApiResponse> postLiveData;

    private PostRepository postRepository;

    public HomeViewModel(HomeActivity activity) {
        postRepository = new PostRepository(activity);

        homeModel = new HomeModel();
    }

    public MutableLiveData<ApiResponse> getPostsDetails() {
        postLiveData = new MutableLiveData<>();

        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("_format", "json");
        queryMap.put("access-token", APIConstants.TOKEN);

        homeModel.getPostsCall(new ApiResponseListener() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                postLiveData.postValue(apiResponse);
            }

            @Override
            public void onFailure(ApiResponse apiResponseFail) {
                postLiveData.postValue(apiResponseFail);
            }
        }, queryMap);

        return postLiveData;
    }

    //db operations
    void insert(PostResult postResult) {
        postRepository.insert(postResult);
    }

    LiveData<List<PostResult>> getOfflinePosts() {
        return postRepository.getAllPosts();
    }
}
