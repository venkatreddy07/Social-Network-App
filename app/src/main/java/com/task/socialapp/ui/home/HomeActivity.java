package com.task.socialapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;
import com.task.socialapp.MyApplication;
import com.task.socialapp.R;
import com.task.socialapp.databinding.ActivityHomeBinding;
import com.task.socialapp.network.response.ApiResponse;
import com.task.socialapp.network.response.post.PostResponse;
import com.task.socialapp.network.response.post.PostResult;
import com.task.socialapp.network.response.user.UserResponse;
import com.task.socialapp.ui.auth.LoginActivity;
import com.task.socialapp.util.Utils;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    private HomeAdapter homeAdapter;

    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (binding == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        }

        Stetho.initializeWithDefaults(this);

        HomeViewModelFactory factory = new HomeViewModelFactory(this);
        homeViewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);

        init();

        if (Utils.isNetworkAvailable(this)) {
            getPosts();
        } else {
            getOfflinePosts();
        }
    }


    private void init() {
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flushUser();
            }
        });
    }

    //online posts
    private void getPosts() {
        binding.progressBar.setVisibility(View.VISIBLE);
        homeViewModel.getPostsDetails().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                binding.progressBar.setVisibility(View.GONE);

                if (apiResponse != null) {
                    if (apiResponse.getResponseBody() instanceof PostResponse) {
                        PostResponse postResponse = (PostResponse) apiResponse.getResponseBody();
                        setAdapter(postResponse.getPostResults());

                        insertPostData(postResponse.getPostResults());
                    }
                }
            }
        });
    }

    //insert posts data into room db
    private void insertPostData(List<PostResult> postResults) {
        for (PostResult postResult : postResults) {
            homeViewModel.insert(postResult);
        }
    }

    //offline posts
    private void getOfflinePosts() {
        binding.progressBar.setVisibility(View.VISIBLE);
        homeViewModel.getOfflinePosts().observe(this, new Observer<List<PostResult>>() {
            @Override
            public void onChanged(List<PostResult> postResults) {
                binding.progressBar.setVisibility(View.GONE);
                if (postResults.size() > 0) {
                    setAdapter(postResults);
                }
            }
        });
    }

    private void setAdapter(List<PostResult> postResponse) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);

        binding.postsRsv.setLayoutManager(mLayoutManager);

        HomeAdapter homeAdapter = new HomeAdapter(this, postResponse);
        binding.postsRsv.setAdapter(homeAdapter);
    }

    //user logout
    private void flushUser() {
        MyApplication.sharedPreferences.flushUser();

        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }
}
