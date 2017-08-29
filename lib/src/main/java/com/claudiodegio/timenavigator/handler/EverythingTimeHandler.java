package com.claudiodegio.timenavigator.handler;

import android.content.Context;
import android.util.Pair;

import com.claudiodegio.timenavigator.R;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class EverythingTimeHandler extends TimeHandler {


    public EverythingTimeHandler(Context mContext) {
        super(mContext);
    }

    @Override
    public boolean hasNext(DateTime currentTime, DateTime endTime) {
        return false;
    }

    @Override
    public boolean hasBefore(DateTime currentTime, DateTime startTime) {
        return false;
    }

    @Override
    public DateTime next(DateTime currentTime) {
        return null;
    }

    @Override
    public DateTime before(DateTime currentTime) {
        return null;
    }

    @Override
    public String getTimeFormatted(DateTime currentTime) {
        return mContext.getString(R.string.time_interval_everything);
    }

    @Override
    public Interval calculateInterval(DateTime currentTime) {
        return null;
    }


}
