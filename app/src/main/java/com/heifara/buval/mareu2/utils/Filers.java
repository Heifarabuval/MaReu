package com.heifara.buval.mareu2.utils;

import com.heifara.buval.mareu2.model.Meet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.heifara.buval.mareu2.utils.Calendar.sameDate;

public class Filers {
    /*
    Return List sort by date
     */
    public static List<Meet> getMeetByDate(Calendar date,List<Meet> meetings){
        List<Meet> temp = new ArrayList<>();
        for(Meet meet: meetings)
            if(sameDate(meet.getStart(),date))
                temp.add(meet);
        Collections.sort(temp);
        return temp;


    }

    /*
    Return List sort by room name
     */
    public static List<Meet> getMeetByRoomName(String roomNAme, List<Meet> meets){
        List<Meet> temp = new ArrayList<>();
        for (Meet meet: meets)
            if (meet.getRoomName().trim().equals(roomNAme.trim()))
                temp.add(meet);
        Collections.sort(temp);
        return temp;
    }
}
