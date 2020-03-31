package com.heifara.buval.mareu2.ui.fragment.meet_list;

import android.content.Context;
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
import com.heifara.buval.mareu2.view.ItemMeet;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ItemMeetRecyclerViewAdapter extends RecyclerView.Adapter<ItemMeet> {

    private Context mContext;
    private List<Meet> meetList;
    private MeetApiService meetApiService;





    public ItemMeetRecyclerViewAdapter(Context context, Calendar date, String room) {
        mContext = context;
        meetApiService= DI.getMeetApiService();
        meetList = meetApiService.getMeets(date,room);
    }

    @NonNull
    @Override
    public ItemMeet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meet_item,parent,false);
        return new ItemMeet(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemMeet holder, int position) {
        System.out.println(meetList.size());
        final Meet meet = meetList.get(position);



        String info = TextUtils.join("-", Arrays.asList(
                meet.getRoomName(),
                DateFormat.getTimeFormat(mContext).format(meet.getStart().getTime()),
                meet.getMeetTopic()));
        holder.mDescriptionText.setText(info);
        holder.mParticipantsText.setText(TextUtils.join(",",
                meet.getGuests()));



        //delete meet

        holder.mDeleteButton.setOnClickListener(
                v-> EventBus.getDefault().post(new DeleteMeetEvent(meet.getId()))

        );

    }

    @Override
    public int getItemCount() {
        return meetList.size();
    }
}
