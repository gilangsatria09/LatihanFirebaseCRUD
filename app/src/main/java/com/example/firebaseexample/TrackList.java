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

public class TrackList extends ArrayAdapter<Track> {
    private Activity context;
    private List<Track>trackList;

    public TrackList(Activity context, List<Track> trackList){
        super(context,R.layout.layout_list_track, trackList);
        this.context = context;
        this.trackList = trackList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listItemView = inflater.inflate(R.layout.layout_list_track,null,true);

        TextView txtNama = (TextView)listItemView.findViewById(R.id.txtNamaTrack);
        TextView txtRating = (TextView)listItemView.findViewById(R.id.txtRating);

        Track track = trackList.get(position);

        txtNama.setText(track.getTrackName());
        txtRating.setText(String.valueOf(track.getTrackRating()));

        return listItemView;
    }
}
