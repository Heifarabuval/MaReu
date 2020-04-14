package com.heifara.buval.mareu2.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Model object representing a Meet
 */
public class Meet implements Comparable<Meet> {

    private static Integer sLastId = 0;
    /**Indentifier*/
    private Integer id;

    /**Room name*/
    private String roomName;

    /**Date*/
    private Calendar date;
    private Calendar start;

    private Calendar end;
    /**Guests*/
    private List<String> guests;

    /**Meet Name*/
    private String meetTopic;
    /**Avatar*/
    private int avatar;







    /**
     * Constructor
     * @param meetTopic
     * @param roomName
     * @param start
     * @param  end
     * @param  meetTopic
     */

    public Meet(String roomName,Calendar date, Calendar start, Calendar end, List<String> guests, String meetTopic,int avatar) {
        id = ++sLastId;
        this.roomName = roomName;
        this.start = start;
        this.end = end;
        this.guests = guests;
        this.date=date;
        this.meetTopic = meetTopic;
        guests = new ArrayList<>();
        this.avatar = avatar;

    }

    public void setGuests(List<String> guests) {
        String email_pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(email_pattern, Pattern.CASE_INSENSITIVE);


        for (String participant : guests) {
            if (pattern.matcher(participant).matches()) {
                guests.add(participant);
            }
        }

    }

    public Integer getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }

    public Calendar getDate() {
        return date;
    }


    public List<String> getGuests() {
        return guests;
    }

    public String getMeetTopic() {
        return meetTopic;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    @Override
    public int compareTo(Meet o) {
        if (getStart()==null||o.getStart()==null){
            return 0;}
        return  getStart().compareTo(o.getStart());
    }
}
