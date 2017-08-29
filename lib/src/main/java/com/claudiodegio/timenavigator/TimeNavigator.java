package com.claudiodegio.timenavigator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.claudiodegio.timenavigator.handler.TimeHandler;
import com.claudiodegio.timenavigator.handler.TimeHandlerFactory;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Date;

public class TimeNavigator extends LinearLayout implements View.OnClickListener {

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

        // TODO da sistemare

        mTimeHandler = TimeHandlerFactory.buildTimeHandler(mTimeInterval, context);
        mCurrentTime = DateTime.now();
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.time_navigator, this);

    }

    private void initStyle(AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TimeNavigator, defStyleAttr, 0);

        if (a != null) {
            if (a.hasValue(R.styleable.TimeNavigator_timeInterval)) {
                mTimeInterval = a.getInt(R.styleable.TimeNavigator_timeInterval, TimeInterval.EVERYTHING);
            }

            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTvCurrent = (TextView) findViewById(R.id.tv_current);

        mIbPrev = (ImageButton) findViewById(R.id.ib_prev);
        mIbPrev.setOnClickListener(this);
        mIbNext = (ImageButton) findViewById(R.id.ib_next);
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
                mOnTimeSelectListener.onTimeSelected(null, null);
            } else {
                mOnTimeSelectListener.onTimeSelected(interval.first, interval.second);
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

        mTvCurrent.setText(currentTimeFormatted);
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

    public void setOnTimeSelectListener(OnTimeSelectListener mOnTimeSelectListener) {
        this.mOnTimeSelectListener = mOnTimeSelectListener;
    }

}
