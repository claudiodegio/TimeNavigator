package com.claudiodegio.timenavigator.handler;


import android.content.Context;
import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class MonthTimeHandler extends GenericTimeHandler{
    MonthTimeHandler(Context mContext) {
        super(mContext);
    }

    @Override
    public DateTime next(DateTime currentTime) {
        DateTime next = new DateTime(currentTime);

        next = next.plusMonths(1);
        return next;
    }

    @Override
    public DateTime before(DateTime currentTime) {
        DateTime before = new DateTime(currentTime);

        before = before.minusMonths(1);
        return before;
    }

    @Override
    public String getTimeFormatted(DateTime currentTime) {
        int dateFormatFlags;

        if (currentTime.getYear() != DateTime.now().getYear()) {
            dateFormatFlags = DateUtils.FORMAT_NO_MONTH_DAY|DateUtils.FORMAT_SHOW_YEAR;
        } else {
            dateFormatFlags = DateUtils.FORMAT_NO_MONTH_DAY;
        }
        return DateUtils.formatDateTime (mContext, currentTime.getMillis(), dateFormatFlags);
    }

    @Override
    public Interval calculateInterval(DateTime currentTime) {
        DateTime start = new DateTime(currentTime);
        DateTime end = new DateTime(currentTime);

        start = start.dayOfMonth()
                .withMinimumValue()
                .withHourOfDay(0)
                .withMinuteOfHour(0);

        end = end.dayOfMonth()
                .withMaximumValue()
                .withHourOfDay(23)
                .withMinuteOfHour(59);

        return new Interval(start, end);    }
}
