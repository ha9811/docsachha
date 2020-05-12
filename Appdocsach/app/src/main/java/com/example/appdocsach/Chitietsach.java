package com.example.appdocsach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Chitietsach extends AppCompatActivity {
    ImageView imgsach;
    TextView txtbookname,txtauthor;
    ListView lvchuong;
    ArrayList<Phan> mangphan;
    PhanAdapter phanAdapter;
    int ID = 0;
    String bookname = "";
    String imgbook = "";
    String author = "";
    int idSach = 0;

    int IDphan = 0;
    String tenphan = "";
    String text = "";
    int idsachphan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsach);
        Anhxa();
        GetInformationbookhot();
        //GetÌnormation();
        Getdata();
        Batsukienitemphan();


    }

    private void GetInformationbookhot() {
        Sach sach = (Sach) getIntent().getSerializableExtra("chitietsach_key");
        ID = sach.getId();
        bookname = sach.getBookName();
        author = sach.getAuthor();
        imgbook = sach.getImgHinh();
        idSach = sach.getIdsach();
        txtauthor.setText(author);
        txtbookname.setText(bookname);
        Picasso.with(getApplicationContext()).load(imgbook)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error_image)
                .into(imgsach);
    }

    private void Batsukienitemphan() {
        lvchuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(),Docsach.class);
                Bundle bundle = new Bundle();
                bundle.putString("tenphan_key",mangphan.get(position).getTenPhan());
                bundle.putString("text_key", mangphan.get(position).getText());
                intent1.putExtra("bundledocsach", bundle);
                startActivity(intent1);
            }
        });
    }
    private void Getdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.Duongdanphansach, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    IDphan = jsonObject.getInt("id");
                                    tenphan = jsonObject.getString("tenphan");
                                    text = jsonObject.getString("text");
                                    idsachphan = jsonObject.getInt("idsach");
                                    mangphan.add(new Phan(IDphan,tenphan,text,idsachphan));
                                    phanAdapter.notifyDataSetChanged();
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
                        Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi \n"+error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }

    private void GetÌnormation() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        ID = bundle.getInt("id_key");
        bookname = bundle.getString("bookname_key");
        author = bundle.getString("author_key");
        imgbook = bundle.getString("imgbook_key");
        idSach = bundle.getInt("idsach_key");
        txtauthor.setText(author);
        txtbookname.setText(bookname);
        Picasso.with(getApplicationContext()).load(imgbook).placeholder(R.drawable.no_image).error(R.drawable.error_image).into(imgsach);
        }

    private void Anhxa() {
        imgsach = findViewById(R.id.imgchitietsach);
        txtbookname = findViewById(R.id.txtchitietsach);
        txtauthor = findViewById(R.id.txtauthorchitietsach);
        lvchuong = findViewById(R.id.lvchuong);
        mangphan = new ArrayList<>();
        phanAdapter = new PhanAdapter(mangphan,getApplicationContext());
        lvchuong.setAdapter(phanAdapter);
    }
}
