package com.heifara.buval.mareu2.utils;

public class Calendar {
    public static boolean checkTime(java.util.Calendar startTime, java.util.Calendar endTime, java.util.Calendar startTimeItemList,
                                    java.util.Calendar endTimeItemList) {
        if ((startTime.get(java.util.Calendar.HOUR_OF_DAY) == (startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))) ||
                (endTime.get(java.util.Calendar.HOUR_OF_DAY) == (endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))) {
            return (startTime.get(java.util.Calendar.MINUTE) == (startTimeItemList.get(java.util.Calendar.MINUTE))) ||
                    (endTime.get(java.util.Calendar.MINUTE) == (endTimeItemList.get(java.util.Calendar.MINUTE)));
        }
        return false;
    }

    public static boolean outRangeTime(java.util.Calendar startTime, java.util.Calendar endTime, java.util.Calendar startTimeItemList,
                                       java.util.Calendar endTimeItemList) {
        if (startTime.get(java.util.Calendar.HOUR_OF_DAY) < startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY) &&
                (endTime.get(java.util.Calendar.HOUR_OF_DAY) > endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))
            return true;

        if ((startTime.get(java.util.Calendar.HOUR_OF_DAY) == startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))
                && (endTime.get(java.util.Calendar.HOUR_OF_DAY) == endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))

            return startTime.get(java.util.Calendar.MINUTE) < startTimeItemList.get(java.util.Calendar.MINUTE) &&
                    endTime.get(java.util.Calendar.MINUTE) > endTimeItemList.get(java.util.Calendar.MINUTE);

        if ((startTime.get(java.util.Calendar.HOUR_OF_DAY) < startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))
                && (endTime.get(java.util.Calendar.HOUR_OF_DAY) == endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))

            return endTime.get(java.util.Calendar.MINUTE) > endTimeItemList.get(java.util.Calendar.MINUTE);

        if ((startTime.get(java.util.Calendar.HOUR_OF_DAY) == startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))
                && (endTime.get(java.util.Calendar.HOUR_OF_DAY) > endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))

            return startTime.get(java.util.Calendar.MINUTE) < endTimeItemList.get(java.util.Calendar.MINUTE);

        return false;
    }


    public static boolean inRangeTime(java.util.Calendar startTime, java.util.Calendar endTime, java.util.Calendar startTimeItemList,
                                      java.util.Calendar endTimeItemList) {
        if (startTime.get(java.util.Calendar.HOUR_OF_DAY) > startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY) &&
                (endTime.get(java.util.Calendar.HOUR_OF_DAY) < endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))
            return true;

        if ((startTime.get(java.util.Calendar.HOUR_OF_DAY) > startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))
                && (endTime.get(java.util.Calendar.HOUR_OF_DAY) < endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))

            return startTime.get(java.util.Calendar.MINUTE) > startTimeItemList.get(java.util.Calendar.MINUTE) &&
                    endTime.get(java.util.Calendar.MINUTE) > endTimeItemList.get(java.util.Calendar.MINUTE);

        return false;


    }

    public static boolean startInEndOut(java.util.Calendar startTime, java.util.Calendar startTimeItemList, java.util.Calendar endTimeItemList) {
        if (startTime.get(java.util.Calendar.HOUR_OF_DAY) > startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY) && //InputStartHour > TempStartHour && InputEndHour > TempEndHour
                (startTime.get(java.util.Calendar.HOUR_OF_DAY) < endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))
            return true;

        if ((startTime.get(java.util.Calendar.HOUR_OF_DAY) >= startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))//InputStartHour = TempStartHour && InputEndHour < TempEndHour
                && (startTime.get(java.util.Calendar.HOUR_OF_DAY) <= endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))

            return (startTime.get(java.util.Calendar.MINUTE) > startTimeItemList.get(java.util.Calendar.MINUTE) &&
                    startTime.before(endTimeItemList));

        return false;
    }


    public static boolean endInStartOut(java.util.Calendar endTime, java.util.Calendar startTimeItemList, java.util.Calendar endTimeItemList) {

        if (endTime.get(java.util.Calendar.HOUR_OF_DAY) > startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY) && //InputStartHour > TempStartHour && InputEndHour > TempEndHour
                (endTime.get(java.util.Calendar.HOUR_OF_DAY) <= endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))
            return true;

        if ((endTime.get(java.util.Calendar.HOUR_OF_DAY) >= startTimeItemList.get(java.util.Calendar.HOUR_OF_DAY))//InputStartHour = TempStartHour && InputEndHour < TempEndHour
                && (endTime.get(java.util.Calendar.HOUR_OF_DAY) <= endTimeItemList.get(java.util.Calendar.HOUR_OF_DAY)))

            return (endTime.get(java.util.Calendar.MINUTE) > startTimeItemList.get(java.util.Calendar.MINUTE) &&
                    endTime.before(endTimeItemList));

        return false;
    }


    public static boolean sameDate(java.util.Calendar firstDate, java.util.Calendar secondDate) {
        if (firstDate.get(java.util.Calendar.YEAR) == secondDate.get(java.util.Calendar.YEAR))
            if (firstDate.get(java.util.Calendar.MONTH) == secondDate.get(java.util.Calendar.MONTH))
                return firstDate.get(java.util.Calendar.DAY_OF_MONTH) == secondDate.get(java.util.Calendar.DAY_OF_MONTH);
        return false;
    }
}
