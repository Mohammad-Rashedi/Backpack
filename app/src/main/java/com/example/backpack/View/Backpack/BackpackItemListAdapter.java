package com.example.backpack.View.Backpack;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backpack.R;

import java.util.ArrayList;
import java.util.List;

public class BackpackItemListAdapter extends RecyclerView.Adapter<BackpackItemListAdapter.BPItemListHolder> {
    private final Context context;
    List<Item> items = new ArrayList<>();
    private onItemClickListener listener;


    @NonNull
    @Override
    public BPItemListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_item, parent , false);
        return new BackpackItemListAdapter.BPItemListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BPItemListHolder holder, int position) {
        Item currentItem = items.get(position);
        holder.textViewItemName.setText(currentItem.getItemName());
        holder.textViewItemCount.setText("" + currentItem.getItemCount());
        holder.imageViewItemImage.setImageResource(currentItem.getItemImage());
        if (currentItem.getCondition()){
            holder.CheckBoxItemCondition.setChecked(true);

            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context
                    , R.color.colorBackgroundItemBPItemViewChecked));
        }

        holder.CheckBoxItemCondition.setActivated(currentItem.getCondition());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class BPItemListHolder extends RecyclerView.ViewHolder{
        private TextView textViewItemName;
        private TextView textViewItemCount;
        private CheckBox CheckBoxItemCondition;
        private ImageView imageViewItemImage;
        private CardView cardView;

        public BPItemListHolder(@NonNull View itemView) {
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.item_chooser_item_name_ItemView);
            textViewItemCount =  itemView.findViewById(R.id.TextViewItemCountItemView);
            imageViewItemImage =  itemView.findViewById(R.id.item_chooser_item_image_itemVIew);
            CheckBoxItemCondition = itemView.findViewById(R.id.radioButtonItemList);
            cardView = itemView.findViewById(R.id.cardViewItemViewItem);
            CheckBoxItemCondition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Item item = items.get(position);

                    item.setCondition(!(item.getCondition()));
                    if (item.getCondition()){
                        cardView.setCardBackgroundColor(ContextCompat.getColor(context
                                , R.color.colorBackgroundItemBPItemViewChecked));
                    }else {
                        cardView.setCardBackgroundColor(ContextCompat.getColor(context
                                , R.color.colorBackgroundItemBPItemViewUnChecked));
                    }
                }
            });

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

    public Item getItemAt(int position){

        return items.get(position);
    }

    public interface onItemClickListener{
        void onItemClick(Item item);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    public BackpackItemListAdapter(List<Item> items, Context context) {
        this.context = context;
        this.items = items;

    }

}

