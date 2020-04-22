package com.heifara.buval.mareu2.service;

import android.graphics.Color;

import com.heifara.buval.mareu2.model.Meet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetGenerator {

    private static final String DATE_START1 = "31-08-2020 10:00:00";
    private static final String DATE_START2 = "18-08-2020 12:00:00";
    private static final String DATE_START3 = "08-08-2020 06:00:00";
    private static final String DATE_START4 = "10-08-2020 03:00:00";
    private static final String DATE_END1 = "31-08-2020 12:00:00";
    private static final String DATE_END2 = "18-08-2020 15:00:00";
    private static final String DATE_END3 = "08-08-2020 08:00:00";
    private static final String DATE_END4 = "10-08-2020 04:00:00";
    private static final Calendar DATE_START_CAL1 = convertStringToDate(DATE_START1);
    private static final Calendar DATE_START_CAL2 = convertStringToDate(DATE_START2);
    private static final Calendar DATE_START_CAL3 = convertStringToDate(DATE_START3);
    private static final Calendar DATE_START_CAL4 = convertStringToDate(DATE_START4);
    private static final Calendar DATE_END_CAL1 = convertStringToDate(DATE_END1);
    private static final Calendar DATE_END_CAL2 = convertStringToDate(DATE_END2);
    private static final Calendar DATE_END_CAL3 = convertStringToDate(DATE_END3);
    private static final Calendar DATE_END_CAL4 = convertStringToDate(DATE_END4);
    private static final List<String> EMAIL_LIST = Arrays.asList("aaa@ddd.com", "bbb@ggg.com");
    private static final String ROOM1 = "Room 1";
    private static final String ROOM2 = "Room 2";
    private static final String ROOM3 = "Room 3";
    private static final String ROOM4 = "Room 4";
    private static final String COLOR1 = "#E3D514";
    private static final String COLOR2 = "#FF7C80";
    private static final String COLOR3 = "#756E91";
    private static final String COLOR4 = "#F59B42";
    private static final String MEET_TOPIC = "Java conference";
    public static final List<Meet> DUMMY_MEETS = Arrays.asList(


            new Meet(ROOM1, DATE_START_CAL1, DATE_START_CAL1, DATE_END_CAL1, EMAIL_LIST, MEET_TOPIC, Color.parseColor(COLOR1)),
            new Meet(ROOM2, DATE_START_CAL2, DATE_START_CAL2, DATE_END_CAL2, EMAIL_LIST, MEET_TOPIC, Color.parseColor(COLOR2)),
            new Meet(ROOM3, DATE_START_CAL3, DATE_START_CAL3, DATE_END_CAL3, EMAIL_LIST, MEET_TOPIC, Color.parseColor(COLOR3)),
            new Meet(ROOM4, DATE_START_CAL4, DATE_END_CAL4, DATE_END_CAL4, EMAIL_LIST, MEET_TOPIC, Color.parseColor(COLOR4))


    );

    private static Calendar convertStringToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateString;
        Calendar cal = Calendar.getInstance();
        try {
            dateString = simpleDateFormat.parse(date);
            cal.setTime(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cal;
    }


}
