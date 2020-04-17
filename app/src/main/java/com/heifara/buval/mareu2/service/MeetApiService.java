package com.heifara.buval.mareu2.service;

import com.heifara.buval.mareu2.model.Meet;

import java.util.Calendar;
import java.util.List;

/**
 * Meet API client
 */
public interface MeetApiService {
    /**
     * Get all the Rooms
     *
     * @return {@link List}
     */
    List<String> getRooms();




    /**
     * Get all my meets
     *
     * @return {@link List}
     */
    List<Meet> getMeets(Calendar date, String roomName);

    List<Meet> getMeetsList();

    /**
     * Deletes a meet
     *
     * @param meet
     */
    void deleteMeet(Meet meet);


    /**
     * Create Meet
     *
     * @param meet
     */
    void createMeet(Meet meet) throws MeetApiServiceException;

}
