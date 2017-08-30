package com.claudiodegio.timenavigator.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.claudiodegio.timenavigator.OnTimeSelectListener;
import com.claudiodegio.timenavigator.TimeInterval;
import com.claudiodegio.timenavigator.TimeNavigator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Screen3Activity extends AppCompatActivity implements OnTimeSelectListener {


    @BindView(R.id.tn)
    TimeNavigator mTimeNavigator;

    @BindView(R.id.tv_period_selected)
    TextView mTvPeriodSelected;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen3);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ButterKnife.bind(this);

        updateLabel();

        mTimeNavigator.setOnTimeSelectListener(this);
    }


    @OnClick({R.id.bt_year, R.id.bt_month, R.id.bt_week, R.id.bt_all,R.id.bt_day})
    public void actionOnButtonClick(View view){

        switch (view.getId()) {
            case R.id.bt_year:
                mTimeNavigator.setTimeInterval(TimeInterval.YEAR);
                break;

            case R.id.bt_month:
                mTimeNavigator.setTimeInterval(TimeInterval.MONTH);
                break;

            case R.id.bt_day:
                mTimeNavigator.setTimeInterval(TimeInterval.DAY);
                break;

            case R.id.bt_week:
                mTimeNavigator.setTimeInterval(TimeInterval.WEEK);
                break;

            case R.id.bt_all:
                mTimeNavigator.setTimeInterval(TimeInterval.EVERYTHING);
                break;

        }

        updateLabel();

    }

    private void updateLabel(){

        String text;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        Pair<Date, Date> interval =  mTimeNavigator.getCurrentInterval();


        if (interval == null) {
            text = "Interval: <no Period>";
        } else {

            text = "From: ";

            text += df.format(interval.first);

            text += "<br>To: " + df.format(interval.second);
        }


        Date currentTime = mTimeNavigator.getCurrentTime();

        text += "<br>CurrentTime: " + df.format(currentTime);

        mTvPeriodSelected.setText(Html.fromHtml(text));
    }

    @Override
    public void onTimeSelected(Date from, Date to) {
        updateLabel();
    }
}
