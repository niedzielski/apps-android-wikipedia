package org.wikipedia.gather;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.wikipedia.R;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GatherCollectionItemView extends FrameLayout {
    @Bind(R.id.view_gather_collection_item_text) TextView textView;

    public GatherCollectionItemView(Context context) {
        super(context);
        init();
    }

    public GatherCollectionItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GatherCollectionItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GatherCollectionItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void update(int item) {
        textView.setText(String.valueOf(item));

        final int fullAlpha = 0xff000000;
        final int fullColor = 0x00ffffff;
        int color = new Random().nextInt(fullColor) | fullAlpha;
        textView.setTextColor(color);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        // todo: is padding in the recycler view?
        ((MarginLayoutParams) getLayoutParams()).topMargin = 16;
        ((MarginLayoutParams) getLayoutParams()).rightMargin = 16;
        ((MarginLayoutParams) getLayoutParams()).bottomMargin = 16;
        ((MarginLayoutParams) getLayoutParams()).leftMargin = 16;
    }

    private void init() {
        // todo: do i normally suffer a non-merge node for these attributes? i can't remember.
        setMinimumWidth(256);
        setMinimumHeight(256);


        setBackgroundResource(R.drawable.gather_collection_item_background);

        // todo: recheck new recyclerview apis

        inflate(getContext(), R.layout.view_gather_collection_item, this);
        ButterKnife.bind(this);
    }
}