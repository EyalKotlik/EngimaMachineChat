package com.example.enigmamachinechat.MessageRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enigmamachinechat.R;

import java.util.ArrayList;
import java.util.Collections;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private ArrayList<Message> messages;

    public MessageAdapter(ArrayList<Message> messages) {
        Collections.reverse(messages);
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View messageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_message, parent, false);
        return new MessageAdapter.MessageViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.textViewMessageSender.setText(message.getSender());
        holder.textViewTime.setText(message.getTime());
        holder.textViewMessageText.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    // a class holding all the references to all the Views in the recycler view
    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMessageSender, textViewTime, textViewMessageText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewMessageSender = itemView.findViewById(R.id.textViewMessageSender);
            this.textViewTime = itemView.findViewById(R.id.textViewTime);
            this.textViewMessageText = itemView.findViewById(R.id.textViewMessageText);
        }
    }
}
