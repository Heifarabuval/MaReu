package com.heifara.buval.mareu2.utils;

public class Calendar {

    // (Year) firstDate < secondDate = true
    // (Year) firstDate=secondDate (Month) firstDate < secondDate = true
    // (Year) firstDate=secondDate (Month) firstDate=secondDate (Day) firstDate<=secondDate = true
    public static boolean beforeSameDate(java.util.Calendar firstDate, java.util.Calendar secondDate){
        if(firstDate.get(java.util.Calendar.YEAR)< secondDate.get(java.util.Calendar.YEAR))
            return true;
        if(firstDate.get(java.util.Calendar.YEAR)==secondDate.get(java.util.Calendar.YEAR))
            if (firstDate.get(java.util.Calendar.MONTH)<secondDate.get(java.util.Calendar.MONTH))
                return true;
        if (firstDate.get(java.util.Calendar.YEAR)==secondDate.get(java.util.Calendar.YEAR))
            if (firstDate.get(java.util.Calendar.MONTH)==secondDate.get(java.util.Calendar.MONTH))
                return firstDate.get(java.util.Calendar.DAY_OF_MONTH)<=secondDate.get(java.util.Calendar.DAY_OF_MONTH);
        return  false;
    }

    public static boolean checkTime(java.util.Calendar startTime,java.util.Calendar endTime,java.util.Calendar startTimeItemList,java.util.Calendar endTimeItemList){

        if (((startTime.after(startTimeItemList))&&(startTime.before(endTimeItemList)))||((endTime.after(startTimeItemList))&&(endTime.before(endTimeItemList)))) {
            System.out.println("Invalid Start input time, the following room is already booked for this time slot");
            return true;
        }else return false;



    }

    public static boolean checkDate(java.util.Calendar startTime,java.util.Calendar endTime,java.util.Calendar startTimeItemList,java.util.Calendar endTimeItemList){
        if ((((startTime.get(java.util.Calendar.HOUR_OF_DAY))>=startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)) &&
                startTime.get(java.util.Calendar.HOUR_OF_DAY)<endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)) ||
                (((endTime.get(java.util.Calendar.HOUR_OF_DAY))>startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)) &&
                endTime.get(java.util.Calendar.HOUR_OF_DAY)<=endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))){
            return true; //  startTimeList <= start < endTimeList || startTimeList < end <= endTimeList
        }
        if ((startTime.get(java.util.Calendar.HOUR_OF_DAY) == startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))){
            if ((startTime.get(java.util.Calendar.MINUTE) == startTimeItemList.get(java.util.Calendar.MINUTE))){
                return true;
            }
    }else  if ((((startTime.get(java.util.Calendar.MINUTE))>=startTimeItemList.get(java.util.Calendar.MINUTE)) &&
                startTime.get(java.util.Calendar.MINUTE)<endTimeItemList.get(java.util.Calendar.MINUTE)) ||
                (((endTime.get(java.util.Calendar.MINUTE))>startTimeItemList.get(java.util.Calendar.MINUTE)) &&
                        endTime.get(java.util.Calendar.MINUTE)<=endTimeItemList.get(java.util.Calendar.MINUTE))){
            return true; //  startTimeList =< start < endTimeList || startTimeList < end <= endTimeList
        }else return false;
        return false;
    }
//(Hour) firstTime<secondTime = true
// (Hour) firstTime=secondTime (Minute) firstTime<=secondTime = true
    public static boolean beforeSameTime(java.util.Calendar firstTime, java.util.Calendar secondTime){
        if (firstTime.get(java.util.Calendar.HOUR_OF_DAY)<secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return true;
        if(firstTime.get(java.util.Calendar.HOUR_OF_DAY)==secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return firstTime.get(java.util.Calendar.MINUTE)<=secondTime.get(java.util.Calendar.MINUTE);
        return false;
    }


    //(Year)  firstDate<secondDate = true
    //(Year)  firstDate=secondDate   (Month) firstDate<secondDate = true
    // (Year)firstDate=secondDate (Month) firstDate=secondDate (Day) firstDate<secondDate = true
    public static boolean beforeDate(java.util.Calendar firstDate, java.util.Calendar secondDate){
        if (firstDate.get(java.util.Calendar.YEAR)<secondDate.get(java.util.Calendar.YEAR))
            return true;
        if (firstDate.get(java.util.Calendar.YEAR)==secondDate.get(java.util.Calendar.YEAR))
            if (firstDate.get(java.util.Calendar.MONTH)<secondDate.get(java.util.Calendar.MONTH))
                return true;
        if (firstDate.get(java.util.Calendar.YEAR)==secondDate.get(java.util.Calendar.YEAR))
            if(firstDate.get(java.util.Calendar.MONTH)==secondDate.get(java.util.Calendar.MONTH))
                return firstDate.get(java.util.Calendar.DAY_OF_MONTH)<secondDate.get(java.util.Calendar.DAY_OF_MONTH);
        return false;
    }

    //(Hour)  firstTime<secondTime = true
    //(Hour)  firstTime=secondTime   (Minutes) firstTime<secondTime = true
    public static boolean beforeTime(java.util.Calendar firstTime, java.util.Calendar secondTime){
        if(firstTime.get(java.util.Calendar.HOUR_OF_DAY)<secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return true;
        if(firstTime.get(java.util.Calendar.HOUR_OF_DAY)==secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return firstTime.get(java.util.Calendar.MINUTE)<secondTime.get(java.util.Calendar.MINUTE);

        return false;}

    // (Hour) firstTime>secondTime = true
    // (Hour)firstTime=secondTime (Minutes) firstTime>secondTime = true
    public static boolean afterTime(java.util.Calendar firsTime, java.util.Calendar secondTime){
        if (firsTime.get(java.util.Calendar.HOUR_OF_DAY)>secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return true;
        if(firsTime.get(java.util.Calendar.HOUR_OF_DAY)==secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return firsTime.get(java.util.Calendar.MINUTE)>secondTime.get(java.util.Calendar.MINUTE);
        return false;
    }

    // (Hour) firstTime>secondTime = true
    // (Hour) firstTime=secondTime (Minutes)firstTime>=secondTime = true
    public static boolean afterSameTime(java.util.Calendar firstTime, java.util.Calendar secondTime){
        if (firstTime.get(java.util.Calendar.HOUR_OF_DAY)>secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return true;
        if(firstTime.get(java.util.Calendar.HOUR_OF_DAY)==secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return firstTime.get(java.util.Calendar.MINUTE)>=secondTime.get(java.util.Calendar.MINUTE);
        return false;}


    public static int compareTime(java.util.Calendar firstTime, java.util.Calendar secondTime) {
        if (firstTime.get(java.util.Calendar.HOUR_OF_DAY) == secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            if (firstTime.get(java.util.Calendar.MINUTE) == secondTime.get(java.util.Calendar.MINUTE))
                return 0;
            else if (firstTime.get(java.util.Calendar.MINUTE) > secondTime.get(java.util.Calendar.MINUTE))
                return 1;
            else return -1;
        else if (firstTime.get(java.util.Calendar.HOUR_OF_DAY) > secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return 1;
        else return -1;

    }
// (Day,Month,Year) 1rstDate=2ndDate = 0 / (Day) 1rstDate>2ndDate =1  / (Day) 2ndDate>1rstDate= -1; /
// (Month) 1rstDate>2ndDate =1 /  2ndDate>1rstDate= -1;
// (Year) 1rstDate>2ndDate =1 / 1rstDate>2ndDate =1


    public static int compareDate(java.util.Calendar firstDate, java.util.Calendar secondDate){
        if (firstDate.get(java.util.Calendar.YEAR)==secondDate.get(java.util.Calendar.YEAR))
            if (firstDate.get(java.util.Calendar.MONTH)==secondDate.get(java.util.Calendar.MONTH))
                if (firstDate.get(java.util.Calendar.DAY_OF_MONTH)==secondDate.get(java.util.Calendar.DAY_OF_MONTH))
                    return  0;
                else  if (firstDate.get(java.util.Calendar.DAY_OF_MONTH)>secondDate.get(java.util.Calendar.DAY_OF_MONTH))
                    return 1;
                else
                    return -1;
            else if (firstDate.get(java.util.Calendar.MONTH)>secondDate.get(java.util.Calendar.MONTH))
                return 1;
            else
                return -1;
        else if (firstDate.get(java.util.Calendar.YEAR)>secondDate.get(java.util.Calendar.YEAR))
            return 1;
        else
            return -1;

    }


    // firstTime=secondTime (Hour & Minutes) = true
    public static  boolean sameTime(java.util.Calendar firstTime, java.util.Calendar secondTime){
        if (firstTime.get(java.util.Calendar.HOUR_OF_DAY)==secondTime.get(java.util.Calendar.HOUR_OF_DAY))
            return firstTime.get(java.util.Calendar.MINUTE)==secondTime.get(java.util.Calendar.MINUTE);
        return false;
    }


    public static boolean sameDate(java.util.Calendar firstDate, java.util.Calendar secondDate){
        if(firstDate.get(java.util.Calendar.YEAR)==secondDate.get(java.util.Calendar.YEAR))
            if (firstDate.get(java.util.Calendar.MONTH)==secondDate.get(java.util.Calendar.MONTH))
                return firstDate.get(java.util.Calendar.DAY_OF_MONTH)==secondDate.get(java.util.Calendar.DAY_OF_MONTH);
        return false;
    }
}
