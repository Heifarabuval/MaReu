package com.heifara.buval.mareu2.ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.heifara.buval.mareu2.R;
import com.heifara.buval.mareu2.model.Meet;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ItemMeet extends RecyclerView.ViewHolder {
    @BindView(R.id.circle_item)
    public CircleImageView mImageView;
    @BindView(R.id.description_item)
    public TextView mDescriptionText;
    @BindView(R.id.participants_item)
    public TextView mParticipantsText;
    @BindView(R.id.delete_item)
    public ImageButton mDeleteButton;

    public ItemMeet(@NonNull View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    private CircleImageView setImageViewColor(Meet meet, Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_circle2);
        drawable.setTint(meet.getAvatar());
        mImageView.setBackground(drawable);
        return mImageView;
    }

    public void bind(Meet meet) {
        Glide.with(mImageView.getContext())
                .load(setImageViewColor(meet, mImageView.getContext()))
                .apply(RequestOptions.circleCropTransform())
                .into(mImageView);
    }

}
