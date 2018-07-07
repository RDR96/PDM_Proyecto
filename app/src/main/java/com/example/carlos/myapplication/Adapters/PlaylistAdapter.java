package com.example.carlos.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlos.myapplication.R;

/**
 * Created by Deryan Cruz on 07/07/2018.
 */

public class PlaylistAdapter  extends RecyclerView.Adapter<PlaylistAdapter.TopPlayersHolder>{

    private Context context;
    List<>


    @NonNull
    @Override
    public TopPlayersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TopPlayersHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TopPlayersHolder extends RecyclerView.ViewHolder {

        //TODO inicializar los controles del news_card

        TextView albumPlaylist, autorPlaylist, namePlaylist;
        ImageView imagePlaylist;

        public PlaylistHolder(View itemView) {
            super(itemView);
            namePlaylist = itemView.findViewById(R.id.nombre_playlist);
            autorPlaylist = itemView.findViewById(R.id.nombre_autor);
            albumPlaylist = itemView.findViewById(R.id.nombre_album);
            imagePlaylist = itemView.findViewById(R.id.image_playlist);
        }
    }
}
