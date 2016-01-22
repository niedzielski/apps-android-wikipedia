package org.wikipedia.server.mwapi;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Thumbnail {
    @NonNull @SerializedName("source") private String url;
    private int width;
    private int height;

    public String url() {
        return url;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}