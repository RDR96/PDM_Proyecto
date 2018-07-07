package com.example.carlos.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlos.myapplication.objects.Notificaciones;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

public class NotificacionesAdapter extends RecyclerView.Adapter<NotificacionesAdapter.ViewHolder> {

    ArrayList<Notificaciones> listDatos;

    public NotificacionesAdapter(ArrayList<Notificaciones> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_notificaciones, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificacionesAdapter.ViewHolder holder, int position) {

        holder.etiqueta.setText(listDatos.get(position).getEtiqueta());
        holder.usuario.setText(listDatos.get(position).getUsuario());
        holder.te_han_etiquetado.setText(listDatos.get(position).getTe_han_etiquetado());
        holder.nombre_cancion.setText(listDatos.get(position).getNombre_cancion());
        holder.album.setText(listDatos.get(position).getAlbum());
        holder.artista.setText(listDatos.get(position).getArtista());
        holder.tiempo.setText(listDatos.get(position).getTiempo());
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView etiqueta, usuario, te_han_etiquetado, nombre_cancion, album, artista, tiempo;
        ImageView foto_pequeña, fotogrande;

        public ViewHolder(View itemView) {
            super(itemView);

            etiqueta = itemView.findViewById(R.id.etiquetaNotificaciones);
            usuario = itemView.findViewById(R.id.usuarioNotificaciones);
            te_han_etiquetado = itemView.findViewById(R.id.te_etiquetaronNotificaciones);
            nombre_cancion = itemView.findViewById(R.id.nombreCancionNotificaciones);
            artista = itemView.findViewById(R.id.ArtistaNotificaciones);
            album = itemView.findViewById(R.id.AlbumNotificaiones);
            tiempo = itemView.findViewById(R.id.TiempoNotificaciones);

            foto_pequeña = itemView.findViewById(R.id.fotopequeñaNotificaciones);
            fotogrande = itemView.findViewById(R.id.fotoGrandeNotificaciones);
        }

    }
}
