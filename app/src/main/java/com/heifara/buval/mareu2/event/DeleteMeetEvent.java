package com.heifara.buval.mareu2.event;

public class DeleteMeetEvent {
    private int meetId;

    public DeleteMeetEvent(int meetId) {
        this.meetId = meetId;
    }

    public int getMeetId() {
        return meetId;
    }


}
