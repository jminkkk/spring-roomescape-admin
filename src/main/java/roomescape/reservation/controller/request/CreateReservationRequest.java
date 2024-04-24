package roomescape.reservation.controller.request;

import java.time.LocalDate;
import roomescape.reservation.domain.ReservationDomain;
import roomescape.reservationtime.domain.ReservationTimeDomain;

public record CreateReservationRequest(LocalDate date, String name, Long timeId) {

    public ReservationDomain toDomain(final ReservationTimeDomain reservationTime) {
        return new ReservationDomain(
                this.name,
                this.date,
                reservationTime.getTime()
        );
    }
}
