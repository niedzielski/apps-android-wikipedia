package org.wikipedia.gather;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.wikipedia.R;
import org.wikipedia.views.AutoFitRecyclerView;
import org.wikipedia.views.DefaultViewHolder;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GatherCollectionView extends FrameLayout {
    @Bind(R.id.view_gather_collection) AutoFitRecyclerView collectionView;
    private GridLayoutManager collectionViewLayoutManager;

    @NonNull private final Collection<GatherCollectionItem> collection = new ArrayList<>();

    public GatherCollectionView(Context context) {
        super(context);
        init();
    }

    public GatherCollectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GatherCollectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GatherCollectionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_gather_collection, this);
        ButterKnife.bind(this);

        initCollection();

        for (int i = 0; i < 1000; ++i) {
            collection.add(new GatherCollectionItem());
        }
    }

    private void initCollection() {
        collectionViewLayoutManager = new GridLayoutManager(getContext(), collectionView.getColumns());
        collectionView.setLayoutManager(collectionViewLayoutManager);
        collectionView.setCallback(new CollectionViewColumnCallback());

        collectionView.setAdapter(new CollectionViewAdapter());
    }

    private class CollectionViewAdapter extends Adapter<DefaultViewHolder<GatherCollectionItemView>> {
        @Override
        public DefaultViewHolder<GatherCollectionItemView> onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DefaultViewHolder<>(new GatherCollectionItemView(getContext()));
        }

        @Override
        public void onBindViewHolder(DefaultViewHolder<GatherCollectionItemView> holder, int position) {
            holder.getView().update(position);
        }

        @Override
        public int getItemCount() {
            return collection.size();
        }
    }

    private class CollectionViewColumnCallback implements AutoFitRecyclerView.Callback {
        @Override
        public void onColumns(int columns) {
            collectionViewLayoutManager.setSpanCount(columns);
        }
    }
}