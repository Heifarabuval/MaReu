package com.heifara.buval.mareu2.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.heifara.buval.mareu2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ItemMeet extends RecyclerView.ViewHolder{

    private static final ArrayList<String> sColorRessources = new ArrayList<>(Arrays.asList(
            "#FFEB3B","#FF0000","#7EADE3","#1E0099","#24EA45","#FD4BAE","#B5BD38","#F59B42","#42F5E6","#756E91","#D054E3","#BF4349","#E3D514" ));
    public final ImageView mImageView;
    public final TextView mDescriptionText;
    public final TextView mParticipantsText;
    public final ImageButton mDeleteButton;
    private Context mContext;
   static Drawable drawable;
    public ItemMeet(@NonNull View itemView,Context context) {
        super(itemView);
        mContext = context;
        mImageView = itemView.findViewById(R.id.circle_item);
        mDescriptionText = itemView.findViewById(R.id.description_item);
        mDeleteButton=itemView.findViewById(R.id.delete_item);
        mParticipantsText=itemView.findViewById(R.id.participants_item);
        drawable = ContextCompat.getDrawable(mContext,R.drawable.ic_circle);
changeColor();

    }

    public static String  randomColor(){
        int random = new Random().nextInt(sColorRessources.size()-1);
        return sColorRessources.get(random);
    }

public void changeColor() {
    drawable.setTint(Color.parseColor(randomColor()));
    Glide.with(mImageView.getContext())
            .load(drawable)
            .apply(RequestOptions.circleCropTransform())
            .into(mImageView);

}


}
