package org.itstep.pastukhov.dz_23.Interface;

import org.itstep.pastukhov.dz_23.Model.WeatherModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lotrik on 12.11.15.
 */
public interface RestInterface {

    @GET("/weather")
    void getWeather (@Query("id") String id, @Query("lang") String lang, @Query("appid") String appid,
                     Callback<WeatherModel> callback);
}
