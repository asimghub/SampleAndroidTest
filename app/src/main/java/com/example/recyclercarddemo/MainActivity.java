package com.example.recyclercarddemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.recyclercarddemo.adapter.UserAdapter;
import com.example.recyclercarddemo.model.User;
import com.example.recyclercarddemo.retrofit.ApiClient;
import com.example.recyclercarddemo.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    private static ArrayList<User> userList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutManager = new LinearLayoutManager(this);
        final Context context = this;

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        //  Get User List from REST Endpoint
        Call<List<User>> call = apiInterface.doGetListUsers();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                userList = (ArrayList<User>) response.body();
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(layoutManager);

                userAdapter = new UserAdapter(context, userList);
                recyclerView.setAdapter(userAdapter);
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }
}
