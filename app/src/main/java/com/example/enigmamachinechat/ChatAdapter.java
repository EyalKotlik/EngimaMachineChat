package com.example.enigmamachinechat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private ArrayList<Chat> chats;

    public ChatAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_chat,parent, false);
        return new ChatViewHolder(chatView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.textViewName.setText(chat.getName());
        holder.textViewLastMessage.setText(chat.getLastMessage());
        holder.textViewLastMessageTime.setText(chat.getLastMessageTime());
        holder.textViewUnreadMessagesCount.setText(Integer.toString(chat.getUnreadMessagesCount()));
        holder.imageViewIcon.setImageBitmap(chat.getIcon());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    // a class holding all the references to all the Views in the recycler view
    public static class ChatViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName, textViewLastMessage, textViewLastMessageTime, textViewUnreadMessagesCount;
        public ImageView imageViewIcon;

        public ChatViewHolder(@NonNull View itemView){
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewGroupName);
            this.textViewLastMessage = itemView.findViewById(R.id.textViewLastMessage);
            this.textViewLastMessageTime = itemView.findViewById(R.id.textViewLastMessageTime);
            this.textViewUnreadMessagesCount = itemView.findViewById(R.id.textViewUnreadMessageCount);
            this.imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
        }
    }
}
