package com.example.carlos.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlos.myapplication.objects.Inicio;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.ViewHolder> {

    ArrayList<Inicio> listDatos;

    public InicioAdapter(ArrayList<Inicio> listDatos){
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_inicio, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InicioAdapter.ViewHolder holder, int position) {

        holder.usuario.setText(listDatos.get(position).getUsuario());
        holder.nombre_cancion.setText(listDatos.get(position).getNombre_Cancion());
        holder.artista.setText(listDatos.get(position).getArtista());
        holder.album.setText(listDatos.get(position).getAlbum());
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView usuario, nombre_cancion, artista, album;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);

            usuario = itemView.findViewById(R.id.usuario_Inicio);
            nombre_cancion = itemView.findViewById(R.id.nombreCancion_Inicio);
            artista = itemView.findViewById(R.id.artista_Inicio);
            album = itemView.findViewById(R.id.album_Inicio);

            imagen = itemView.findViewById(R.id.foto_Inicio);
        }
    }
}
