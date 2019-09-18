package com.example.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddTrackActivity extends AppCompatActivity {

    TextView tvArtistName;
    EditText trackName;
    SeekBar seekBar;
    ListView listTrack;

    ListView listViewTrack;

    Button btnAddTrack;

    DatabaseReference databaseReference;

    List<Track> trackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        tvArtistName = (TextView)findViewById(R.id.tvArtistName);
        trackName = (EditText)findViewById(R.id.txtTrack);
        seekBar = (SeekBar)findViewById(R.id.seekBarRating);
        listTrack = (ListView)findViewById(R.id.listViewTrack);
        btnAddTrack = (Button)findViewById(R.id.btnSimpanTrack);

        listViewTrack = (ListView)findViewById(R.id.listViewTrack);

        Intent intent = getIntent();
        trackList = new ArrayList<>();
        String id = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);

        tvArtistName.setText(name);

        databaseReference = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        btnAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrack();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trackList.clear();
                for (DataSnapshot trackSnapshot : dataSnapshot.getChildren()){
                    Track track = trackSnapshot.getValue(Track.class);
                    trackList.add(track);
                }
                TrackList trackListAdapter = new TrackList(AddTrackActivity.this,trackList);
                listViewTrack.setAdapter(trackListAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveTrack(){
        String track = trackName.getText().toString().trim();
        int rating = seekBar.getProgress();

        if (!TextUtils.isEmpty(track)){

            String id = databaseReference.push().getKey();

            Track track1 =  new Track(id,track,rating);

            databaseReference.child(id).setValue(track1);

            Toast.makeText(getApplicationContext(),"Track berhasil dimasukkan!",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplicationContext(),"Mohon Isi Nama Track",Toast.LENGTH_SHORT).show();
        }
    }
}
