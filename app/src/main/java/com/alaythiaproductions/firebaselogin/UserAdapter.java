package com.alaythiaproductions.firebaselogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        final UserData userData = this.userData.get(position);

        holder.retrieveName.setText(userData.getName());
        holder.retrievePhone.setText(userData.getPhone());
        holder.retrieveAddress.setText(userData.getAddress());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Data").child(userData.getId());
                databaseReference.removeValue();
                Toast.makeText(context, "Data Delete", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), RetrieveData.class);
                v.getContext().startActivity(intent);

            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateData.class);
                intent.putExtra("id", userData.getId());
                intent.putExtra("name", userData.getName());
                intent.putExtra("phone", userData.getPhone());
                intent.putExtra("address", userData.getAddress());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView retrieveName, retrievePhone, retrieveAddress;
        Button btnDelete, btnUpdate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            retrieveName = itemView.findViewById(R.id.retrieve_name);
            retrievePhone = itemView.findViewById(R.id.retrieve_phoneNumber);
            retrieveAddress = itemView.findViewById(R.id.retrieve_address);
            btnDelete = itemView.findViewById(R.id.user_list_delete);
            btnUpdate = itemView.findViewById(R.id.user_list_update);
        }
    }

}
