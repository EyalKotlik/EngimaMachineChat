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
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.enigmamachinechat.EnigmaMachine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

public class MainActivity extends AppCompatActivity implements OnChatListener {
    private ArrayList<Chat> chats;
    private String email;
    private FirebaseAuth firebaseAuth;
    private Context context;
    //UI elements
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        context = this;

        //sign in prompt
        ActionCodeSettings actionCodeSettings =
                ActionCodeSettings.newBuilder()
                        // URL you want to redirect back to. The domain (www.example.com) for this
                        // URL must be whitelisted in the Firebase Console.
                        .setUrl("https://www.example.com/finishSignUp?cartId=1234")
                        // This must be true
                        .setHandleCodeInApp(true)
                        .setIOSBundleId("com.example.ios")
                        .setAndroidPackageName(
                                "com.example.android",
                                true, /* installIfNotAvailable */
                                "12"    /* minimumVersion */
                        )
                        .build();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.activity_authentication);
        builder.setPositiveButton(
                com.google.android.gms.base.R.string.common_signin_button_text,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).toString();
                    }
                }
        );
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context, "must sign in to continue", Toast.LENGTH_SHORT).show();
            }
        });
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(email, actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "Email sent.");
                        }
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