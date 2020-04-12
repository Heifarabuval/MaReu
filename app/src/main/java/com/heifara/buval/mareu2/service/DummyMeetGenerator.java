package com.heifara.buval.mareu2.service;

import com.heifara.buval.mareu2.model.Meet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetGenerator {
  static String dateStart1= "31-08-2020 10:00:00";
  static String dateStart2= "18-08-2020 12:00:00";
  static String dateStart3= "08-08-2020 06:00:00";
  static String dateStart4= "10-08-2020 03:00:00";
  static String dateEnd1= "31-08-2020 12:00:00";
  static String dateEnd2= "18-08-2020 15:00:00";
  static String dateEnd3= "08-08-2020 08:00:00";
  static String dateEnd4= "10-08-2020 04:00:00";

  static Calendar dateStartCal1 = convertStringToDate(dateStart1);
  static Calendar dateStartCal2 = convertStringToDate(dateStart2);
  static Calendar dateStartCal3 = convertStringToDate(dateStart3);
  static Calendar dateStartCal4 = convertStringToDate(dateStart4);
  static Calendar dateEndCal1 = convertStringToDate(dateEnd1);
  static Calendar dateEndCal2 = convertStringToDate(dateEnd2);
  static Calendar dateEndCal3 = convertStringToDate(dateEnd3);
  static Calendar dateEndCal4 = convertStringToDate(dateEnd4);
  static List<String> emailList= Arrays.asList("aaa@ddd.com","bbb@ggg.com");


    public static Calendar convertStringToDate(String date)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateString = null;
        Calendar cal =Calendar.getInstance();
        try {
            dateString = simpleDateFormat.parse(date);
            cal.setTime(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cal;
    }


public static final List<Meet> DUMMY_MEETS= Arrays.asList(
        new Meet("Room 1",dateStartCal1,dateStartCal1,dateEndCal1,emailList,"dd"),
        new Meet("Room 2",dateStartCal2,dateStartCal2,dateEndCal2,emailList,"dd"),
        new Meet("Room 3",dateStartCal3,dateStartCal3,dateEndCal3,emailList,"dd"),
        new Meet("Room 4",dateStartCal4,dateStartCal4,dateEndCal4,emailList,"dd")


        );







}
