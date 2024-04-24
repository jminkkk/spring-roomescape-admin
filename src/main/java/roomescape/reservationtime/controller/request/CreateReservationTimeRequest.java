package roomescape.reservationtime.controller.request;

import java.time.LocalTime;
import roomescape.reservationtime.domain.ReservationTimeDomain;

public record CreateReservationTimeRequest(LocalTime startAt) {
    public ReservationTimeDomain toDomain() {
        return new ReservationTimeDomain(startAt);
    }
}
