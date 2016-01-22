package org.wikipedia.gather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.wikipedia.R;
import org.wikipedia.WikipediaApp;
import org.wikipedia.activity.CallbackFragment;
import org.wikipedia.activity.FragmentCallback;
import org.wikipedia.server.PageService;
import org.wikipedia.util.FeedbackUtil;
import org.wikipedia.util.log.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GatherFragment extends CallbackFragment<FragmentCallback> {
    // TODO: feels like this should be some place more centralized.
    private static final int RETRY_LIMIT = 10;

    // TODO: seems like we mant want a little helper API for this.
    private static final int COLLECTIONS_WATCHLIST_ID = 0;

    public static GatherFragment newInstance() {
        return new GatherFragment();
    }

    @Bind(R.id.fragment_gather_collections) GatherCollectionView collectionsView;

    @NonNull private final List<GatherArticle> collections = new ArrayList<>();

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_gather, container, false);
        ButterKnife.bind(this, view);

        collectionsView.setItems(collections);

        // TODO: show loading screen.

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: force English Site.
        // TODO: is this apropos for lifecycle?
        PageService service = WikipediaApp.getInstance().getAppPageService();
        service.requestGatherCollection(COLLECTIONS_WATCHLIST_ID) // TODO: requestGatherCollection_s_
                .retry(RETRY_LIMIT) // TODO: still necessary?
                .subscribeOn(Schedulers.io()) // TODO: still necessary?
                .observeOn(AndroidSchedulers.mainThread()) // TODO: still necessary?
                .subscribe(new CollectionsObserver());

        // TODO: review RxJava, RxAndroid, Retrofit etc examples and best practices.

        // TODO: cancel request in onDed and unsubscribe.
    }

    //  TODO: report on Gather collection_s_ not a singular.
    private class CollectionsObserver implements Observer<GatherCollection> {
        @Override
        public void onCompleted() {
            // TODO: update progress bar / loading spinner.
            // TODO: report on number here.
            L.d("collections received");
        }

        @Override
        public void onError(Throwable e) {
            // TODO: does RxAndroid check for Activity existence somehow?
            // TODO: should FeedbackUtil.showError encompass null activity check and L.e?
            if (getActivity() != null) {
                FeedbackUtil.showError(getActivity(), e);
            }
            L.e(e);
        }

        @Override
        public void onNext(GatherCollection collections) {
            L.d("collections=" + collections);
            GatherFragment.this.collections.addAll(collections.items());
            collectionsView.notifyDataSetChanged();
        }
    }
}