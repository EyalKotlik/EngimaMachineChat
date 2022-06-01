package com.example.enigmamachinechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.enigmamachinechat.EnigmaMachine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements OnChatListener{
    private ArrayList<Chat> chats;

    //UI elements
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // test data, to be replaced by the data base late
        chats = new ArrayList<>();
        Bitmap testImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.chat_test_icon);
        ArrayList<String> members = new ArrayList<String>();
        members.add("first");
        members.add("second");
        for (int i = 0; i < 50; i++)
            chats.add(new Chat("Group" + i, "Hi " + i, "12:0" + i, testImage, i, members));

        // creates and deploys the recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewChats);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ChatAdapter chatAdapter = new ChatAdapter(chats, this);
        recyclerView.setAdapter(chatAdapter);
    }



    @Override
    public void onChatClick(int position) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("groupName", chats.get(position).getName());
        intent.putExtra("groupMembers", chats.get(position).getChatMemebrs());
        startActivity(intent);
    }
}