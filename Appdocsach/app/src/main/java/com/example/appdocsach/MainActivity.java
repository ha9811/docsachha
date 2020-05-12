package com.example.appdocsach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdocsach.fragment.FragmentVanHoc;
import com.example.appdocsach.fragment.FragmentCongnghe;
import com.example.appdocsach.fragment.FragmentKhoaHoc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Animation in, out;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    ImageButton imgbtntrangchu;
    ArrayList<Sach> mangsachhot;
    BookhotAdapter bookhotAdapter;
    int ID = 0;
    String bookname = "";
    String imgbook = "";
    String author = "";
    int idsach = 0;
    FragmentManager fragmentManager =getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        Flipper();
        Getdatasbookhot();
        Eventtrangchu();
    }
    private void Eventtrangchu() {
        imgbtntrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentVanHoc fragmentVanHoc = (FragmentVanHoc) getSupportFragmentManager().findFragmentByTag("Fragvanhoc");
                FragmentKhoaHoc fragmentKhoaHoc = (FragmentKhoaHoc) getSupportFragmentManager().findFragmentByTag("Fragkhoahoc");
                FragmentCongnghe fragmentCongnghe = (FragmentCongnghe) getSupportFragmentManager().findFragmentByTag("Fragcongnghe");
                if(fragmentVanHoc != null){
                    fragmentTransaction.remove(fragmentVanHoc);
                    fragmentTransaction.commit();
                }
                if(fragmentKhoaHoc !=null){
                    fragmentTransaction.remove(fragmentKhoaHoc);
                    fragmentTransaction.commit();
                }
                if(fragmentCongnghe !=null){
                    fragmentTransaction.remove(fragmentCongnghe);
                    fragmentTransaction.commit();
                }

            }
        });
    }

    private void Getdatasbookhot() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.Duongdansachmoi, null,
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
                            mangsachhot.add(new Sach(ID,bookname,imgbook,author,idsach));
                            bookhotAdapter.notifyDataSetChanged();
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
                Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d("AAA", "Lỗi \n"+error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void Anhxa() {
        viewFlipper = findViewById(R.id.viewFlipper);
        imgbtntrangchu = findViewById(R.id.imgbtntrangchu);
        recyclerView = findViewById(R.id.recyclersachhot);
        mangsachhot = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        bookhotAdapter = new BookhotAdapter(mangsachhot, getApplicationContext());
        recyclerView.setAdapter(bookhotAdapter);
    }

    private void Flipper() {
        in = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
    }

    public void ClickFragmentvanhoc(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.line11,new FragmentVanHoc(),"Fragvanhoc");
        fragmentTransaction.commit();
    }

    public void ClickFragmentkhoahoc(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.line11,new FragmentKhoaHoc(),"Fragkhoahoc");
        fragmentTransaction.commit();
    }

    public void ClickFragmentcongnghe(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.line11,new FragmentCongnghe(),"Fragcongnghe");
        fragmentTransaction.commit();
    }
}
