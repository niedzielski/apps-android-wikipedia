package org.wikipedia.gather;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.wikipedia.R;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GatherArticleView extends FrameLayout {
    @Bind(R.id.view_gather_article_image) ImageView imageView;
    @Bind(R.id.view_gather_article_text) TextView textView;

    public GatherArticleView(Context context) {
        super(context);
        init();
    }

    public GatherArticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GatherArticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GatherArticleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void update(@NonNull GatherArticle item) {
        Picasso.with(getContext()).load(item.thumbnail())
                .placeholder(R.drawable.ic_pageimage_placeholder)
                .into(imageView);

        textView.setText(item.title());
        final int fullAlpha = 0xff000000;
        final int fullColor = 0x00ffffff;
        int color = new Random().nextInt(fullColor) | fullAlpha;
        textView.setTextColor(color);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        // todo: is padding in the recycler view?
        // todo: dimens
        ((MarginLayoutParams) getLayoutParams()).topMargin = 16;
        ((MarginLayoutParams) getLayoutParams()).rightMargin = 16;
        ((MarginLayoutParams) getLayoutParams()).bottomMargin = 16;
        ((MarginLayoutParams) getLayoutParams()).leftMargin = 16;
    }

    private void init() {
        // todo: do i normally suffer a non-merge node for these attributes? i can't remember.
        setMinimumWidth(256);
        setMinimumHeight(256);


        setBackgroundResource(R.drawable.gather_article_background);

        // todo: recheck new recyclerview apis

        inflate(getContext(), R.layout.view_gather_article, this);
        ButterKnife.bind(this);
    }
}