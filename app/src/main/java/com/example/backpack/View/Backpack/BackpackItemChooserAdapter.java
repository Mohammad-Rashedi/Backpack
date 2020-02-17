package com.example.backpack.View.Backpack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.backpack.R;
import com.example.backpack.Room.BackpackTable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BackpackItemChooserAdapter extends RecyclerView.Adapter<BackpackItemChooserAdapter.BPChooserHolder> {
    List<Item> items = new ArrayList<>();
    private onItemClickListener listener;

    @NonNull
    @Override
    public BPChooserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chooser_item, parent , false);
        return new BackpackItemChooserAdapter.BPChooserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BPChooserHolder holder, int position) {
        Item currentItem = items.get(position);
        holder.textViewBPName.setText(currentItem.getItemName());
        holder.imageViewItem.setImageResource(currentItem.getItemImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class BPChooserHolder extends RecyclerView.ViewHolder{
        private TextView textViewBPName;
        private TextView textViewItemCount;
        private ImageView imageViewItem;
        private ImageButton imageButtonPlus;
        private ImageButton imageButtonMinus;
        public BPChooserHolder(@NonNull View itemView) {
            super(itemView);
            textViewBPName = itemView.findViewById(R.id.item_chooser_item_name);
            imageViewItem = itemView.findViewById(R.id.item_chooser_item_image);
            imageButtonMinus = itemView.findViewById(R.id.imageButtonMinus);
            imageButtonPlus = itemView.findViewById(R.id.imageButtonPlus);
            textViewItemCount =  itemView.findViewById(R.id.TextViewItemCount);
            imageButtonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Item item = items.get(position);
                    if (item.getItemCount()>0){
                    item.setItemCount(item.getItemCount() - 1);
                        textViewItemCount.setText(item.getItemCount()+"");
                    }
                    Log.i("==", "onClick: Minus " + item.getItemCount());
                }
            });
            imageButtonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Item item = items.get(position);
                    item.setItemCount(item.getItemCount() + 1);
                    Log.i("==", "onClick: Plus " + item.getItemCount());
                    textViewItemCount.setText(item.getItemCount()+"");

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

    public BackpackItemChooserAdapter(List<Item> items, Context context) {
        this.items = items;

    }

}
