package com.heifara.buval.mareu2;

import com.heifara.buval.mareu2.di.DI;
import com.heifara.buval.mareu2.model.Meet;
import com.heifara.buval.mareu2.service.MeetApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class ExampleUnitTest {
    private MeetApiService meetApiService;
    @Before
    public void setup(){
        meetApiService = DI.getMeetApiService();
    }


    @Test
    public void addMeet() {
        List<Meet> meet = meetApiService.getMeetsList();

    }
}