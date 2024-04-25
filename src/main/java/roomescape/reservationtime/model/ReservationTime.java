package roomescape.reservationtime.model;

import java.time.LocalTime;

public class ReservationTime {
    private final Long id;
    private final LocalTime time;

    public ReservationTime(final Long id, final LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }
}