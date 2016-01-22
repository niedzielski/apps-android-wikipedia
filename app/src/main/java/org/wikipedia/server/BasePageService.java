package org.wikipedia.server;

import android.support.annotation.NonNull;

import org.wikipedia.Site;

public abstract class BasePageService implements PageService {
    @NonNull private final Site site;

    public BasePageService(@NonNull Site site) {
        this.site = site;
    }

    @NonNull
    @Override
    public Site getSite() {
        return site;
    }
}