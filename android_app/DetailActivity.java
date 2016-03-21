package com.example.shaolun.homework9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    private String state;
    private String jsont;
    private String unit;
    private String city;
    private String degree;
    private ImageView k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final TableLayout t1 = (TableLayout) findViewById(R.id.tableLayout);
        final TableLayout t2 = (TableLayout) findViewById(R.id.tableLayout2);
        final TableLayout t3 = (TableLayout) findViewById(R.id.tableLayout3);
        final TableLayout plus = (TableLayout) findViewById(R.id.tableplus);
        final Button plusb = (Button) findViewById(R.id.plus);
        final TableRow r13 = (TableRow) findViewById(R.id.r13);
        final TableRow r14 = (TableRow) findViewById(R.id.r14);
        final TableRow r15 = (TableRow) findViewById(R.id.r15);
        final TableRow r16 = (TableRow) findViewById(R.id.r16);
        final TableRow r17 = (TableRow) findViewById(R.id.r17);
        final TableRow r18 = (TableRow) findViewById(R.id.r18);
        final TableRow r19 = (TableRow) findViewById(R.id.r19);
        final TableRow r20 = (TableRow) findViewById(R.id.r20);
        final TableRow r21 = (TableRow) findViewById(R.id.r21);
        final TableRow r22 = (TableRow) findViewById(R.id.r22);
        final TableRow r23 = (TableRow) findViewById(R.id.r23);
        final TableRow r24 = (TableRow) findViewById(R.id.r24);
        plus.setVisibility(View.VISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.VISIBLE);
        t3.setVisibility(View.VISIBLE);
        r13.setVisibility(View.INVISIBLE);
        r14.setVisibility(View.INVISIBLE);
        r15.setVisibility(View.INVISIBLE);
        r16.setVisibility(View.INVISIBLE);
        r17.setVisibility(View.INVISIBLE);
        r18.setVisibility(View.INVISIBLE);
        r19.setVisibility(View.INVISIBLE);
        r20.setVisibility(View.INVISIBLE);
        r21.setVisibility(View.INVISIBLE);
        r22.setVisibility(View.INVISIBLE);
        r23.setVisibility(View.INVISIBLE);
        r24.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        city = intent.getStringExtra("CITY");
        state = intent.getStringExtra("STATE");
        jsont = intent.getStringExtra("JSON");
        unit = intent.getStringExtra("TEMP");

        if (unit.equals("us")) {
            degree = (char) 0x00B0 + "F";
        }
        else {
            degree = (char) 0x00B0 + "C";
        }
        TextView test = (TextView) findViewById(R.id.textView9);
        test.setText("More Details for " + city + ", " + state);
        test = (TextView) findViewById(R.id.temp);
        test.setText("Temp(" + degree + ") ");
        //parse JSON
        try {
            JSONObject json = new JSONObject(jsont);
            String off = json.getString("offset");
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            int offset = Integer.parseInt(off);

            //hour1 time
            String time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getString("time");
            long unixSeconds = Long.parseLong(time);
            Date date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            String formattedDate = sdf.format(date);
            TextView textview = (TextView)findViewById(R.id.hour1);
            textview.setText(formattedDate);
            //hour1 image
            k = (ImageView) findViewById(R.id.imagehour1);
            String qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getString("icon");
            picture(qq);
            //hour1 temp
            String temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getString("temperature");
            double num = Double.parseDouble(temp);
            int i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp1);
            textview.setText(temp);

            //hour2 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour2);
            textview.setText(formattedDate);
            //hour2 image
            k = (ImageView) findViewById(R.id.imagehour2);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getString("icon");
            picture(qq);
            //hour2 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp2);
            textview.setText(temp);

            //hour3 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour3);
            textview.setText(formattedDate);
            //hour3 image
            k = (ImageView) findViewById(R.id.imagehour3);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getString("icon");
            picture(qq);
            //hour3 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp3);
            textview.setText(temp);

            //hour4 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour4);
            textview.setText(formattedDate);
            //hour4 image
            k = (ImageView) findViewById(R.id.imagehour4);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getString("icon");
            picture(qq);
            //hour4 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp4);
            textview.setText(temp);

            //hour5 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour5);
            textview.setText(formattedDate);
            //hour5 image
            k = (ImageView) findViewById(R.id.imagehour5);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getString("icon");
            picture(qq);
            //hour5 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp5);
            textview.setText(temp);

            //hour6 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour6);
            textview.setText(formattedDate);
            //hour6 image
            k = (ImageView) findViewById(R.id.imagehour6);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getString("icon");
            picture(qq);
            //hour6 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(5).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp6);
            textview.setText(temp);

            //hour7 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour7);
            textview.setText(formattedDate);
            //hour7 image
            k = (ImageView) findViewById(R.id.imagehour7);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getString("icon");
            picture(qq);
            //hour7 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(6).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp7);
            textview.setText(temp);

            //hour8 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(7).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour8);
            textview.setText(formattedDate);
            //hour8 image
            k = (ImageView) findViewById(R.id.imagehour8);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(7).getString("icon");
            picture(qq);
            //hour8 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(7).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp8);
            textview.setText(temp);

            //hour9 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(8).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour9);
            textview.setText(formattedDate);
            //hour9 image
            k = (ImageView) findViewById(R.id.imagehour9);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(8).getString("icon");
            picture(qq);
            //hour9 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(8).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp9);
            textview.setText(temp);

            //hour10 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(9).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour10);
            textview.setText(formattedDate);
            //hour10 image
            k = (ImageView) findViewById(R.id.imagehour10);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(9).getString("icon");
            picture(qq);
            //hour10 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(9).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp10);
            textview.setText(temp);

            //hour11 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(10).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour11);
            textview.setText(formattedDate);
            //hour11 image
            k = (ImageView) findViewById(R.id.imagehour11);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(10).getString("icon");
            picture(qq);
            //hour11 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(10).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp11);
            textview.setText(temp);

            //hour12 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(11).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour12);
            textview.setText(formattedDate);
            //hour12 image
            k = (ImageView) findViewById(R.id.imagehour12);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(11).getString("icon");
            picture(qq);
            //hour12 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(11).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp12);
            textview.setText(temp);

            //hour13 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(12).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour13);
            textview.setText(formattedDate);
            //hour13 image
            k = (ImageView) findViewById(R.id.imagehour13);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(12).getString("icon");
            picture(qq);
            //hour13 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(12).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp13);
            textview.setText(temp);

            //hour14 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(13).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour14);
            textview.setText(formattedDate);
            //hour14 image
            k = (ImageView) findViewById(R.id.imagehour14);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(13).getString("icon");
            picture(qq);
            //hour14 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(13).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp14);
            textview.setText(temp);

            //hour15 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(14).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour15);
            textview.setText(formattedDate);
            //hour15 image
            k = (ImageView) findViewById(R.id.imagehour15);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(14).getString("icon");
            picture(qq);
            //hour15 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(14).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp15);
            textview.setText(temp);

            //hour16 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(15).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour16);
            textview.setText(formattedDate);
            //hour16 image
            k = (ImageView) findViewById(R.id.imagehour16);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(15).getString("icon");
            picture(qq);
            //hour16 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(15).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp16);
            textview.setText(temp);

            //hour17 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(16).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour17);
            textview.setText(formattedDate);
            //hour17 image
            k = (ImageView) findViewById(R.id.imagehour17);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(16).getString("icon");
            picture(qq);
            //hour17 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(16).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp17);
            textview.setText(temp);


            //hour18 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(17).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour18);
            textview.setText(formattedDate);
            //hour18 image
            k = (ImageView) findViewById(R.id.imagehour18);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(17).getString("icon");
            picture(qq);
            //hour18 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(17).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp18);
            textview.setText(temp);


            //hour19 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(18).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour19);
            textview.setText(formattedDate);
            //hour19 image
            k = (ImageView) findViewById(R.id.imagehour19);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(18).getString("icon");
            picture(qq);
            //hour19 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(18).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp19);
            textview.setText(temp);


            //hour20 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(19).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour20);
            textview.setText(formattedDate);
            //hour20 image
            k = (ImageView) findViewById(R.id.imagehour20);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(19).getString("icon");
            picture(qq);
            //hour20 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(19).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp20);
            textview.setText(temp);


            //hour21 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(20).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour21);
            textview.setText(formattedDate);
            //hour21 image
            k = (ImageView) findViewById(R.id.imagehour21);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(20).getString("icon");
            picture(qq);
            //hour21 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(20).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp21);
            textview.setText(temp);


            //hour22 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(21).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour22);
            textview.setText(formattedDate);
            //hour22 image
            k = (ImageView) findViewById(R.id.imagehour22);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(21).getString("icon");
            picture(qq);
            //hour22 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(21).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp22);
            textview.setText(temp);

            //hour23 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(22).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour23);
            textview.setText(formattedDate);
            //hour23 image
            k = (ImageView) findViewById(R.id.imagehour23);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(22).getString("icon");
            picture(qq);
            //hour23 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(22).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp23);
            textview.setText(temp);

            //hour24 time
            time = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(23).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            textview = (TextView)findViewById(R.id.hour24);
            textview.setText(formattedDate);
            //hour24 image
            k = (ImageView) findViewById(R.id.imagehour24);
            qq = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(23).getString("icon");
            picture(qq);
            //hour24 temp
            temp = json.getJSONObject("hourly").getJSONArray("data").getJSONObject(23).getString("temperature");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            textview = (TextView) findViewById(R.id.hourtemp24);
            textview.setText(temp);

            //weekday
            SimpleDateFormat weekday = new SimpleDateFormat("EEEE");
            SimpleDateFormat month = new SimpleDateFormat("MMM");
            SimpleDateFormat day = new SimpleDateFormat("dd");

            //day1
            time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(1).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);

            String week = weekday.format(date);
            String mon = month.format(date);
            String dayy = day.format(date);
            textview = (TextView) findViewById(R.id.day1);
            textview.setText(week + ", " + mon + " " + dayy);

            k = (ImageView) findViewById(R.id.dayimg1);
            qq = json.getJSONObject("daily").getJSONArray("data").getJSONObject(1).getString("icon");
            picture(qq);

            String tempMin = json.getJSONObject("daily").getJSONArray("data").getJSONObject(1).getString("temperatureMin");
            Double num1 = Double.parseDouble(tempMin);
            int i1 = (int)Math.round(num1);
            tempMin = Integer.toString(i1);
            String tempMax = json.getJSONObject("daily").getJSONArray("data").getJSONObject(1).getString("temperatureMax");
            Double num2 = Double.parseDouble(tempMax);
            int i2 = (int)Math.round(num2);
            tempMax = Integer.toString(i2);
            textview = (TextView) findViewById(R.id.dailytemp1);
            textview.setText("Min: " + tempMin + degree + " | Max: " + tempMax + degree );

            //day2
            time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(2).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);

            week = weekday.format(date);
            mon = month.format(date);
            dayy = day.format(date);
            textview = (TextView) findViewById(R.id.day2);
            textview.setText(week + ", " + mon + " " + dayy);

            k = (ImageView) findViewById(R.id.dayimg2);
            qq = json.getJSONObject("daily").getJSONArray("data").getJSONObject(2).getString("icon");
            picture(qq);

            tempMin = json.getJSONObject("daily").getJSONArray("data").getJSONObject(2).getString("temperatureMin");
            num1 = Double.parseDouble(tempMin);
            i1 = (int)Math.round(num1);
            tempMin = Integer.toString(i1);
            tempMax = json.getJSONObject("daily").getJSONArray("data").getJSONObject(2).getString("temperatureMax");
            num2 = Double.parseDouble(tempMax);
            i2 = (int)Math.round(num2);
            tempMax = Integer.toString(i2);
            textview = (TextView) findViewById(R.id.dailytemp2);
            textview.setText("Min: " + tempMin + degree + " | Max: " + tempMax + degree );

            //day3
            time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(3).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);

            week = weekday.format(date);
            mon = month.format(date);
            dayy = day.format(date);
            textview = (TextView) findViewById(R.id.day3);
            textview.setText(week + ", " + mon + " " + dayy);

            k = (ImageView) findViewById(R.id.dayimg3);
            qq = json.getJSONObject("daily").getJSONArray("data").getJSONObject(3).getString("icon");
            picture(qq);

            tempMin = json.getJSONObject("daily").getJSONArray("data").getJSONObject(3).getString("temperatureMin");
            num1 = Double.parseDouble(tempMin);
            i1 = (int)Math.round(num1);
            tempMin = Integer.toString(i1);
            tempMax = json.getJSONObject("daily").getJSONArray("data").getJSONObject(3).getString("temperatureMax");
            num2 = Double.parseDouble(tempMax);
            i2 = (int)Math.round(num2);
            tempMax = Integer.toString(i2);
            textview = (TextView) findViewById(R.id.dailytemp3);
            textview.setText("Min: " + tempMin + degree + " | Max: " + tempMax + degree );


            //day4
            time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(4).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);

            week = weekday.format(date);
            mon = month.format(date);
            dayy = day.format(date);
            textview = (TextView) findViewById(R.id.day4);
            textview.setText(week + ", " + mon + " " + dayy);

            k = (ImageView) findViewById(R.id.dayimg4);
            qq = json.getJSONObject("daily").getJSONArray("data").getJSONObject(4).getString("icon");
            picture(qq);

            tempMin = json.getJSONObject("daily").getJSONArray("data").getJSONObject(4).getString("temperatureMin");
            num1 = Double.parseDouble(tempMin);
            i1 = (int)Math.round(num1);
            tempMin = Integer.toString(i1);
            tempMax = json.getJSONObject("daily").getJSONArray("data").getJSONObject(4).getString("temperatureMax");
            num2 = Double.parseDouble(tempMax);
            i2 = (int)Math.round(num2);
            tempMax = Integer.toString(i2);
            textview = (TextView) findViewById(R.id.dailytemp4);
            textview.setText("Min: " + tempMin + degree + " | Max: " + tempMax + degree );


            //day5
            time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(5).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);

            week = weekday.format(date);
            mon = month.format(date);
            dayy = day.format(date);
            textview = (TextView) findViewById(R.id.day5);
            textview.setText(week + ", " + mon + " " + dayy);

            k = (ImageView) findViewById(R.id.dayimg5);
            qq = json.getJSONObject("daily").getJSONArray("data").getJSONObject(5).getString("icon");
            picture(qq);

            tempMin = json.getJSONObject("daily").getJSONArray("data").getJSONObject(5).getString("temperatureMin");
            num1 = Double.parseDouble(tempMin);
            i1 = (int)Math.round(num1);
            tempMin = Integer.toString(i1);
            tempMax = json.getJSONObject("daily").getJSONArray("data").getJSONObject(5).getString("temperatureMax");
            num2 = Double.parseDouble(tempMax);
            i2 = (int)Math.round(num2);
            tempMax = Integer.toString(i2);
            textview = (TextView) findViewById(R.id.dailytemp5);
            textview.setText("Min: " + tempMin + degree + " | Max: " + tempMax + degree );



            //day6
            time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(6).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);

            week = weekday.format(date);
            mon = month.format(date);
            dayy = day.format(date);
            textview = (TextView) findViewById(R.id.day6);
            textview.setText(week + ", " + mon + " " + dayy);

            k = (ImageView) findViewById(R.id.dayimg6);
            qq = json.getJSONObject("daily").getJSONArray("data").getJSONObject(6).getString("icon");
            picture(qq);

            tempMin = json.getJSONObject("daily").getJSONArray("data").getJSONObject(6).getString("temperatureMin");
            num1 = Double.parseDouble(tempMin);
            i1 = (int)Math.round(num1);
            tempMin = Integer.toString(i1);
            tempMax = json.getJSONObject("daily").getJSONArray("data").getJSONObject(6).getString("temperatureMax");
            num2 = Double.parseDouble(tempMax);
            i2 = (int)Math.round(num2);
            tempMax = Integer.toString(i2);
            textview = (TextView) findViewById(R.id.dailytemp6);
            textview.setText("Min: " + tempMin + degree + " | Max: " + tempMax + degree );



            //day7
            time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(7).getString("time");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);

            week = weekday.format(date);
            mon = month.format(date);
            dayy = day.format(date);
            textview = (TextView) findViewById(R.id.day7);
            textview.setText(week + ", " + mon + " " + dayy);

            k = (ImageView) findViewById(R.id.dayimg7);
            qq = json.getJSONObject("daily").getJSONArray("data").getJSONObject(7).getString("icon");
            picture(qq);

            tempMin = json.getJSONObject("daily").getJSONArray("data").getJSONObject(7).getString("temperatureMin");
            num1 = Double.parseDouble(tempMin);
            i1 = (int)Math.round(num1);
            tempMin = Integer.toString(i1);
            tempMax = json.getJSONObject("daily").getJSONArray("data").getJSONObject(7).getString("temperatureMax");
            num2 = Double.parseDouble(tempMax);
            i2 = (int)Math.round(num2);
            tempMax = Integer.toString(i2);
            textview = (TextView) findViewById(R.id.dailytemp7);
            textview.setText("Min: " + tempMin + degree + " | Max: " + tempMax + degree );

        }
        catch (Exception e){
            Log.e("Can not parse", "Error converting result " + e.toString());
        }


        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                t1.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                plus.setVisibility(View.VISIBLE);
                r13.setVisibility(View.INVISIBLE);
                r14.setVisibility(View.INVISIBLE);
                r15.setVisibility(View.INVISIBLE);
                r16.setVisibility(View.INVISIBLE);
                r17.setVisibility(View.INVISIBLE);
                r18.setVisibility(View.INVISIBLE);
                r19.setVisibility(View.INVISIBLE);
                r20.setVisibility(View.INVISIBLE);
                r21.setVisibility(View.INVISIBLE);
                r22.setVisibility(View.INVISIBLE);
                r23.setVisibility(View.INVISIBLE);
                r24.setVisibility(View.INVISIBLE);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.INVISIBLE);
                t2.setVisibility(View.VISIBLE);
                plus.setVisibility(View.INVISIBLE);
            }
        });

        plusb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus.setVisibility(View.INVISIBLE);
                r13.setVisibility(View.VISIBLE);
                r14.setVisibility(View.VISIBLE);
                r15.setVisibility(View.VISIBLE);
                r16.setVisibility(View.VISIBLE);
                r17.setVisibility(View.VISIBLE);
                r18.setVisibility(View.VISIBLE);
                r19.setVisibility(View.VISIBLE);
                r20.setVisibility(View.VISIBLE);
                r21.setVisibility(View.VISIBLE);
                r22.setVisibility(View.VISIBLE);
                r23.setVisibility(View.VISIBLE);
                r24.setVisibility(View.VISIBLE);
            }
        });

    }


    public void picture(String icon) {
        if (icon.equals("clear-day")) {
            k.setImageResource(R.drawable.clear);
        }
        else if (icon.equals("clear-night")) {
            k.setImageResource(R.drawable.clear_night);
        }
        else if (icon.equals("rain")) {
            k.setImageResource(R.drawable.rain);
        }
        else if (icon.equals("snow")) {
            k.setImageResource(R.drawable.snow);
        }
        else if (icon.equals("sleet")) {
            k.setImageResource(R.drawable.sleet);
        }
        else if (icon.equals("wind")) {
            k.setImageResource(R.drawable.wind);
        }
        else if (icon.equals("fog")) {
            k.setImageResource(R.drawable.fog);
        }
        else if (icon.equals("cloudy")) {
            k.setImageResource(R.drawable.cloudy);
        }
        else if (icon.equals("partly-cloudy-day")) {
            k.setImageResource(R.drawable.cloud_day);
        }
        else {
            k.setImageResource(R.drawable.cloud_night);
        }
    }
}
