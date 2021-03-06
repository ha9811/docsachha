package com.example.appdocsach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdocsach.BookhotAdapter;
import com.example.appdocsach.Chitietsach;
import com.example.appdocsach.MainActivity;
import com.example.appdocsach.Phan;
import com.example.appdocsach.R;
import com.example.appdocsach.Sach;
import com.example.appdocsach.SachAdapter;
import com.example.appdocsach.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentVanHoc extends ListFragment {
    ArrayList<Sach> mangsachvanhoc;
    SachAdapter sachvanhocadapter;
    ArrayList<Phan> mangphan;
    int ID = 0;
    String bookname = "";
    String imgbook = "";
    String author = "";
    int idsach = 0;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vanhoc,container,false);
        mangphan = new ArrayList<>();
        mangsachvanhoc = new ArrayList<>();
        sachvanhocadapter = new SachAdapter(getContext(),mangsachvanhoc);
        setListAdapter(sachvanhocadapter);
        Getdatasachvanhoc();
        return view;
    }

//    @Override
//    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
//        Intent intent = new Intent(getActivity(), Chitietsach.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("id_key",mangsachvanhoc.get(position).getId());
//        bundle.putString("bookname_key",mangsachvanhoc.get(position).getBookName());
//        bundle.putString("author_key", mangsachvanhoc.get(position).getAuthor());
//        bundle.putString("imgbook_key",mangsachvanhoc.get(position).getImgHinh());
//        intent.putExtra("bundle", bundle);
//        startActivity(intent);
//        super.onListItemClick(l, v, position, id);
//    }

    private void Getdatasachvanhoc() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.Duongdansachvanhoc, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    ID = jsonObject.getInt("id");
                                    bookname = jsonObject.getString("tensach");
                                    imgbook = jsonObject.getString("hinhanhsach");
                                    author = jsonObject.getString("tacgiasach");
                                    idsach = jsonObject.getInt("idsach");
                                    mangsachvanhoc.add(new Sach(ID,bookname,imgbook,author,idsach));
                                    sachvanhocadapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("BBB", "Lỗi \n"+error.toString());

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}
