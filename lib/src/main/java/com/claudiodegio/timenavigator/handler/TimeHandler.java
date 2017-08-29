package com.claudiodegio.timenavigator.handler;

import android.content.Context;
import android.util.Pair;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public abstract class TimeHandler {


    protected Context mContext;

    TimeHandler(Context mContext) {
        this.mContext = mContext;
    }

    abstract public boolean hasNext(final DateTime currentTime, final DateTime endTime);
    abstract public boolean hasBefore(final DateTime currentTime, final DateTime startTime);

    abstract public DateTime next(final DateTime currentTime);
    abstract public DateTime before(final DateTime currentTime);

    abstract public String getTimeFormatted(DateTime currentTime);

    abstract public Interval calculateInterval(DateTime currentTime);
}
