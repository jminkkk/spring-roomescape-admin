package roomescape.reservation.controller.response;

import java.util.Map.Entry;
import roomescape.reservationtime.domain.ReservationTimeDomain;
import roomescape.util.CustomDateTimeFormatter;

public record FindTimeOfReservationsResponse(Long id, String startAt) {
    public static FindTimeOfReservationsResponse of(final Entry<Long, ReservationTimeDomain> reservationTimeDomainEntry) {
        return new FindTimeOfReservationsResponse(
                reservationTimeDomainEntry.getKey(),
                CustomDateTimeFormatter.getFormattedTime(reservationTimeDomainEntry.getValue().getTime())
        );
    }
}
