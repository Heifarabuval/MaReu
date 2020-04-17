package com.heifara.buval.mareu2.event;

import com.heifara.buval.mareu2.model.Meet;

public class DeleteMeetEvent {
    public final Meet meet;

    public DeleteMeetEvent(Meet meet) {
        this.meet = meet;
    }


}
