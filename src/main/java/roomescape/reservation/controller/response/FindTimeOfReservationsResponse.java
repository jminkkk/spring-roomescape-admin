package roomescape.reservation.controller.response;

import roomescape.reservationtime.repository.ReservationTimeEntity;
import roomescape.util.CustomDateTimeFormatter;

public record FindTimeOfReservationsResponse(Long id, String startAt) {
    public static FindTimeOfReservationsResponse of(final ReservationTimeEntity reservationTimeDomainEntry) {
        return new FindTimeOfReservationsResponse(
                reservationTimeDomainEntry.getId(),
                CustomDateTimeFormatter.getFormattedTime(reservationTimeDomainEntry.getTime())
        );
    }
}
