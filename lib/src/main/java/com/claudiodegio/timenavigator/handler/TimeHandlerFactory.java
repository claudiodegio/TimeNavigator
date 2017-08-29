package com.claudiodegio.timenavigator.handler;


import android.content.Context;

import com.claudiodegio.timenavigator.TimeInterval;

public class TimeHandlerFactory {


    static public TimeHandler buildTimeHandler(@TimeInterval int timeInterval, final Context context){


        switch (timeInterval) {
            case TimeInterval.EVERYTHING:
                return new EverythingTimeHandler(context);
            case TimeInterval.YEAR:
                return new YearTimeHandler(context);
            case TimeInterval.MONTH:
                return new MonthTimeHandler(context);
            case TimeInterval.WEEK:
                return new WeekTimeHandler(context);
            case TimeInterval.DAY:
                return new DayTimeHandler(context);
           default:
                return new ToImplementsTimeHandler(context);
        }

    }

}
