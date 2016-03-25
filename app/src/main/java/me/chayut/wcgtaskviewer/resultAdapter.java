package me.chayut.wcgtaskviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        TextView tvName = (TextView) rowView.findViewById(R.id.textViewName);
        TextView textViewDeviceName = (TextView) rowView.findViewById(R.id.textViewDeviceName);
        TextView tvReceivedTime = (TextView) rowView.findViewById(R.id.tvReceivedTime);

        WCGResult mResult = mList.get(position);
        tvName.setText(mResult.getName());
        textViewDeviceName.setText("Device: " + mResult.getDeviceName());
        String receivedTime = mResult.getReceivedTime();
        if(receivedTime != null) {
            tvReceivedTime.setText("Received Time: " + receivedTime);
        }
        return rowView;
    }

}
