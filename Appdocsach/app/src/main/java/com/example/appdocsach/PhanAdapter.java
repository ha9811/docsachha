package com.example.appdocsach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PhanAdapter extends BaseAdapter {
    ArrayList<Phan> mangphan;
    Context context;

    public PhanAdapter(ArrayList<Phan> mangphan, Context context) {
        this.mangphan = mangphan;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mangphan.size();
    }

    @Override
    public Object getItem(int position) {
        return mangphan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txttenphan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_phan,null);
            viewHolder.txttenphan = convertView.findViewById(R.id.txtdongphan);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Phan phan = (Phan) getItem(position);
        viewHolder.txttenphan.setText(phan.getTenPhan());
        return convertView;
    }
}
