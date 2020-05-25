package com.task.socialapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.task.socialapp.network.response.post.PostResult;

@Database(entities = {PostResult.class}, version = 1, exportSchema = false)
public abstract class PostDataBase extends RoomDatabase {

    public abstract PostDto postDto();

    private static PostDataBase instance;

    public static synchronized PostDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PostDataBase.class, "post_database")
                    .build();
        }

        return instance;
    }
}
