package org.wikipedia.gather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.wikipedia.server.mwapi.Thumbnail;

public class GatherArticle {
    @SerializedName("pageid") private int pagedId;
    @SerializedName("ns") private int namespace;
    @NonNull private String title;
    @Nullable private Thumbnail thumbnail;

    public int namespace() {
        return namespace;
    }

    @NonNull String title() {
        return title;
    }

    @Nullable public String thumbnail() {
        return thumbnail == null ? null : thumbnail.url();
    }
}
