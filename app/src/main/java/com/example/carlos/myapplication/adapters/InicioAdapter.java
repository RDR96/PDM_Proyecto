package com.example.carlos.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlos.myapplication.activities.ReproductorActivity;
import com.example.carlos.myapplication.objects.Cancion;
import com.example.carlos.myapplication.objects.Inicio;
import com.example.carlos.myapplication.R;

import java.util.ArrayList;

public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.ViewHolder> {

    ArrayList<Cancion> listDatos;
    Context context;
    public InicioAdapter(Context context, ArrayList<Cancion> listDatos){
        this.context = context;
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_inicio, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InicioAdapter.ViewHolder holder,final int position) {
        holder.nombre_cancion.setText(listDatos.get(position).getTitulo());
        holder.artista.setText(listDatos.get(position).getCantante());

        holder.itemCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReproductorActivity.class);
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,listDatos.get(position).getTitulo() + "-/" + listDatos.get(position).getCantante() + "-/" + listDatos.get(position).getLocalizacion());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout itemCancion;
        TextView usuario, nombre_cancion, artista, album;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);

            usuario = itemView.findViewById(R.id.usuario_Inicio);
            nombre_cancion = itemView.findViewById(R.id.nombreCancion_Inicio);
            artista = itemView.findViewById(R.id.artista_Inicio);
            album = itemView.findViewById(R.id.album_Inicio);
            itemCancion = itemView.findViewById(R.id.item_cancion);
            imagen = itemView.findViewById(R.id.foto_Inicio);
        }
    }
}
