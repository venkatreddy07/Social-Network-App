package com.task.socialapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.task.socialapp.network.response.post.PostResult;

import java.util.List;

@Dao
public interface PostDto {

    @Query("SELECT id FROM posts_table WHERE id = :id LIMIT 1")
    String checkForData(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostResult postResult);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(PostResult postResult);

    @Query("SELECT * FROM posts_table")
    LiveData<List<PostResult>> getAllPosts();
}
