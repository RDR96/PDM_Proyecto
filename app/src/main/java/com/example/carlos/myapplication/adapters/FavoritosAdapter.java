package com.example.carlos.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlos.myapplication.objects.Cancion;
import com.example.carlos.myapplication.objects.Notificaciones;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.ViewHolder> {

    ArrayList<Cancion> listDatos;

    public FavoritosAdapter(ArrayList<Cancion> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fav, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosAdapter.ViewHolder holder, int position) {
        holder.title_fav.setText(listDatos.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_fav;
        TextView title_fav;

        public ViewHolder(View itemView) {
            super(itemView);
            img_fav = itemView.findViewById(R.id.img_fav);
            title_fav = itemView.findViewById(R.id.title_fav);
        }

    }
}
