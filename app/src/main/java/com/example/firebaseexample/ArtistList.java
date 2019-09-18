package com.example.firebaseexample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artist> {
    private Activity context;
    private List<Artist> artistList;


    public ArtistList(Activity context, List<Artist> artistList){
        super(context,R.layout.layout_list_artist, artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listItemView = inflater.inflate(R.layout.layout_list_artist,null,true);

        TextView txtNama = (TextView)listItemView.findViewById(R.id.txtNama);
        TextView txtArtis = (TextView)listItemView.findViewById(R.id.txtArtis);

        Artist artist = artistList.get(position);

        txtNama.setText(artist.getName());
        txtArtis.setText(artist.getArtist());

        return listItemView;
    }
}
