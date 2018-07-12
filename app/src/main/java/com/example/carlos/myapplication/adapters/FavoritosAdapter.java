package com.example.carlos.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlos.myapplication.Database.Entidades.Cancion;
import com.example.carlos.myapplication.R;
import com.example.carlos.myapplication.activities.ReproductorActivity;

import java.util.ArrayList;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.ViewHolder> {

    ArrayList<Cancion> listDatos;
    Context context;
    View view;

    public FavoritosAdapter(Context context, ArrayList<Cancion> listDatos) {
        this.listDatos = listDatos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fav, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosAdapter.ViewHolder holder,final int position) {
        holder.title_fav.setText(listDatos.get(position).getTitulo());

        holder.itemCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReproductorActivity.class);
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                Toast.makeText(context, listDatos.get(position).getId(),Toast.LENGTH_SHORT).show();
                intent.putExtra(Intent.EXTRA_TEXT,listDatos.get(position).getTitulo() + "-/" + listDatos.get(position).getCantante() + "-/" + listDatos.get(position).getLocalizacion() + "-/" + listDatos.get(position).getAlbum() + "-/" + position + "-/" + "0");
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_fav;
        TextView title_fav;
        ConstraintLayout itemCancion;

        public ViewHolder(View itemView) {
            super(itemView);
            img_fav = itemView.findViewById(R.id.img_fav);
            title_fav = itemView.findViewById(R.id.title_fav);
            itemCancion = itemView.findViewById(R.id.item_cancion);
        }

    }
}
