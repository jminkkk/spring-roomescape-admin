package roomescape.reservationtime.controller.response;

import roomescape.reservationtime.repository.ReservationTimeEntity;
import roomescape.util.CustomDateTimeFormatter;

public record FindReservationTimeResponse(Long id, String startAt) {
    public static FindReservationTimeResponse of(final ReservationTimeEntity reservationTimeEntity) {
        return new FindReservationTimeResponse(
                reservationTimeEntity.getId(),
                CustomDateTimeFormatter.getFormattedTime(reservationTimeEntity.getTime()));
    }
}
