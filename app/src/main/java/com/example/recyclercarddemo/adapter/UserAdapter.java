package com.example.recyclercarddemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclercarddemo.AlbumActivity;
import com.example.recyclercarddemo.R;
import com.example.recyclercarddemo.model.User;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<User> userList;

    public UserAdapter(Context context, ArrayList<User> userList){
        this.layoutInflater = LayoutInflater.from(context);
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.user_details,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with userList received

        Integer id = userList.get(i).getId();
        String name = userList.get(i).getName();
        String email = userList.get(i).getEmail();
        String phone = userList.get(i).getPhone();
        viewHolder.id.setText("ID: " + id.toString());
        viewHolder.name.setText("Name: " + name);
        viewHolder.email.setText("Email: " + email);
        viewHolder.phone.setText("Phone: " + phone);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, name,email,phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), AlbumActivity.class);
                    i.putExtra("id",userList.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(i);
                }
            });
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
