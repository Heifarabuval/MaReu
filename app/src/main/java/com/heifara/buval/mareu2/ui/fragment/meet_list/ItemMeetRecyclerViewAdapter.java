package com.heifara.buval.mareu2.ui.fragment.meet_list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heifara.buval.mareu2.R;
import com.heifara.buval.mareu2.di.DI;
import com.heifara.buval.mareu2.event.DeleteMeetEvent;
import com.heifara.buval.mareu2.model.Meet;
import com.heifara.buval.mareu2.service.MeetApiService;
import com.heifara.buval.mareu2.ui.fragment.ItemMeet;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ItemMeetRecyclerViewAdapter extends RecyclerView.Adapter<ItemMeet> {

    public static final List<String> DRAWABLES = Arrays.asList(
            "#FFEB3B", "#FF0000", "#7EADE3", "#1E0099", "#24EA45", "#FD4BAE", "#B5BD38", "#F59B42", "#42F5E6", "#756E91", "#D054E3", "#BF4349", "#E3D514");
    private static Drawable drawable;
    private final Context mContext;
    private final List<Meet> meetList;


    public ItemMeetRecyclerViewAdapter(Context context, Calendar date, String room) {

        mContext = context;
        MeetApiService meetApiService = DI.getMeetApiService();
        meetList = meetApiService.getMeets(date, room);


    }

    @NonNull
    @Override
    public ItemMeet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meet_item, parent, false);
        return new ItemMeet(view, mContext);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemMeet holder, int position) {


        Meet meet = meetList.get(position);

        String info = TextUtils.join("-", Arrays.asList(
                meet.getRoomName(),
                DateFormat.getTimeFormat(mContext).format(meet.getStart().getTime()),
                meet.getMeetTopic()));

        /* holder.colorDrawable(meet,mContext);*/

        holder.bind(meet);

        holder.mDescriptionText.setText(info);
        holder.mParticipantsText.setText(TextUtils.join(",",
                meet.getGuests()));
        //delete meet
        holder.mDeleteButton.setOnClickListener(
                v -> {
                    EventBus.getDefault().post(new DeleteMeetEvent(meet));
                }
        );

    }
    @Override
    public int getItemCount() {
        return meetList.size();
    }


}

