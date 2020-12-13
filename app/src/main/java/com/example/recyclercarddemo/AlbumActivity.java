package com.example.recyclercarddemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.recyclercarddemo.adapter.AlbumAdapter;
import com.example.recyclercarddemo.model.Album;
import com.example.recyclercarddemo.retrofit.ApiClient;
import com.example.recyclercarddemo.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    AlbumAdapter adapter;
    private static ArrayList<Album> albumList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutManager = new LinearLayoutManager(this);
        final Context context = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        //  Get Album List from REST Endpoint
        Call<List<Album>> call = apiInterface.doGetListAlbums();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                albumList = (ArrayList<Album>) response.body();
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(layoutManager);

                adapter = new AlbumAdapter(context, albumList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

    }
}
