package roomescape.reservationtime.repository;

import java.time.LocalTime;
import roomescape.reservationtime.domain.ReservationTimeDomain;

public class ReservationTimeEntity {
    private final Long id;
    private final LocalTime time;

    public ReservationTimeEntity(final Long id, final LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public ReservationTimeDomain toDomain() {
        return new ReservationTimeDomain(time); // TODO: Entity가 Domain을 알아도 될까???
    }
}
