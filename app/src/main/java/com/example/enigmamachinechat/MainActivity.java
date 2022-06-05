package com.example.enigmamachinechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.enigmamachinechat.ChatRecyclerView.Chat;
import com.example.enigmamachinechat.ChatRecyclerView.ChatAdapter;
import com.example.enigmamachinechat.ChatRecyclerView.OnChatListener;
import com.example.enigmamachinechat.EnigmaMachine.EnigmaMachine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

public class MainActivity extends AppCompatActivity implements OnChatListener {
    public static final String TAG = "MainActivity";
    private ArrayList<Chat> chats;
    private FirebaseAuth firebaseAuth;

    private EnigmaMachine enigma;
    //UI elements
    private ImageButton imageButtonMainEnigmaMachine, imageButtonSettingsMain, imageButtonSignOut;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // assigning the UI elements
        imageButtonMainEnigmaMachine = (ImageButton) findViewById(R.id.imageButtonMainEnigmaMachine);
        imageButtonSettingsMain = (ImageButton) findViewById(R.id.imageButtonSettingsMain);
        imageButtonSignOut = (ImageButton) findViewById(R.id.imageButtonSignOut);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewChats);

        // assigning the functions for the UI elements
        imageButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this, AuthenticationActivity.class));
            }
        });

        // test data, to be replaced by the data base late
        chats = new ArrayList<>();
        Bitmap testImage =
                BitmapFactory.decodeResource(this.getResources(), R.drawable.chat_test_icon);
        ArrayList<String> members = new ArrayList<String>();
        members.add("first");
        members.add("second");
        for (int i = 0; i < 50; i++)
            chats.add(new Chat("Group" + i, "Hi " + i, "12:0" + i, testImage, i, members));

        // creates and deploys the recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ChatAdapter chatAdapter = new ChatAdapter(chats, this);
        recyclerView.setAdapter(chatAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (firebaseAuth.getCurrentUser() != null) {
            return;
        }
        startActivity(new Intent(this, AuthenticationActivity.class));
    }

    @Override
    public void onChatClick(int position) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("groupName", chats.get(position).getName());
        intent.putExtra("groupMembers", chats.get(position).getChatMemebrs());
        startActivity(intent);
    }

}