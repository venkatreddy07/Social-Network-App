package com.task.socialapp.util;

import com.google.gson.Gson;
import com.task.socialapp.MyApplication;
import com.task.socialapp.network.response.user.UserResponse;

public class Constants {

    public static int SUCCESS_CODE = 200;

    public static class Pref {
        public static String LOGGED_IN = "loggedIn";

        static String USER_DETAILS = "loggedIn";
    }


    public static void setUserDetails(UserResponse userResponse) {

        Gson gson = new Gson();
        String userJson = gson.toJson(userResponse.getResult());
        MyApplication.sharedPreferences.setStringPreference(Constants.Pref.USER_DETAILS, userJson);

        MyApplication.sharedPreferences.setLoginDetails(Constants.Pref.LOGGED_IN, true);
    }
}
