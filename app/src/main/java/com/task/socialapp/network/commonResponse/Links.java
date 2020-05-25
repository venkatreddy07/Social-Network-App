package com.task.socialapp.network.commonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.task.socialapp.network.response.user.Avatar;
import com.task.socialapp.network.response.user.Edit;
import com.task.socialapp.network.response.user.Self;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("edit")
    @Expose
    private Edit edit;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Edit getEdit() {
        return edit;
    }

    public void setEdit(Edit edit) {
        this.edit = edit;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
