package com.example.firestoreapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firestoreapplication.models.Friends;

import java.util.ArrayList;

public class AdapterFriends extends RecyclerView.Adapter<AdapterFriends.ViewHolderFriends> {
    ArrayList<Friends> ListaDePersonajes;
    private int itemselected;

    public AdapterFriends(ArrayList<Friends> listaDePersonajes) {
        ListaDePersonajes = listaDePersonajes;
    }

    @NonNull
    @Override
    public ViewHolderFriends onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,null,false);
        return new ViewHolderFriends(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFriends holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(ListaDePersonajes.get(position).getName());
        holder.email.setText(ListaDePersonajes.get(position).getEmail());
        holder.id.setText(ListaDePersonajes.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return ListaDePersonajes.size();
    }
    public class ViewHolderFriends extends RecyclerView.ViewHolder {
        TextView name, email, id ;
        public ViewHolderFriends(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nombre_view);
            email = itemView.findViewById(R.id.correo_view);
            id = itemView.findViewById(R.id.id_view);
        }
    }
}