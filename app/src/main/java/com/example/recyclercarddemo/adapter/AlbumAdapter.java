package com.example.recyclercarddemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclercarddemo.PhotoActivity;
import com.example.recyclercarddemo.R;
import com.example.recyclercarddemo.model.Album;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<Album> albumList;

    public AlbumAdapter(Context context, ArrayList<Album> albumList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.album_details,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with albumList received

        Integer id = albumList.get(i).getId();
        Integer albumId = albumList.get(i).getAlbumId();
        String title = albumList.get(i).getTitle();
        final String thumbnailUrl = albumList.get(i).getThumbnailUrl();
        String url = albumList.get(i).getUrl();
        viewHolder.title.setText(title);
        final Bitmap[] bitmap = {null};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {

                    try (InputStream is = new URL(thumbnailUrl).openStream()) {
                        bitmap[0] = BitmapFactory.decodeStream(is);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        viewHolder.thumbnailUrl.setImageBitmap(bitmap[0]);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView thumbnailUrl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), PhotoActivity.class);
                    i.putExtra("albumId",albumList.get(getAdapterPosition()).getAlbumId());
                    i.putExtra("photoId",albumList.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(i);
                }
            });
            title = itemView.findViewById(R.id.imageText);
            thumbnailUrl = itemView.findViewById(R.id.imageView);
        }
    }
}
