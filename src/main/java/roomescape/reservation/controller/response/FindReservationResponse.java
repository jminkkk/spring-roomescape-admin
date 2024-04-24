package roomescape.reservation.controller.response;

import java.time.LocalDate;
import java.util.Map.Entry;
import roomescape.reservation.domain.ReservationDomain;
import roomescape.reservationtime.domain.ReservationTimeDomain;

public record FindReservationResponse(Long id, String name, LocalDate date, FindTimeOfReservationsResponse time) {
    public static FindReservationResponse of(final Entry<Long, ReservationDomain> reservationEntry,
                                             final Entry<Long, ReservationTimeDomain> reservatinoTimeEntry) {
        return new FindReservationResponse(
                reservationEntry.getKey(),
                reservationEntry.getValue().getName().getValue(),
                reservationEntry.getValue().getDate().getValue(),
                FindTimeOfReservationsResponse.of(reservatinoTimeEntry));
    }
}
