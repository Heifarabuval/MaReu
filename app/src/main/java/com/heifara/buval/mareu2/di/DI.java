package com.heifara.buval.mareu2.di;

import com.heifara.buval.mareu2.model.Meet;
import com.heifara.buval.mareu2.service.DummyMeetGenerator;
import com.heifara.buval.mareu2.service.MeetApiService;
import com.heifara.buval.mareu2.service.MeetApiServiceException;

import java.util.List;

/**
 * Dependency injector to get instance of services
 */
public class DI {
    public static  MeetApiService service = new DummyMeetGenerator();

    /**
     * Get an instance of {@link MeetApiService}
     * @return
     */
    public static  MeetApiService getMeetApiService(){return service;}

    public  static void  initMeetApiService (List<String> rooms, List<Meet> meets) throws MeetApiServiceException{

        service = new DummyMeetGenerator();
        service.delAllRooms();

        for (String room: rooms)
            service.addRoom(room);
        for (Meet meet: meets)
            service.createMeet(meet);
    }
}


