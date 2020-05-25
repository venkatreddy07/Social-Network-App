package com.task.socialapp.network.request;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {

    @SerializedName("first_name")
    private String fName;
    @SerializedName("last_name")
    private String lName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("email")
    private String email;
    @SerializedName("status")
    private String status;

    public SignUpRequest(String fName, String lName, String email, String gender, String status) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }
}
