package com.example.crud.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.entity.DataPengguna;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataPengguna> list;
    MainContact.view aView;

    public MainAdapter(Context context, List<DataPengguna> list, MainContact.view aView) {
        this.context = context;
        this.list = list;
        this.aView = aView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pengguna,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.viewHolder holder, int position) {
        final DataPengguna item = list.get(position);
        holder.tvName.setText(item.getName());
        holder.tvBirthday.setText(item.getBirthday());
        holder.tvAddress.setText(item.getAddress());
        holder.tvPhone.setText(item.getPhone());
        holder.tvEmail.setText(item.getEmail());
        holder.id.setText(Integer.toString(item.getId()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                aView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvBirthday,tvAddress,tvPhone,tvEmail,id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvName  =   itemView.findViewById(R.id.tv_item_name);
            tvBirthday  =   itemView.findViewById(R.id.tv_item_birthday);
            tvAddress  =   itemView.findViewById(R.id.tv_item_address);
            tvPhone  =   itemView.findViewById(R.id.tv_item_phone);
            tvEmail  =   itemView.findViewById(R.id.tv_item_email);
            id  =   itemView.findViewById(R.id.tv_item_id);
            cardView  =   itemView.findViewById(R.id.cv);
        }
    }
}

