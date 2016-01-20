package org.itstep.pastukhov.dz_23;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.itstep.pastukhov.dz_23.Interface.RestInterface;
import org.itstep.pastukhov.dz_23.Model.WeatherModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WeatherResult extends AppCompatActivity {
    TextView textView, cloud, degrees, wind, humidity, pressure, sunrise, sunset;
    ImageView weather;
    String picassoURL;
    Context context;
    String appid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_result);
        context = this;
        appid = "578270dced985a3a2b7149effa1ef63a";

        textView = (TextView)findViewById(R.id.textView);
        cloud = (TextView)findViewById(R.id.cloud);
        degrees = (TextView)findViewById(R.id.degrees);
        wind = (TextView)findViewById(R.id.wind);
        humidity = (TextView)findViewById(R.id.humidity);
        pressure = (TextView)findViewById(R.id.pressure);
        sunrise = (TextView)findViewById(R.id.sunrise);
        sunset = (TextView)findViewById(R.id.sunset);
        weather = (ImageView)findViewById(R.id.weather);
        picassoURL = "http://openweathermap.org/img/w/";

        String url = "http://api.openweathermap.org/data/2.5";

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String city = intent.getStringExtra("city");

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);

        restInterface.getWeather(id, "ru", appid, new Callback<WeatherModel>() {
            @Override
            public void success(WeatherModel weatherModel, Response response) {
                Double temp = weatherModel.getMain().getTemp() - 273.15;
                textView.setText(city);
                Picasso.with(context).load(picassoURL+weatherModel.getWeather().get(0).getIcon() + ".png").
                        into(weather);
                cloud.setText(weatherModel.getWeather().get(0).getDescription());
                degrees.setText(temp.intValue() + " °C");
                wind.setText("Ветер: " + windDirection(weatherModel.getWind().getDeg()) +
                        weatherModel.getWind().getSpeed().intValue() + " м/с");
                humidity.setText("Влажность: " + weatherModel.getMain().getHumidity() + "%");
                pressure.setText("Давление: " + converterHpaToMmrh(weatherModel.getMain().getPressure())
                        + " мм");
                sunrise.setText("Восход солнца: " + convertUnixToNormal(weatherModel.getSys().getSunrise()));
                sunset.setText("Закат солнца: " + convertUnixToNormal(weatherModel.getSys().getSunset()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("tag", error.getMessage());
            }
        });

    }

    public int converterHpaToMmrh (Double hpa) {
        Double mmrh = hpa * 0.75006375541921;
        return mmrh.intValue();
    }

    public String convertUnixToNormal (long unix) {
        String date = new SimpleDateFormat("HH:mm").format(new Date(unix * 1000));
        return date;
    }

    public String windDirection (Double degrees) {
        if (degrees >= 348.75 || degrees <= 11.25){
            return "Северный ";
        }
        if (degrees >= 11.26 && degrees <= 33.75){
            return "Северо-северо-восточный ";
        }
        if (degrees >= 33.76 && degrees <= 56.25){
            return "Северо-восточный ";
        }
        if (degrees >=56.26 && degrees <= 78.75){
            return "Восточно-северо-восточный ";
        }
        if (degrees >= 78.76 && degrees <= 101.25){
            return "Восточный";
        }
        if (degrees >= 101.26 && degrees <= 123.75){
            return "Восточно-юго-восточный ";
        }
        if (degrees >= 123.76 && degrees <= 146.25){
            return "Юго-восточный ";
        }
        if (degrees >= 146.26 && degrees <= 168.75){
            return "Юго-юго-восточный ";
        }
        if (degrees >= 168.76 && degrees <= 191.25){
            return "Южный ";
        }
        if (degrees >= 191.26 && degrees <= 213.75){
            return "Юго-юго-западный ";
        }
        if (degrees >= 213.76 && degrees <= 236.25){
            return "Юго-западный ";
        }
        if (degrees >= 236.26 && degrees <= 258.75){
            return "Западно-юго-западный ";
        }
        if (degrees >= 258.76 && degrees <= 281.25){
            return "Западный ";
        }
        if (degrees >= 281.26 && degrees <= 303.75){
            return "Западно-северо-западный ";
        }
        if (degrees >= 303.76 && degrees <= 326.25){
            return "Северо-западный ";
        }
        if (degrees >= 326.26 && degrees <= 348.74){
            return "Северо-северо-западный ";
        }
        return "";
    }
}
