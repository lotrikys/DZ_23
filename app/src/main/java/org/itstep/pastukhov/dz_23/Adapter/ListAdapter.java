package org.itstep.pastukhov.dz_23.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.itstep.pastukhov.dz_23.Model.BaseAdapterModel;
import org.itstep.pastukhov.dz_23.R;

import java.util.ArrayList;

/**
 * Created by lotrik on 12.11.15.
 */
public class ListAdapter extends BaseAdapter {
    TextView cityname;
    ImageView weathericon;
    Context context;
    LayoutInflater inflater;
    ArrayList<BaseAdapterModel> city;
    String url = "http://openweathermap.org/themes/demo/assets/vendor/owm/images/OWM_logo32_32.png";

    public ListAdapter (Context context, ArrayList<BaseAdapterModel> city) {
        this.context = context;
        this.city = city;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return city.size();
    }

    @Override
    public Object getItem(int i) {
        return city.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if (view == null){
           view = inflater.inflate(R.layout.city_list, viewGroup, false);
        }

        BaseAdapterModel c = getWeatherModel(i);

        weathericon = (ImageView)view.findViewById(R.id.weathericon);
        Picasso.with(context).load(url).into(weathericon);
        cityname = (TextView) view.findViewById(R.id.cityname);
        cityname.setText(c.name);

        return view;
    }

    BaseAdapterModel getWeatherModel(int i){
        return ((BaseAdapterModel)getItem(i));
    }
}
