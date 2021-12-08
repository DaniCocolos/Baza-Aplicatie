package com.example.forfoodiesbyfoodies.Forum;

import android.renderscript.Sampler;
import android.util.Log;

import com.google.firebase.database.ServerValue;
import com.google.firebase.database.core.ServerValues;

import java.util.Date;

public class ForumObject {

    Date date;

    String topic_description;
    String topic_id;
    String topic_title;
    String username;

    //Test
    String answerText;
    String url;



    /*public ForumObject(String answerText, String username, String url )
    {
        this.answerText = answerText;
        this.username = username;
        this.url = url;
    }*/

    public ForumObject( String topic_description, String topic_id, String topic_title, String username) {


        this.topic_description = topic_description;
        this.topic_id = topic_id;
        this.topic_title = topic_title;
        this.username = username;
    }
    public ForumObject(){}



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic_description() {
        return topic_description;
    }

    public void setTopic_description(String topic_description) {
        this.topic_description = topic_description;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
