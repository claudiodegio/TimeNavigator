package com.claudiodegio.timenavigator;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by claud on 29/08/2017.
 */


@Retention(SOURCE)
public @interface TimeInterval {
    int EVERYTHING = 0;
    int YEAR = 1;
    int MONTH = 2;
    int WEEK = 3;
    int DAY = 4;
}
