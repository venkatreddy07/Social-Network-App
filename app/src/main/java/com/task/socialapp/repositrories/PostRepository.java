package com.task.socialapp.repositrories;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;

import com.task.socialapp.db.PostDataBase;
import com.task.socialapp.db.PostDto;
import com.task.socialapp.network.response.post.PostResult;

import java.util.List;

public class PostRepository {

    private PostDto postDto;

    public PostRepository(Context context) {
        PostDataBase postDataBase = PostDataBase.getInstance(context);

        postDto = postDataBase.postDto();
    }

    //get posts to support offline mode
    public LiveData<List<PostResult>> getAllPosts() {
        return postDto.getAllPosts();
    }

    //insert post data
    public void insert(PostResult postResult) {
        new InsertPostAsyncTask(postDto).execute(postResult);
    }

    private static class InsertPostAsyncTask extends AsyncTask<PostResult, Void, Void> {

        private PostDto postDto;

        InsertPostAsyncTask(PostDto postDto) {
            this.postDto = postDto;
        }

        @Override
        protected Void doInBackground(PostResult... postResults) {
            String id = postDto.checkForData(postResults[0].getId());
            if(!TextUtils.isEmpty(id)){
                postDto.update(postResults[0]);
            }else {
                postDto.insert(postResults[0]);
            }
            return null;
        }
    }
}
