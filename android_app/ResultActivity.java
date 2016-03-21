package com.example.shaolun.homework9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity {

    private String speed = "";
    private String degree = "";
    private String length = "";
    private ImageView k;
    private String state;
    private String msg;
    private String msg2;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        k = (ImageView) findViewById(R.id.imageView3);
        Intent intent = getIntent();
        //json string
        msg = intent.getStringExtra("JSON");
        //unit string
        msg2 = intent.getStringExtra("TEMP");
        //get state
        state = intent.getStringExtra("STATE");
        //
        city = intent.getStringExtra("CITY");
        if (msg2.equals("us")) {
//            test.setText(msg2 + (char) 0x00B0);
            speed = " mph";
            degree = (char) 0x00B0 + "F";
            length = " mi";
        }
        else {
//            test.setText("qq"+msg2);
            speed = " m/s";
            degree = (char) 0x00B0 + "C";
            length = " km";
        }
        try {
        JSONObject json = new JSONObject(msg);
        //summary
        String qq = json.getJSONObject("currently").getString("summary");
            TextView tt = (TextView) findViewById(R.id.summ);
            tt.setText(qq + ", " + state);
        //temp
        String temp = json.getJSONObject("currently").getString("temperature");
        double num = Double.parseDouble(temp);
        int i = (int)Math.round(num);
        temp = Integer.toString(i);
        TextView temp1 = (TextView) findViewById(R.id.textView5);
        temp1.setText(temp);
        //temp max and min
        String tempMax = json.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("temperatureMax");
        String tempMin = json.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("temperatureMin");
        double num1 = Double.parseDouble(tempMin);
        int i1 = (int)Math.round(num1);
        tempMin = Integer.toString(i1);
        double num2 = Double.parseDouble(tempMax);
        int i2 = (int)Math.round(num2);
        tempMax = Integer.toString(i2);
        TextView tempm = (TextView) findViewById(R.id.textView6);
        tempm.setText("L:" + tempMin + (char) 0x00B0 + " | H:" + tempMax + (char) 0x00B0);
        //degree
        TextView deg = (TextView) findViewById(R.id.textView4);
        deg.setText(degree);
        //icon to picture
            qq = json.getJSONObject("currently").getString("icon");
            picture(qq);
        //precipitation
        temp = json.getJSONObject("currently").getString("precipIntensity");
        num = Double.parseDouble(temp);
        temp = precip(num);
        TextView precipI = (TextView) findViewById(R.id.textView201);
        precipI.setText(temp);
        //chance of rain
        temp = json.getJSONObject("currently").getString("precipProbability");
        num = Double.parseDouble(temp);
        i = (int)Math.round(num*100);
        temp = Integer.toString(i);
            TextView precipP = (TextView) findViewById(R.id.textView202);
            precipP.setText(temp + " %");
        //wind speed
        temp = json.getJSONObject("currently").getString("windSpeed");
            TextView windspeed = (TextView) findViewById(R.id.textView203);
            windspeed.setText(temp + speed);
        //dewpoint
        temp = json.getJSONObject("currently").getString("dewPoint");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num);
            temp = Integer.toString(i);
            TextView dew = (TextView) findViewById(R.id.textView204);
            dew.setText(temp + degree);
        //humidity
        temp = json.getJSONObject("currently").getString("humidity");
            num = Double.parseDouble(temp);
            i = (int)Math.round(num*100);
            temp = Integer.toString(i);
            TextView humidity = (TextView) findViewById(R.id.textView205);
            humidity.setText(temp + " %");
        //visibility
        temp = json.getJSONObject("currently").getString("visibility");
            num = Double.parseDouble(temp);
            num = Math.round(num*100)/100;
            temp = Double.toString(num);
            TextView visibility = (TextView) findViewById(R.id.textView206);
            visibility.setText(temp + length);
        //sunrise
        String time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("sunriseTime");
        long unixSeconds = Long.parseLong(time);
        temp = json.getString("offset");
        int offset = Integer.parseInt(temp);
            Date date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            String formattedDate = sdf.format(date);
            TextView sunrise = (TextView) findViewById(R.id.textView207);
            sunrise.setText(formattedDate);
        //sunset
            time = json.getJSONObject("daily").getJSONArray("data").getJSONObject(0).getString("sunsetTime");
            unixSeconds = Long.parseLong(time);
            date = new Date((unixSeconds+8*3600 + offset*3600)*1000);
            formattedDate = sdf.format(date);
            TextView sunset = (TextView) findViewById(R.id.textView208);
            sunset.setText(formattedDate);


        }
        catch (Exception e){
            Log.e("Can not parse", "Error converting result " + e.toString());
        }
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

    public String precip(Double pre) {
        if (pre >= 0 && pre < 0.002) { return "None";}
        else if (pre >= 0.002 && pre < 0.017) { return "Very Light"; }
        else if (pre >= 0.017 && pre < 0.1) { return "Light"; }
        else if (pre >= 0.1 && pre < 0.4) { return "Moderate"; }
        else  { return "Heavy"; }
    }

    /** Called when the user clicks the ABOUT button */
    public void showDetail(View view) {
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
        intent.putExtra("JSON", msg);
        intent.putExtra("TEMP", msg2);
        intent.putExtra("STATE", state);
        intent.putExtra("CITY", city);
        startActivity(intent);
//        startActivity(new Intent(ResultActivity.this, DetailActivity.class));
    }


}
