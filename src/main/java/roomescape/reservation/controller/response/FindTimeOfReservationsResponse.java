package roomescape.reservation.controller.response;

import roomescape.reservationtime.domain.ReservationTime;
import roomescape.util.CustomDateTimeFormatter;

public record FindTimeOfReservationsResponse(Long id, String startAt) {
    public static FindTimeOfReservationsResponse of(final ReservationTime reservationTime) {
        return new FindTimeOfReservationsResponse(
                reservationTime.getId(),
                CustomDateTimeFormatter.getFormattedTime(reservationTime.getTime())
        );
    }
}
