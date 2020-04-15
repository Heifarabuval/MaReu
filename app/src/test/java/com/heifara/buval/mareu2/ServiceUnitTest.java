package com.heifara.buval.mareu2;

import android.graphics.Color;

import com.heifara.buval.mareu2.di.DI;
import com.heifara.buval.mareu2.model.Meet;
import com.heifara.buval.mareu2.service.DummyMeetGenerator;
import com.heifara.buval.mareu2.service.MeetApiService;
import com.heifara.buval.mareu2.service.MeetApiServiceException;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceUnitTest {

    private MeetApiService meetApiService;
    @Before
    public void setup(){
        meetApiService = DI.getMeetApiService();
    }

@Test
public void dDeleteMeetWithSuccess(){

    Meet meetToDelete= meetApiService.getMeetsList().get(0);
        meetApiService.deleteMeet(meetToDelete);
    assertFalse("Impossible de supprimer un voisin de la liste contacts",meetApiService.getMeetsList().contains(meetToDelete));
}

    @Test
    public void aGetMeetsWithSuccess() {
        List<Meet> meets = meetApiService.getMeetsList();
        List<Meet> expectedNeighbours = DummyMeetGenerator.DUMMY_MEETS;
        assertThat(meets, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void bFilter(){
        String roomName = "Room 1";
        String dateStart= "31-08-2020 10:00:00";
        Calendar dateStartCal1 = convertStringToDate(dateStart);
      List<Meet> staticMeet = DummyMeetGenerator.DUMMY_MEETS;
        List<Meet> meets = meetApiService.getMeets(dateStartCal1,"Room 1");
        List<Meet> expectedMeet= new ArrayList<>();

        for (int i = 0; i <staticMeet.size() ; i++) {

            String tempRoomName = staticMeet.get(i).getRoomName();

            if (tempRoomName.matches(roomName))
            { expectedMeet.add(staticMeet.get(i));
            }
            assertTrue("Les listes ne correspondent pas",meets.containsAll(expectedMeet));
        }


    }



    @Test
    public void cAddMeet() {
 String dateStart= "31-07-2020 10:00:00";
 String dateEnd= "31-07-2020 12:00:00";
        List<String> emailList= Arrays.asList("aaa@ddd.com","bbb@ggg.com");
        Calendar dateStartCal1 = convertStringToDate(dateStart);
         Calendar dateEndCal2 = convertStringToDate(dateEnd);
        Meet meetToAdd = new Meet("Room 1",dateStartCal1,dateStartCal1,dateEndCal2,emailList,"voila", Color.parseColor("#D054E3"));
        try {
            meetApiService.createMeet(meetToAdd);
            assertTrue("Impossible de créer et d'ajouter une réunion",meetApiService.getMeets(dateStartCal1,"Room 1").contains(meetToAdd));
        } catch (MeetApiServiceException e) {
            e.printStackTrace();
        }

    }


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
}