package com.claudiodegio.timenavigator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class TimeNavigator extends LinearLayout {

    public TimeNavigator(Context context) {
        this(context, null);
    }

    public TimeNavigator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeNavigator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initializeViews(context);
    }


    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.time_navigator, this);
    }
}
