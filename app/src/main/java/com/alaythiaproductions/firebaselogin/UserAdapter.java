package com.alaythiaproductions.firebaselogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<UserData> userData;

    public UserAdapter(Context context, ArrayList<UserData> userData) {
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UserData userData = this.userData.get(position);

        holder.retrieveName.setText(userData.getName());
        holder.retrievePhone.setText(userData.getPhone());
        holder.retrieveAddress.setText(userData.getAddress());
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView retrieveName, retrievePhone, retrieveAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            retrieveName = itemView.findViewById(R.id.retrieve_name);
            retrievePhone = itemView.findViewById(R.id.retrieve_phoneNumber);
            retrieveAddress = itemView.findViewById(R.id.retrieve_address);

        }
    }

}
