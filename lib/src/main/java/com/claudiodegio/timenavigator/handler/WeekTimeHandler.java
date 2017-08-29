package com.claudiodegio.timenavigator.handler;


import android.content.Context;
import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

public class WeekTimeHandler extends GenericTimeHandler{
    WeekTimeHandler(Context mContext) {
        super(mContext);
    }

    @Override
    public DateTime next(DateTime currentTime) {
        DateTime next = new DateTime(currentTime);

        next = next.plusWeeks(1);
        return next;
    }

    @Override
    public DateTime before(DateTime currentTime) {
        DateTime before = new DateTime(currentTime);

        before = before.minusWeeks(1);
        return before;
    }

    @Override
    public String getTimeFormatted(DateTime currentTime) {
        int dateFormatFlags;
        dateFormatFlags = 0;

        if (currentTime.getYear() != LocalDate.now().getYear()) {
            dateFormatFlags |= DateUtils.FORMAT_SHOW_YEAR;
        }

        Interval interval = calculateInterval(currentTime);
        return DateUtils.formatDateRange (mContext, interval.getStartMillis(), interval.getEndMillis(), dateFormatFlags);
    }

    @Override
    public Interval calculateInterval(DateTime currentTime) {
        DateTime start = new DateTime(currentTime);
        DateTime end = new DateTime(currentTime);

        start = start
                .withDayOfWeek(1)
                .withHourOfDay(0)
                .withMinuteOfHour(0);

        end = end
                .withDayOfWeek(7)
                .withHourOfDay(23)
                .withMinuteOfHour(59);

        return new Interval(start, end);
    }
}
