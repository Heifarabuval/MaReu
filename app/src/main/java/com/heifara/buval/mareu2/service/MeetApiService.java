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
     * @return {@link List}
     */
    List<String> getRooms();


    /**
     * Create a room
     * @param room
     */
    void addRoom(String room);

    /**
     * Delete a Room
     * @param room
     */
    void delRoom(String room);


    /**
     * Delete all Room
     */
    void delAllRooms();


    /**
     * Get all my meets
     * @return {@link List}
     */
    List<Meet> getMeets(Calendar date,String roomName);


    /**
     *  Deletes a meet
     * @param idMeet
     */
    void deleteMeet(Integer idMeet);


    /**
     * Create Meet
     * @param  meet
     */
    void createMeet (Meet meet) throws MeetApiServiceException ;

}
