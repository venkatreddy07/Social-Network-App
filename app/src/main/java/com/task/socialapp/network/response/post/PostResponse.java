package com.task.socialapp.network.response.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostResponse {
    @SerializedName("_meta")
    @Expose
    private PostMeta postMeta;
    @SerializedName("result")
    @Expose
    private List<PostResult> postResults = null;

    public PostMeta getPostMeta() {
        return postMeta;
    }

    public void setPostMeta(PostMeta postMeta) {
        this.postMeta = postMeta;
    }

    public List<PostResult> getPostResults() {
        return postResults;
    }

    public void setPostResults(List<PostResult> postResults) {
        this.postResults = postResults;
    }
}
