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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    // UI elements
    private ImageButton imageButtonReturn, imageButtonNewMessage, imageButtonSettings;
    private TextView textViewGroupNameTitle, textViewGroupMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // defining all the UI elements
        imageButtonReturn = (ImageButton) findViewById(R.id.imageButtonReturn);
        imageButtonNewMessage = (ImageButton) findViewById(R.id.imageButtonEnigmaMachine);
        imageButtonSettings = (ImageButton) findViewById(R.id.imageButtonGroupSettings);
        textViewGroupNameTitle = (TextView) findViewById(R.id.textViewGroupNameTitle);
        textViewGroupMembers = (TextView) findViewById(R.id.textViewGroupMembers);

        // setting all the properties and methods for the UI elements
        imageButtonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMain(view);
            }
        });
        textViewGroupNameTitle.setText(getIntent().getExtras().getString("groupName"));
        String groupMembers = "";
        ArrayList<String> groupMembersList = getIntent().getExtras().getStringArrayList("groupMembers");
        for (int i = 0; i < groupMembersList.size() - 1; i++)
            groupMembers += groupMembersList.get(i)+", ";
        groupMembers += groupMembersList.get(groupMembersList.size()-1);
        textViewGroupMembers.setText(groupMembers);


        // test data, to be replaced by the data base late
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            messages.add(new Message("message " + i, "Bot " + i, Integer.toString(i)));

        // creates and deploys the recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMessages);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter messageAdapter = new MessageAdapter(messages);
        recyclerView.setAdapter(messageAdapter);
    }

    private void returnToMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}