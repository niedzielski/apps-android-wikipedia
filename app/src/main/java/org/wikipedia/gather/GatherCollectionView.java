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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GatherCollectionView extends FrameLayout {
    @Bind(R.id.view_gather_collection) AutoFitRecyclerView collectionView;
    private GridLayoutManager collectionViewLayoutManager;

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

    public void setItems(@NonNull List<GatherArticle> items) {
        // TODO: should this class be responsible for showing a "no items in collection" view?
        collectionView.setAdapter(new CollectionViewAdapter(items));
    }

    public void notifyDataSetChanged() {
        collectionView.getAdapter().notifyDataSetChanged();
    }

    private void init() {
        inflate(getContext(), R.layout.view_gather_collection, this);
        ButterKnife.bind(this);

        initCollection();
    }

    private void initCollection() {
        collectionViewLayoutManager = new GridLayoutManager(getContext(), collectionView.getColumns());
        collectionView.setLayoutManager(collectionViewLayoutManager);
        collectionView.setCallback(new CollectionViewColumnCallback());
    }

    private class CollectionViewAdapter extends Adapter<DefaultViewHolder<GatherArticleView>> {
        @NonNull private final List<GatherArticle> items;

        CollectionViewAdapter(@NonNull List<GatherArticle> items) {
            this.items = items;
        }

        @Override
        public DefaultViewHolder<GatherArticleView> onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DefaultViewHolder<>(new GatherArticleView(getContext()));
        }

        @Override
        public void onBindViewHolder(DefaultViewHolder<GatherArticleView> holder, int position) {
            holder.getView().update(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private class CollectionViewColumnCallback implements AutoFitRecyclerView.Callback {
        @Override
        public void onColumns(int columns) {
            collectionViewLayoutManager.setSpanCount(columns);
        }
    }
}