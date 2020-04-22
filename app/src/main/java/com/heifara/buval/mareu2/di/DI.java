package com.heifara.buval.mareu2.di;

import com.heifara.buval.mareu2.service.DummyMeetApiService;
import com.heifara.buval.mareu2.service.MeetApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {
    private static final MeetApiService service = new DummyMeetApiService();

    /**
     * Get an instance of {@link MeetApiService}
     *
     * @return service
     */
    public static MeetApiService getMeetApiService() {
        return service;
    }


}


