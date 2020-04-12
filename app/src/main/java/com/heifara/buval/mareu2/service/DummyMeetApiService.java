package com.heifara.buval.mareu2.service;

import com.heifara.buval.mareu2.model.Meet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.heifara.buval.mareu2.utils.Filers.getMeetByDate;
import static com.heifara.buval.mareu2.utils.Filers.getMeetByRoomName;

public class DummyMeetApiService implements MeetApiService{

    private List<Meet> mMeet;
    private final List<String> mRooms;
    static Calendar calendar = Calendar.getInstance();





    /* Room List */
    public DummyMeetApiService() {
        mMeet = new ArrayList<>();
    mMeet.addAll(DummyMeetGenerator.DUMMY_MEETS);
        mRooms = new ArrayList<>(Arrays.asList(
                "Room 1","Room 2 ","Room 3","Room 4 ","Room 5","Room 6 ",
                "Room 7","Room 8 ","Room 9","Room 10 "));

    }

    /* Override methods from MeetApiService */
    @Override
    public List<Meet> getMeets(Calendar date, String roomName) {

        if (date!=null && roomName!= null && !roomName.isEmpty())
            return getMeetByDate(date, getMeetByRoomName(roomName,mMeet));

        else if (date!=null)
            return getMeetByDate(date,mMeet);
        else if (roomName!= null && ! roomName.isEmpty())
            return getMeetByRoomName(roomName,mMeet);
        Collections.sort(mMeet);
        return mMeet;

    }

    @Override
    public List<Meet> getMeetsList() {
        return mMeet;
    }

    @Override
    public List<String> getRooms() {return mRooms;}

    @Override
    public void addRoom(String room) {mRooms.add(room);}

    @Override
    public void delRoom(String room) {mRooms.remove(room);}

    @Override
    public void delAllRooms() {mRooms.clear();}

    @Override
    public void deleteMeet(Integer idMeet) {
        for(Meet meet: mMeet){
            if (meet.getId().equals(idMeet)){
                mMeet.remove(meet);
            }
        }
    }

    @Override
    public void createMeet(Meet meet) {mMeet.add(meet); }
}
