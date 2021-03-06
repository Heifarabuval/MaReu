package com.heifara.buval.mareu2.service;

import com.heifara.buval.mareu2.model.Meet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.heifara.buval.mareu2.utils.Filters.getMeetByDate;
import static com.heifara.buval.mareu2.utils.Filters.getMeetByRoomName;

public class DummyMeetApiService implements MeetApiService {

    private final List<Meet> mMeet;
    private final List<String> mRooms;


    /* Room List */
    public DummyMeetApiService() {
        mMeet = new ArrayList<>();
        mMeet.addAll(DummyMeetGenerator.DUMMY_MEETS);
        mRooms = new ArrayList<>(Arrays.asList(
                "Room 1", "Room 2 ", "Room 3", "Room 4 ", "Room 5", "Room 6 ",
                "Room 7", "Room 8 ", "Room 9", "Room 10 "));

    }

    /* Override methods from MeetApiService */
    @Override
    public List<Meet> getMeets(Calendar date, String roomName) {

        if (date != null && roomName != null && !roomName.isEmpty())
            return getMeetByDate(date, getMeetByRoomName(roomName, mMeet));

        else if (date != null)
            return getMeetByDate(date, mMeet);
        else if (roomName != null && !roomName.isEmpty())
            return getMeetByRoomName(roomName, mMeet);
        Collections.sort(mMeet);
        return mMeet;

    }

    @Override
    public List<Meet> getMeetsList() {
        return mMeet;
    }

    @Override
    public List<String> getRooms() {
        return mRooms;
    }


    @Override
    public void deleteMeet(Meet meetItem) {
        for (Meet meet : new ArrayList<>(mMeet)) {
            if (meet.equals(meetItem)) {
                mMeet.remove(meet);
            }
        }
    }


    @Override
    public void createMeet(Meet meet) {
        mMeet.add(meet);
    }
}
