package org.wikipedia.gather;

import android.content.Context;
import android.content.Intent;

import org.wikipedia.activity.CompatSingleFragmentActivity;

public class GatherActivity extends CompatSingleFragmentActivity<GatherFragment> {
    public static Intent newIntent(Context context) {
        return new Intent(context, GatherActivity.class);
    }

    @Override
    protected GatherFragment createFragment() {
        return GatherFragment.newInstance();
    }
}