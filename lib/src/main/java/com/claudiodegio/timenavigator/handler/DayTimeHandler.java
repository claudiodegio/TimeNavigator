package com.claudiodegio.timenavigator.handler;


import android.content.Context;
import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class DayTimeHandler extends GenericTimeHandler{
    DayTimeHandler(Context mContext) {
        super(mContext);
    }

    @Override
    public DateTime next(DateTime currentTime) {
        DateTime next = new DateTime(currentTime);

        next = next.plusDays(1);
        return next;
    }

    @Override
    public DateTime before(DateTime currentTime) {
        DateTime before = new DateTime(currentTime);

        before = before.minusDays(1);
        return before;
    }

    @Override
    public String getTimeFormatted(DateTime currentTime) {
        int dateFormatFlags;

        if (currentTime.getYear() != DateTime.now().getYear()) {
            dateFormatFlags = DateUtils.FORMAT_SHOW_YEAR;
        } else {
            dateFormatFlags = DateUtils.FORMAT_SHOW_DATE;
        }
        return DateUtils.formatDateTime (mContext, currentTime.getMillis(), dateFormatFlags);
    }

    @Override
    public Interval calculateInterval(DateTime currentTime) {
        DateTime start = new DateTime(currentTime);
        DateTime end = new DateTime(currentTime);

        start = start
                .withHourOfDay(0)
                .withMinuteOfHour(0);

        end = end.withHourOfDay(23)
                .withMinuteOfHour(59);

        return new Interval(start, end);    }
}
