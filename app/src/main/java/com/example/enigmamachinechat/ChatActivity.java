package com.example.enigmamachinechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        ImageButton imageButtonReturn = (ImageButton)findViewById(R.id.imageButtonReturn);
        ImageButton imageButtonNewMessage = (ImageButton)findViewById(R.id.imageButtonEnigmaMachine);
        ImageButton imageButtonSettings = (ImageButton)findViewById(R.id.imageButtonGroupSettings);

        imageButtonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMain(view);
            }
        });

        // test data, to be replaced by the data base late
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            messages.add(new Message("message "+i, "Bot "+i, Integer.toString(i)));

        // creates and deploys the recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMessages);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter messageAdapter = new MessageAdapter(messages);
        recyclerView.setAdapter(messageAdapter);
    }

    private void returnToMain(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}