package com.example.android.musicplayerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bruno André on 04/04/2018.
 */

public class CategorySongListActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    // Various identifiers
    private String mIntentMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);

                /*
        Start---------->Bottom Navigation Bar with Activities<-------------
        Bottom Navigation Bar with Activities - Android Advanced Tutorial #6 - https://www.youtube.com/watch?v=xyGrdOqseuw
         */

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        //To change color as we change activity
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_home:
                                Intent intent1 = new Intent(CategorySongListActivity.this, MainActivity.class);
                                startActivity(intent1);
                                break;

                            case R.id.action_artist:
                                Intent intent2 = new Intent(CategorySongListActivity.this, AllArtistListActivity.class);
                                startActivity(intent2);
                                break;

                            case R.id.action_song:
                                Intent intent3 = new Intent(CategorySongListActivity.this, AllSongListActivity.class);
                                startActivity(intent3);
                                break;
                        }

                        return false;
                    }
                });

//End---------->Bottom Navigation Bar with Activities<-------------


        Bundle bundle = getIntent().getExtras();
        mIntentMessage = bundle.getString("message");

        // Display list of songs based on category chosen
        createSongList();
    }

    /**
     * Method to create list of songs to be displayed in homepage
     */
    public void createSongList() {

        ArrayList<AllSong> allSongs = new ArrayList<AllSong>();

        if (mIntentMessage.equals(getString(R.string.library_category_musopen))) {


            allSongs.add(new AllSong("Maple Leaf Rag", "Scott Joplin"));
            allSongs.add(new AllSong("Hungarian Rhapsody", "Franz Liszt"));
            allSongs.add(new AllSong("Flight of the Bumblebee", "Korsakov"));
            allSongs.add(new AllSong("Eine Kleine Nachtmusik", "Mozart"));
            allSongs.add(new AllSong("Dance of the sugar plum fairy", "Tchaikovsky"));

        } else if (mIntentMessage.equals(getString(R.string.library_category_youtube))) {


            allSongs.add(new AllSong("Toccata in D Minor", "Bach"));
            allSongs.add(new AllSong("Symphony No 5", "Beethoven"));
            allSongs.add(new AllSong("Aleluia Messiah", "Handel"));
            allSongs.add(new AllSong("If I Had a Chicken", "Kevin MacLeod"));
            allSongs.add(new AllSong("The Entertainer", "Scott Joplin"));

        } else if (mIntentMessage.equals(getString(R.string.library_category_bensound))) {


            allSongs.add(new AllSong("Buddy", "Bensound"));
            allSongs.add(new AllSong("Cute", "Bensound"));
            allSongs.add(new AllSong("Happiness", "Bensound"));
            allSongs.add(new AllSong("Sunny", "Bensound"));
            allSongs.add(new AllSong("Ukulele", "Bensound"));

        }

        ListView listView = (ListView) findViewById(R.id.list_albums);
        listView.setBackgroundColor(getResources().getColor(R.color.home_background));


        // Create AllSongAdapter object to display listview
        AllSongAdapter adapter = new AllSongAdapter(this, allSongs);
        listView.setAdapter(adapter);

        // Set OnClickListener on ListView to identify the item on ListView clicked by user
        // Text on the ListView item clicked is passed on to MediaPlayerActivity
        listView.setOnItemClickListener(this);
    }

    /**
     * Method to identify ListView item clicked and launch MediaPlayerActivity
     *
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        final Context context = this;
        String intentExtra = "";

        TextView textViewSong = (TextView) view.findViewById(R.id.text_song);
        String song = textViewSong.getText().toString();

        TextView textViewSinger = (TextView) view.findViewById(R.id.text_singer);
        String singer = textViewSinger.getText().toString();

        intentExtra = song + "|" + singer;
        Intent intent = new Intent(context, MediaPlayerActivity.class);
        intent.putExtra("message", intentExtra);
        startActivity(intent);
    }

}