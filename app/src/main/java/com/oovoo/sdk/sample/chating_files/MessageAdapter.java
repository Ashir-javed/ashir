package com.oovoo.sdk.sample.chating_files;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oovoo.sdk.oovoosdksampleshow.R;

import java.util.List;


public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.ViewHolder>{


    private List<Message> mMessages;
    private int[] mUsernameColors;


    public MessageAdapter(List<Message> messages) {
        this.mMessages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_message, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder , int position) {

        Message message = mMessages.get(position);
        viewHolder.setMessage(message.getMessage());
        viewHolder.setImage(message.getImage());

    }


    @Override
    public int getItemCount() {
        return mMessages.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getType();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mMessageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.image);
            mMessageView = (TextView)itemView.findViewById(R.id.message);
        }
        public void setMessage(String message){
            if (null==mMessageView){
                return;
            }
            if (null==message){
                return;
            }
            mMessageView.setText(message);
        }
        public void setImage(Bitmap bmp){
            if (null==mImageView)return;

            if (null==bmp) return;

            mImageView.setImageBitmap(bmp);
        }

        private int getUsernameColor(String username){
            int hash = 8;
            for (int i =0 ,len =username.length();i<len;i++){
                hash = username.codePointAt(i)+(hash<<5)-hash;

            }
            int index = Math.abs(hash % mUsernameColors.length);
            return mUsernameColors[index];
        }
    }


}
