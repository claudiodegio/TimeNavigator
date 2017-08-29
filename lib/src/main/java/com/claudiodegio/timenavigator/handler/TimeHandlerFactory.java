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
           default:
                return new ToImplementsTimeHandler(context);
        }

    }

}
