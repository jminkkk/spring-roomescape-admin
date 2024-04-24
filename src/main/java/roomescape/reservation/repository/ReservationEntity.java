package roomescape.reservation.repository;

import java.time.LocalDate;
import roomescape.reservation.domain.ReservationDomain;
import roomescape.reservationtime.domain.ReservationTimeDomain;

public class ReservationEntity {
    private final Long id;
    private final String name;
    private final LocalDate date;
    private final Long timeId;

    public ReservationEntity(final Long id, final String name, final LocalDate date, final Long timeId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.timeId = timeId;
    }

    public static ReservationEntity of(final ReservationDomain reservationDomain, final Long timeId) {
        return new ReservationEntity(
                null,
                reservationDomain.getName().getValue(),
                reservationDomain.getDate().getValue(), // TODO: 괜춘??
                timeId
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getTimeId() {
        return timeId;
    }

    // TODO
    public ReservationDomain toDomain(final ReservationTimeDomain reservationTimeDomain) {
        return new ReservationDomain(name, date, reservationTimeDomain.getTime());
    }
}
