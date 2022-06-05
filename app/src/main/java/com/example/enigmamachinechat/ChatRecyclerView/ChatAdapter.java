package com.example.enigmamachinechat.ChatRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enigmamachinechat.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private ArrayList<Chat> chats;
    private OnChatListener onChatListener;

    public ChatAdapter(ArrayList<Chat> chats, OnChatListener onChatListener) {
        this.chats = chats;
        this.onChatListener = onChatListener;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_chats, parent, false);
        return new ChatViewHolder(chatView, onChatListener);
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
    public static class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewName, textViewLastMessage, textViewLastMessageTime, textViewUnreadMessagesCount;
        public ImageView imageViewIcon;
        private OnChatListener onChatListener;

        public ChatViewHolder(@NonNull View itemView, OnChatListener onChatListener) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewGroupName);
            this.textViewLastMessage = itemView.findViewById(R.id.textViewLastMessage);
            this.textViewLastMessageTime = itemView.findViewById(R.id.textViewLastMessageTime);
            this.textViewUnreadMessagesCount = itemView.findViewById(R.id.textViewUnreadMessageCount);
            this.imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            this.onChatListener = onChatListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onChatListener.onChatClick(getAdapterPosition());
        }
    }
}
