package com.example.shaolun.homework9;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    static String json ="";
    private EditText streetEditText;
    private EditText cityEditText;
    private RadioButton rb_f;
    private TextView alert;
    private String addr;
    private String street;
    private String city;
    private String stateValue;
    private String unit;
    public String jsontext;
    private String[] state = {"Select","AL","AK","AZ","AR","CA","CO","CT","DE","DC","FL","GA",
            "HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT",
            "NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX",
            "UT","VT","VA","WA","WV","WI","WY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ArrayAdapter<String> stateList;
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //change the color and remove the underline of
        TextView link = (TextView) findViewById(R.id.link);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        stripUnderlines(link);

        //spinner
        spinner = (Spinner)findViewById(R.id.mySpinner);

        stateList = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state);
        spinner.setAdapter(stateList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                Toast.makeText(mContext, "You selected " + state[position], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        streetEditText = (EditText) findViewById(R.id.text_street);
        cityEditText = (EditText) findViewById(R.id.text_city);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_f = (RadioButton) radioGroup.findViewById(R.id.radioButton);
        alert = (TextView) findViewById(R.id.alertText);




//        Button btn_search = (Button) findViewById(R.id.search);
//
//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (streetEditText.getText().toString().trim().length() == 0){
//                    alert.setText("Please enter a Street Address.");
//                    return;
//                }
//                if (cityEditText.getText().toString().trim().length() == 0){
//                    alert.setText("Please enter a City.");
//                    return;
//                }
//                if (spinner.getSelectedItemPosition()==0){
//                    alert.setText("Please enter a State.");
//                    return;
//                }
//                street = streetEditText.getText().toString();
//                city = cityEditText.getText().toString();
//                stateValue = spinner.getSelectedItem().toString();
//                if (rb_f.isChecked()) {
//                    unit = "us";
//                }
//                else unit = "si";
//                alert.setText(street + " " + city + " " + stateValue + " " + unit);
//            }
//        });

//        Button btn_clear = (Button) findViewById(R.id.clear);
//        btn_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cityEditText.setText("");
//                streetEditText.setText("");
//                rb_f.setChecked(true);
//                spinner.setSelection(0);
//                alert.setText("");
//            }
//        });

//        Button btn_about = (Button)findViewById(R.id.about);
//        btn_about.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AboutActivity.class));
//            }
//        });




    }

    //remove underline of hyperlink
    private void stripUnderlines(TextView textView) {
        Spannable s = (Spannable)textView.getText();
        URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
        for (URLSpan span: spans) {
            int start = s.getSpanStart(span);
            int end = s.getSpanEnd(span);
            s.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            s.setSpan(span, start, end, 0);
        }
        textView.setText(s);
    }
    //remove underline of hyperlink
    private class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }
        @Override public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

    /** Called when the user clicks the ABOUT button */
    public void aboutMe(View view) {
        // start AboutActivity
        startActivity(new Intent(MainActivity.this, AboutActivity.class));
    }

    /** Called when the user clicks the CLEAR button */
    public void clear(View view) {
        cityEditText.setText("");
        streetEditText.setText("");
        rb_f.setChecked(true);
        spinner.setSelection(0);
        alert.setText("");
    }

    /** Called when the user clicks the SEARCH button */
    public void search(View view) {

        if (streetEditText.getText().toString().trim().length() == 0){
            alert.setText("Please enter a Street Address.");
            return;
        }
        if (cityEditText.getText().toString().trim().length() == 0){
            alert.setText("Please enter a City.");
            return;
        }
        if (spinner.getSelectedItemPosition()==0){
            alert.setText("Please enter a State.");
            return;
        }
        street = streetEditText.getText().toString().trim().replace(" ","%20");
        city = cityEditText.getText().toString().trim().replace(" ", "%20");
        stateValue = spinner.getSelectedItem().toString();
        if (rb_f.isChecked()) {
            unit = "us";
        } else unit = "si";

//        test code
//        alert.setText(street + " " + city + " " + stateValue + " " + unit);
//        http://shaolunchen-env.elasticbeanstalk.com/hw8/hw8.php?addr=1201+w36st&city=Los+Angeles&state=CA&temp=us

        //set url
        addr = "http://shaolunchen-env.elasticbeanstalk.com/hw8/hw8.php?addr=%27" + street + "%27&city=%27" + city + "&state=%27" + stateValue + "%27&temp=" +unit;
        new getData().execute();

    }


    public class getData extends AsyncTask<String, String, String> {

        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            try {
                URL url = new URL(addr);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                in.close();
                json = sb.toString();

            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            // return JSON String
            return json;
        }

        @Override
        protected void onPostExecute(String json) {

            jsontext = json;
            //Set JSON Data in TextView
            Intent intent = new Intent(getBaseContext(), ResultActivity.class);
            intent.putExtra("JSON", jsontext);
            intent.putExtra("TEMP", unit);
            intent.putExtra("STATE", stateValue);
            intent.putExtra("CITY", city);
            startActivity(intent);
        }
    }
}
