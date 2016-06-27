package com.example.samsung.gp.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Hussein on 5/31/2016.
 */
public class CommentItem implements Serializable {


    private String CommentId;
    private String CommentTxt;
    private String UserId;
    private String ItemId;
    private String Name;
    private String UserImage;
    private String rate;

    public CommentItem(JSONObject jItem) {
        try {
            this.CommentId = jItem.getString("CommentId");
            this.CommentTxt = jItem.getString("CommentTxt");
            this.UserId = jItem.getString("UserId");
            this.ItemId =jItem.getString("ItemId");
            this.Name = jItem.getString("Name");
            this.UserImage = jItem.getString("UserImage");
            this.rate = jItem.getString("rate");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public CommentItem(String CommentId, String CommentTxt, String UserId, String ItemId, String Name, String UserImage, String rate) {
        this.CommentId = CommentId;
        this.CommentTxt = CommentTxt;
        this.UserId = UserId;
        this.ItemId =ItemId;
        this.Name = Name;
        this.UserImage = UserImage;
        this.rate = rate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCommentId() {
        return CommentId;
    }

    public void setCommentId(String commentId) {
        CommentId = commentId;
    }

    public String getTxtComment() {
        return CommentTxt;
    }

    public void setTxtComment(String txtComment) {
        CommentTxt = txtComment;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getrate() {
        return rate;
    }

    public void setrate(String Rate) {
        rate = Rate;
    }



}
