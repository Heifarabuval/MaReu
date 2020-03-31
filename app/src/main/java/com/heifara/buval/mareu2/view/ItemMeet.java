package com.heifara.buval.mareu2.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heifara.buval.mareu2.R;

public class ItemMeet extends RecyclerView.ViewHolder{
    public final ImageView mImageView;
    public final TextView mDescriptionText;
    public final TextView mParticipantsText;
    public final ImageButton mDeleteButton;

    public ItemMeet(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.circle_item);
        mDescriptionText = itemView.findViewById(R.id.description_item);
        mDeleteButton=itemView.findViewById(R.id.delete_item);
        mParticipantsText=itemView.findViewById(R.id.participants_item);
    }


}
