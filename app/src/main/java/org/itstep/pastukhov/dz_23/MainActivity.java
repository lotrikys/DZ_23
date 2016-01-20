package org.itstep.pastukhov.dz_23;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.itstep.pastukhov.dz_23.Adapter.ListAdapter;
import org.itstep.pastukhov.dz_23.Model.BaseAdapterModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] cities = {"Днепропетровск", "Киев", "Харьков", "Запорожье", "Донецк", "Луганск",
            "Ровно", "Львов", "Сумы", "Тернополь", "Ужгород"};
    ArrayList<BaseAdapterModel> baseAdapterModels = new ArrayList<>();
    ListAdapter listAdapter;
    BaseAdapterModel baseAdapterModel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        fillCity();
        listAdapter = new ListAdapter(this, baseAdapterModels);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "709930");
                        intent.putExtra("city", "Днепропетровск");
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "703448");
                        intent.putExtra("city", "Киев");
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "706483");
                        intent.putExtra("city", "Харьков");
                        startActivity(intent);
                        break;
                    }
                    case 3:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "687700");
                        intent.putExtra("city", "Запорожье");
                        startActivity(intent);
                        break;
                    }
                    case 4:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "709717");
                        intent.putExtra("city", "Донецк");
                        startActivity(intent);
                        break;
                    }
                    case 5:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "702658");
                        intent.putExtra("city", "Луганск");
                        startActivity(intent);
                        break;
                    }
                    case 6:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "695594");
                        intent.putExtra("city", "Ровно");
                        startActivity(intent);
                        break;
                    }
                    case 7:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "702550");
                        intent.putExtra("city", "Львов");
                        startActivity(intent);
                        break;
                    }
                    case 8:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "692194");
                        intent.putExtra("city", "Сумы");
                        startActivity(intent);
                        break;
                    }
                    case 9:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "691650");
                        intent.putExtra("city", "Тернополь");
                        startActivity(intent);
                        break;
                    }
                    case 10:{
                        Intent intent = new Intent(context, WeatherResult.class);
                        intent.putExtra("id", "690548");
                        intent.putExtra("city", "Ужгород");
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    void fillCity() {
        for (int i = 0; i < cities.length; i++){
            baseAdapterModel = new BaseAdapterModel(cities[i]);
            baseAdapterModels.add(baseAdapterModel);
        }
    }
}
