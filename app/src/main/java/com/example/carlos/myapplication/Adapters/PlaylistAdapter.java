package com.example.carlos.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlos.myapplication.Objects.Playlist;
import com.example.carlos.myapplication.R;

import java.util.List;

/**
 * Created by Deryan Cruz on 07/07/2018.
 */

public class PlaylistAdapter  extends RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder>{

    private Context context;
    private List<Playlist> playlistList;

    public PlaylistAdapter(Context context, List<Playlist> playlistList) {
        this.context = context;
        this.playlistList = playlistList;
    }

    @NonNull
    @Override
    public PlaylistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_playlist,null,false);
        return new PlaylistAdapter.PlaylistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistHolder holder, int position) {
        holder.albumPlaylist.setText(playlistList.get(position).getAlbumPlaylist());
        holder.autorPlaylist.setText(playlistList.get(position).getAutorPlaylist());
        holder.namePlaylist.setText(playlistList.get(position).getNombrePlaylist());
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }


    public class PlaylistHolder extends RecyclerView.ViewHolder {

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
