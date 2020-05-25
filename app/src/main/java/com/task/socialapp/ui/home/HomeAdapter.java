package com.task.socialapp.ui.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.task.socialapp.R;
import com.task.socialapp.network.response.post.PostResult;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<PostResult> postResults;


    public HomeAdapter(Context context, List<PostResult> postResults) {
        this.context = context;
        this.postResults = postResults;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        PostResult postResult = postResults.get(position);
        if (postResult != null) {
            if(!TextUtils.isEmpty(postResult.getTitle())){
                holder.title.setText(postResult.getTitle());
            }

            if(!TextUtils.isEmpty(postResult.getBody())){
                holder.body.setText(postResult.getBody());
            }
        }
    }

    @Override
    public int getItemCount() {
        return postResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
