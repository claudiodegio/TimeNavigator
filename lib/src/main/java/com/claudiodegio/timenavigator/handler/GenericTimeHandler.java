package com.claudiodegio.timenavigator.handler;

import android.content.Context;

import org.joda.time.DateTime;

/**
 * Created by claud on 29/08/2017.
 */

public abstract class GenericTimeHandler extends TimeHandler {
    GenericTimeHandler(Context mContext) {
        super(mContext);
    }

    @Override
    public boolean hasNext(DateTime currentTime, DateTime endTime) {

        if (endTime == null) return true;

        DateTime next = next(currentTime);

        if (next.isAfter(endTime)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean hasBefore(DateTime currentTime, DateTime startTime) {

        if (startTime == null) return true;

        DateTime before = before(currentTime);

        if (before.isBefore(startTime)) {
            return false;
        }

        return true;
    }
}
