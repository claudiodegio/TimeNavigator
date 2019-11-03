package com.claudiodegio.timenavigator.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.claudiodegio.timenavigator.OnTimeSelectListener;
import com.claudiodegio.timenavigator.TimeInterval;
import com.claudiodegio.timenavigator.TimeNavigator;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Screen1Activity extends AppCompatActivity implements OnTimeSelectListener {


    @BindView(R.id.tn)
    TimeNavigator mTimeNavigator;

    @BindView(R.id.tv_period_selected)
    TextView mTvPeriodSelected;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen1);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ButterKnife.bind(this);

        updateLabel();

        mTimeNavigator.setOnTimeSelectListener(this);
    }


    @OnClick({R.id.bt_year, R.id.bt_month, R.id.bt_week, R.id.bt_all,R.id.bt_day, R.id.bt_set_time})
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

            case R.id.bt_set_time:
                DateTime dateTime = DateTime.parse("2018-01-01");
                mTimeNavigator.setCurrentTime(dateTime.toDate());
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

        text += "<br>CurrentTimeString: " + mTimeNavigator.getCurrentTimeAsString();


        mTvPeriodSelected.setText(Html.fromHtml(text));
    }


    @Override
    public void onTimeSelected(Date from, Date to, String name) {
        updateLabel();
    }
}
