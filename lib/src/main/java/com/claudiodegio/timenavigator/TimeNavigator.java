package com.claudiodegio.timenavigator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.claudiodegio.timenavigator.handler.TimeHandler;
import com.claudiodegio.timenavigator.handler.TimeHandlerFactory;

import org.apache.commons.text.WordUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Date;

public class TimeNavigator extends FrameLayout implements View.OnClickListener {

    private int mTimeInterval;
    private TimeHandler mTimeHandler;
    private DateTime mCurrentTime;

    private DateTime mStartTime = null;
    private DateTime mEndTime = null;

    // View
    private TextView mTvCurrent;
    private ImageButton mIbNext;
    private ImageButton mIbPrev;

    private OnTimeSelectListener mOnTimeSelectListener;

    public TimeNavigator(Context context) {
        this(context, null);
    }

    public TimeNavigator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeNavigator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
        initStyle(attrs, defStyleAttr);

        mTimeHandler = TimeHandlerFactory.buildTimeHandler(mTimeInterval, context);
        mCurrentTime = DateTime.now();


    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.time_navigator, this);

        mTvCurrent =  findViewById(R.id.tv_current);
        mIbPrev =  findViewById(R.id.ib_prev);
        mIbNext =  findViewById(R.id.ib_next);
    }

    private void initStyle(AttributeSet attrs, int defStyleAttr) {

        int color = 0;
        int fontSize = 0;
        int arrowsSize;

        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TimeNavigator, defStyleAttr, 0);

        if (a != null) {
            mTimeInterval = a.getInt(R.styleable.TimeNavigator_tnInterval, TimeInterval.EVERYTHING);

            color = a.getColor(R.styleable.TimeNavigator_tnColor, Color.WHITE);
            setColor(color);


            if (a.hasValue(R.styleable.TimeNavigator_tnTextSize)) {
                fontSize = a.getDimensionPixelSize(R.styleable.TimeNavigator_tnTextSize, 0);
                mTvCurrent.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
            }


            if (a.hasValue(R.styleable.TimeNavigator_tnArrowSize)) {
                arrowsSize = a.getDimensionPixelSize(R.styleable.TimeNavigator_tnArrowSize, 0);
                ViewGroup.LayoutParams params = mIbNext.getLayoutParams();
                params.height = arrowsSize;
                params.width = arrowsSize;
                mIbNext.setLayoutParams(params);
                mIbNext.requestLayout();
                params = mIbPrev.getLayoutParams();
                params.height = arrowsSize;
                params.width = arrowsSize;
                mIbPrev.setLayoutParams(params);
                mIbPrev.requestLayout();
            }


            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mIbPrev.setOnClickListener(this);
        mIbNext.setOnClickListener(this);

        updateTimeLabelAndButton();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_next) {
            next();
        } else {
            before();
        }
    }

    public void next(){
        if (mTimeHandler.hasNext(mCurrentTime, mEndTime)) {
            mCurrentTime = mTimeHandler.next(mCurrentTime);
            updateTimeLabelAndButton();
            notifyNewTimeSelected();
        }
    }

    public void before(){
        if (mTimeHandler.hasBefore(mCurrentTime, mStartTime)) {
            mCurrentTime = mTimeHandler.before(mCurrentTime);
            updateTimeLabelAndButton();
            notifyNewTimeSelected();
        }
    }

    private void notifyNewTimeSelected(){
        Pair<Date, Date> interval;
        if (mOnTimeSelectListener != null) {
            interval = getCurrentInterval();
            if (interval == null) {
                mOnTimeSelectListener.onTimeSelected(null, null, null);
            } else {
                mOnTimeSelectListener.onTimeSelected(interval.first, interval.second, mTvCurrent.getText().toString());
            }
        }
    }

    private void updateTimeLabelAndButton(){
        String currentTimeFormatted;

        // Update of image button
        mIbPrev.setVisibility(INVISIBLE);
        mIbNext.setVisibility(INVISIBLE);

        if (mTimeHandler.hasNext(mCurrentTime, mEndTime)) {
            mIbNext.setVisibility(VISIBLE);
        }

        if (mTimeHandler.hasBefore(mCurrentTime, mStartTime)) {
            mIbPrev.setVisibility(VISIBLE);
        }

        // Update of text view
        currentTimeFormatted = mTimeHandler.getTimeFormatted(mCurrentTime);

        mTvCurrent.setText(WordUtils.capitalizeFully(currentTimeFormatted));
    }

    public void setTimeInterval(@TimeInterval int timeInterval) {
        mTimeInterval = timeInterval;

        mTimeHandler = TimeHandlerFactory.buildTimeHandler(mTimeInterval, getContext());

        updateTimeLabelAndButton();
    }

    public void setTimeLimits(Date startTime, Date endTime) {

        this.mStartTime = new DateTime(startTime);
        this.mEndTime = new DateTime(endTime);

        updateTimeLabelAndButton();
    }

    public @TimeInterval int getTimeInterval() {
        return mTimeInterval;
    }

    public Pair<Date, Date> getCurrentInterval(){
        Interval interval;

        interval = mTimeHandler.calculateInterval(mCurrentTime);

        if (interval == null) {
            return null;
        }

        return Pair.create(interval.getStart().toDate(), interval.getEnd().toDate());
    }

    public Date getCurrentTime(){
        return mCurrentTime.toDate();
    }

    public String getCurrentTimeAsString(){
        return mTvCurrent.getText().toString();
    }

    public void setOnTimeSelectListener(OnTimeSelectListener mOnTimeSelectListener) {
        this.mOnTimeSelectListener = mOnTimeSelectListener;
    }

    public void setColor(int color){

        mTvCurrent.setTextColor(color);

        DrawableCompat.setTint(mIbNext.getDrawable(), color);
        DrawableCompat.setTint(mIbPrev.getDrawable(), color);

    }

    public void setCurrentTime(Date time) {
        this.mCurrentTime = new DateTime(time);
        updateTimeLabelAndButton();
    }
}
