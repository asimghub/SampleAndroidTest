package com.example.recyclercarddemo.retrofit;

import com.example.recyclercarddemo.model.Album;
import com.example.recyclercarddemo.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("https://jsonplaceholder.typicode.com/users")
    Call<List<User>> doGetListUsers();

    @GET("https://jsonplaceholder.typicode.com/photos")
    Call<List<Album>> doGetListAlbums();
}