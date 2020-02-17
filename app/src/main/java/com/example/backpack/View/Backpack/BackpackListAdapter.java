package com.example.backpack.View.Backpack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backpack.R;
import com.example.backpack.Room.BackpackTable;

import java.util.ArrayList;
import java.util.List;

public class BackpackListAdapter extends RecyclerView.Adapter<BackpackListAdapter.BPHolder> {
    List<BackpackTable> myBackpackList = new ArrayList<>();
    private onItemClickListener listener;

    @NonNull
    @Override
    public BPHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bp_list, parent, false);

        return new BPHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BPHolder holder, int position) {
        BackpackTable currentBP = myBackpackList.get(position);
        Log.i("==ttt", "onBindViewHolder: "+currentBP);
        holder.textViewBPName.setText(currentBP.getName());
    }

    @Override
    public int getItemCount() {

        return myBackpackList.size();
    }
    public void setBPs(List<BackpackTable> BPTables){
        myBackpackList = BPTables;
        notifyDataSetChanged();

    }

    class  BPHolder extends RecyclerView.ViewHolder{
        private TextView textViewBPName;
        //        private BackpackTable currentBP;
        public BPHolder(@NonNull View itemView) {
            super(itemView);
            textViewBPName = itemView.findViewById(R.id.bp_name_bp_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener!= null && position!= RecyclerView.NO_POSITION) {
                        listener.onItemClick(myBackpackList.get(position));
                    }
                }
            });
        }

    }

    public BackpackTable getBPAt(int position){

        return myBackpackList.get(position);
    }


    public interface onItemClickListener{
        void onItemClick(BackpackTable backpackTable);
    }
    public void setOnItemClickListener(onItemClickListener listener){

        this.listener = listener;
    }

    public BackpackListAdapter(List<BackpackTable> myBackpackList, Context context) {
        this.myBackpackList = myBackpackList;
        Log.i("==ttt", "BackpackListAdapter: "+myBackpackList);
    }



}
