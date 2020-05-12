package com.example.appdocsach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Sach> mangSach;

    public SachAdapter(Context context, ArrayList<Sach> mangSach) {
        this.context = context;
        this.mangSach = mangSach;
    }

    @Override
    public int getCount() {
        return mangSach.size();
    }

    @Override
    public Object getItem(int position) {
        return mangSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txtnameBook;
        ImageView imgHinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_sachmoi, null);
            viewHolder.imgHinh = convertView.findViewById(R.id.imgsach);
            viewHolder.txtnameBook = convertView.findViewById(R.id.txtsach);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
            }
        Sach sach = (Sach) getItem(position);
        viewHolder.txtnameBook.setText(sach.getBookName());
        Picasso.with(context).load(sach.getImgHinh())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error_image)
                .into(viewHolder.imgHinh);
        return convertView;
    }
}