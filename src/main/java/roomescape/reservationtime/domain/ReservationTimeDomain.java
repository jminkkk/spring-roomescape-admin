package roomescape.reservationtime.domain;

import java.time.LocalTime;

public class ReservationTimeDomain {
    private final LocalTime time;

    public ReservationTimeDomain(final LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }
}
