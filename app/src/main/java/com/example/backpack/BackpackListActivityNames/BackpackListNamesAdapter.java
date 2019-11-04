package com.example.backpack.BackpackListActivityNames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.backpack.R;
import com.example.backpack.myDatabse.BackpackTable;

import java.util.List;

public class BackpackListNamesAdapter extends ArrayAdapter<BackpackTable> {
    Context context = getContext();
    List<BackpackTable> myBackpackList;

    public BackpackListNamesAdapter(@NonNull Context context, @NonNull List<BackpackTable> objects) {
        super(context,0, objects);
        myBackpackList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.row_backpack_list_name_view, null);
        BackpackTable backpackTable = myBackpackList.get(position);
        String name = backpackTable.getName();
        TextView textViewName = rootView.findViewById(R.id.textViewName);
        textViewName.setText(name);
        return rootView;

    }

}
