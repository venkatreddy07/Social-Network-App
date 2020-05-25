package com.task.socialapp.network;


import com.task.socialapp.network.request.SignUpRequest;
import com.task.socialapp.network.response.post.PostResponse;
import com.task.socialapp.network.response.user.UserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RestClientApiService {

    String AUTHORIZATION = "Authorization";
    String USERS = "users";
    String POSTS = "posts";


    @GET(USERS + "/" + "{user_id}")
    Call<UserResponse> getUserDetails(@Header(AUTHORIZATION) String token,
                                      @Path(value = "user_id", encoded = true) String userId);

    @POST(USERS)
    Call<UserResponse> signUpUser(@Header(AUTHORIZATION) String token,
                                  @Body SignUpRequest signUpRequest);

    @GET(POSTS)
    Call<PostResponse> getPosts(@QueryMap Map<String,String> queryMap);
}
