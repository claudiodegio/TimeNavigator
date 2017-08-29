package com.claudiodegio.timenavigator.handler;

import android.content.Context;
import android.util.Pair;


import org.joda.time.DateTime;
import org.joda.time.Interval;

public class YearTimeHandler extends GenericTimeHandler{

    public YearTimeHandler(Context mContext) {
        super(mContext);
    }

    @Override
    public DateTime next(DateTime currentTime) {
        DateTime next = new DateTime(currentTime);

        next = next.plusYears(1);
        return next;
    }

    @Override
    public DateTime before(DateTime currentTime) {
        DateTime before = new DateTime(currentTime);

        before = before.minusYears(1);
        return before;
    }

    @Override
    public String getTimeFormatted(DateTime currentTime) {
        return Integer.toString(currentTime.getYear());
    }

    @Override
    public Interval calculateInterval(DateTime currentTime) {
        DateTime start = new DateTime(currentTime);
        DateTime end = new DateTime(currentTime);

        start = start.withDayOfYear(1)
                .withHourOfDay(0)
                .withMinuteOfHour(0);

        end = end.dayOfYear()
                .withMaximumValue()
                .withHourOfDay(23)
                .withMinuteOfHour(59);

        return new Interval(start, end);
    }

}
