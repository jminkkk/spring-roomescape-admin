package roomescape.reservation.controller.response;

import java.time.LocalDate;
import roomescape.reservation.repository.ReservationEntity;
import roomescape.reservationtime.repository.ReservationTimeEntity;

public record FindReservationResponse(Long id, String name, LocalDate date, FindTimeOfReservationsResponse time) {
    public static FindReservationResponse of(final ReservationEntity reservationEntry,
                                             final ReservationTimeEntity reservationTimeEntry) {
        return new FindReservationResponse(
                reservationEntry.getId(),
                reservationEntry.getName(),
                reservationEntry.getDate(),
                FindTimeOfReservationsResponse.of(reservationTimeEntry));
    }
}
