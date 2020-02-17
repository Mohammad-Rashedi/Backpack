package com.example.backpack.View.Weather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.backpack.R;
import com.example.backpack.View.Backpack.BackpackItemListAdapter;
import com.example.backpack.View.Backpack.Item;
import com.example.backpack.WebService.WeatherGsonClasses.Weather;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


    public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherListHolder> {
        private final Context context;
        List<WeatherItem> items = new ArrayList<>();
        private WeatherAdapter.onItemClickListener listener;

        @NonNull
        @Override
        public WeatherListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_weather, parent , false);
            return new WeatherAdapter.WeatherListHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull WeatherAdapter.WeatherListHolder holder, int position) {
            WeatherItem currentItem = items.get(position);
            int cityTemperatureInteger = Integer.parseInt(currentItem.getCityTemperature());
            String cityWeatherCondition = currentItem.getCityWeatherCondition();
            holder.textViewItemName.setText(currentItem.getCityName());
            holder.textViewItemTemperature.setText(currentItem.getCityTemperature() + "°");
            if (cityTemperatureInteger>15){
                holder.imageViewItemTemperature.setImageResource(R.drawable.thermometerhot);
            }else if (cityTemperatureInteger>5){
                holder.imageViewItemTemperature.setImageResource(R.drawable.thermometercold);
            }

                switch (cityWeatherCondition){
                    case "light rain":
                        holder.imageViewItemWeatherCondition.setImageResource(R.drawable.conditionlightrain);
                        break;
                    case "fog":
                        holder.imageViewItemWeatherCondition.setImageResource(R.drawable.conditionfog);

                        break;
                    case "few clouds":
                        holder.imageViewItemWeatherCondition.setImageResource(R.drawable.conditionbrokenclouds);

                        break;
                    case "scattered clouds":
                        holder.imageViewItemWeatherCondition.setImageResource(R.drawable.conditionbrokenclouds);

                        break;
                    case "clear sky":
                        holder.imageViewItemWeatherCondition.setImageResource(R.drawable.conditionclearsky);

                        break;
                    case "light intensity shower rain":
                        holder.imageViewItemWeatherCondition.setImageResource(R.drawable.conditionlightintensityshowerrain);

                        break;
                    case "thunderstorm with rain":
                        holder.imageViewItemWeatherCondition.setImageResource(R.drawable.conditionthunderstormwithrain);

                        break;
                }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class WeatherListHolder extends RecyclerView.ViewHolder{
            private TextView textViewItemName;
            private TextView textViewItemTemperature;
            private ImageView imageViewItemTemperature;
            private ImageView imageViewItemWeatherCondition;
            private CardView cardView;

            public WeatherListHolder(@NonNull View itemView) {
                super(itemView);
                textViewItemName = itemView.findViewById(R.id.textViewItemWeatherName);
                textViewItemTemperature =  itemView.findViewById(R.id.textViewItemWeatherTemperature);
                imageViewItemTemperature =  itemView.findViewById(R.id.ImageViewItemWeatherTemperature);
                imageViewItemWeatherCondition = itemView.findViewById(R.id.ImageViewItemWeatherCondition);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (listener!= null && position!= RecyclerView.NO_POSITION) {
                            listener.onItemClick(items.get(position));
                        }
                    }
                });

            }
        }

        public WeatherItem getItemAt(int position){

            return items.get(position);
        }

        public interface onItemClickListener{
            void onItemClick(WeatherItem item);
        }
        public void setOnItemClickListener(WeatherAdapter.onItemClickListener listener){
            this.listener = listener;
        }

        public WeatherAdapter(List<WeatherItem> items, Context context) {
            this.context = context;
            this.items = items;

        }

    }

