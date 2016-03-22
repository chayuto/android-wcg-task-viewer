package me.chayut.wcgtaskviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chayut on 23/03/16.
 */

 public class resultAdapter extends ArrayAdapter<WCGResult> {

    Context mContext;
    private List<WCGResult> mList;

    public resultAdapter(Context context, int textViewResourceId,
                         List<WCGResult> objects) {
        super(context, textViewResourceId, objects);

        this.mContext = context;
        this.mList = objects;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_result, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.textViewName);
        textView.setText(mList.get(position).getName());

        return rowView;
    }

}
