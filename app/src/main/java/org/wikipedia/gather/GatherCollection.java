package org.wikipedia.gather;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class GatherCollection {
    @NonNull @SerializedName("pages") private List<GatherArticle> items;

    // TODO: implementation, list id, other stuff
    public Bitmap icon() {
        return null;
    }

    @NonNull public List<GatherArticle> items() {
        return Collections.unmodifiableList(items);
    }

    public int size() {
        return items.size();
    }

    @Override
    public String toString() {
        return "size=" + size();
    }
}