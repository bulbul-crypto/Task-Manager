package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class HTP_Page_one extends AppCompatActivity {

    static final String OPEN_WEATHER_MAP_URL = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric";
    static final String OPEN_WEATHER_MAP_API = "f79ce44a48cad45208e66f285a9aad14";

    TextView cityField, detailsField, currentTemperatureField, humidityField, pressureField, weatherIcon, updatedField;
    Typeface weatherFont;
    static String latitude;
    static String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htp__page_one);

        Toolbar ab_tsp_three = findViewById(R.id.ab_htp_one);
        setSupportActionBar(ab_tsp_three);
        getSupportActionBar().setTitle("Weather Information");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        requestPermissions();

        FusedLocationProviderClient mFusedLocationProviderClient;
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(HTP_Page_one.this,ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            return;
        }
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(HTP_Page_one.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null){

                    latitude=String.valueOf(location.getLatitude());
                    longitude=String.valueOf(location.getLongitude());

                    weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

                    cityField = findViewById(R.id.city_field);
                    currentTemperatureField = findViewById(R.id.current_temperature_field);
                    updatedField = findViewById(R.id.updated_field);
                    detailsField = findViewById(R.id.details_field);
                    humidityField = findViewById(R.id.humidity_field);
                    pressureField = findViewById(R.id.pressure_field);
                    weatherIcon = findViewById(R.id.weather_icon);
                    weatherIcon.setTypeface(weatherFont);

                    String[] jsondata = getJSONResponse();

                    //-------------------->>>>>> Start below...

                    cityField.setText(jsondata[0]);
                    detailsField.setText(jsondata[1]);
                    currentTemperatureField.setText(jsondata[2]);
                    humidityField.setText("Humidity: "+jsondata[3]);
                    pressureField.setText("Pressure: "+jsondata[4]);
                    updatedField.setText(jsondata[5]);
                    weatherIcon.setText(Html.fromHtml(jsondata[6]));
                }
            }
        });

    }

    public String[] getJSONResponse(){

        String[] jsondata = new String[7];
        JSONObject jsonWeather = null;
        try {

            jsonWeather = getWeatherJSON(latitude,longitude);

        } catch (Exception e){

            Log.d("Error","Can't process JSON results",e);

        }

        try {

            if(jsonWeather != null){

                JSONObject details = jsonWeather.getJSONArray("weather").getJSONObject(0);
                JSONObject main = jsonWeather.getJSONObject("main");
                DateFormat df = DateFormat.getDateInstance();

                String city = jsonWeather.getString("name")+", "+jsonWeather.getJSONObject("sys").getString("country");
                String description = details.getString("description").toLowerCase(Locale.US);
                String temperature = String.format("%.0f",main.getDouble("temp"))+"'";
                String humidity = main.getString("humidity")+"%";
                String pressure = main.getString("pressure")+" hPa";
                String upDatedOn = df.format(new Date(jsonWeather.getLong("dt")*1000));
                String iconText = setWeatherIcon(details.getInt("id"),jsonWeather.getJSONObject("sys").getLong("sunrise")*1000,
                        jsonWeather.getJSONObject("sys").getLong("sunset")*1000);

                jsondata[0] = city;
                jsondata[1] = description;
                jsondata[2] = temperature;
                jsondata[3] = humidity;
                jsondata[4] = pressure;
                jsondata[5] = upDatedOn;
                jsondata[6] = iconText;

            }

        }catch (Exception e){


        }

        return jsondata;
    }

    public static String setWeatherIcon(int actualID,long sunrise,long sunset){

        int id = actualID/100;
        String icon = "";
        if(actualID == 800){

            long currentTime = new Date().getTime();
            if((currentTime >= sunrise) && (currentTime < sunset)){

                icon = "&#xf00d";
            }
            else{

                icon = "&#xf02e";
            }
        }
        else{

            switch (id){

                case 2:
                    icon = "&#xf01e";
                    break;
                case 3:
                    icon = "&#xf01c";
                    break;
                case 7:
                    icon = "&#xf014";
                    break;
                case 8:
                    icon = "&#xf013";
                    break;
                case 6:
                    icon = "&#xf01b";
                    break;
                case 5:
                    icon = "&#xf019";
                    break;
            }
        }
        return  icon;
    }

    public static JSONObject getWeatherJSON(String lat, String lon){

        try{

            URL url = new URL(String.format(OPEN_WEATHER_MAP_URL,lat,lon));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key",OPEN_WEATHER_MAP_API);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while((tmp = reader.readLine()) != null){

                json.append(tmp).append("\n");
            }
            reader.close();
            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200){

                return null;
            }
            return data;
        }
        catch (Exception e){

            return null;
        }
    }

    public void requestPermissions(){

        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
}
