package com.buddy.tiki.model.msg;

import android.support.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class RoomMessage {
    private String content;
    private String type;

    public RoomMessage(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", this.type);
            jsonObject.put("content", this.content);
        } catch (JSONException e) {
        }
        return jsonObject.toString();
    }
}
