package com.example.backpack.BackpackView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.backpack.R;
import com.example.backpack.myDatabse.BackpackTable;

import java.util.List;

public class BackpackListAdapter extends ArrayAdapter<String> {
    Context context = getContext();
    List<String> myBackpackList;

    public BackpackListAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context,0, objects);
        myBackpackList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.row_backpack_view, null);
        String[] separated = myBackpackList.get(position).split(":");
        String name = separated[0];
        String Count = separated[1];
        String condition = separated[2];
        if (name.charAt(0) == '[') {
            name = name.substring(1, name.length());
        }else if (name.charAt(name.length()-1)==']'){
            name = name.substring(0, name.length()-1);
        }
        if ( Count.charAt(0) == '[') {
            Count = Count.substring(1, Count.length());
        }else if ( Count.charAt(Count.length()-1)==']'){
            Count = Count.substring(0, Count.length()-1);

        }
        if ( condition.charAt(0) == '[') {
            condition = condition.substring(1, condition.length());
        }else if ( condition.charAt(condition.length()-1)==']'){
            condition = condition.substring(0, condition.length()-1);
        }

        TextView textViewName = rootView.findViewById(R.id.textViewName);
        TextView textViewNumber = rootView.findViewById(R.id.textViewNumber);
        TextView textViewCondition = rootView.findViewById(R.id.textViewCondition);
        textViewName.setText(name);
        textViewNumber.setText("  " + Count + "  "  );
        if(condition.equals("true")){
            textViewCondition.setText("  " + "دارم" + "  ");
            textViewName.setBackgroundColor(Color.GREEN);
            textViewNumber.setBackgroundColor(Color.GREEN);
            textViewCondition.setBackgroundColor(Color.GREEN);
        }else{            textViewCondition.setText("  " + "ندارم" + "  ");
        }
        return rootView;
    }
}
