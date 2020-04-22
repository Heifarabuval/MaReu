package com.heifara.buval.mareu2.ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.heifara.buval.mareu2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemMeet extends RecyclerView.ViewHolder {
    @BindView(R.id.circle_item)
    public ImageView mImageView;


    public final TextView mDescriptionText;
    public final TextView mParticipantsText;
    public final ImageButton mDeleteButton;

    public ItemMeet(@NonNull View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mImageView = itemView.findViewById(R.id.circle_item);
        mDescriptionText = itemView.findViewById(R.id.description_item);
        mDeleteButton = itemView.findViewById(R.id.delete_item);
        mParticipantsText = itemView.findViewById(R.id.participants_item);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_circle);


    }


}
