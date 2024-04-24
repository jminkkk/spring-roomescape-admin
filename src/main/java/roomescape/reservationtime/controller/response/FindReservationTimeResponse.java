package roomescape.reservationtime.controller.response;

import java.util.Map.Entry;
import roomescape.reservationtime.domain.ReservationTimeDomain;
import roomescape.util.CustomDateTimeFormatter;

public record FindReservationTimeResponse(Long id, String startAt) {
    public static FindReservationTimeResponse of(final Entry<Long, ReservationTimeDomain> reservationTimeDomainEntry) {
        return new FindReservationTimeResponse(
                reservationTimeDomainEntry.getKey(),
                CustomDateTimeFormatter.getFormattedTime(reservationTimeDomainEntry.getValue().getTime()));
    }
}
