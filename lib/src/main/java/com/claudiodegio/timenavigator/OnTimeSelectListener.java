package com.claudiodegio.timenavigator;


import java.util.Date;

public interface OnTimeSelectListener {

    void onTimeSelected(Date from, Date to, String name);
}
