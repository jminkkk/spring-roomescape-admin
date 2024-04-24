package roomescape.reservation.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.reservationtime.domain.ReservationTimeDomain;

public class ReservationDomain {
    private final ReservationName name;
    private final ReservationDate date;
    private final ReservationTimeDomain reservationTime;

    public ReservationDomain(final String name, final LocalDate date, final LocalTime time) {
        this.name = new ReservationName(name);
        this.date = new ReservationDate(date);
        this.reservationTime = new ReservationTimeDomain(time);
    }

    public ReservationDomain(final String name, final LocalDate date, final ReservationTimeDomain reservationTime) {
        this.name = new ReservationName(name);
        this.date = new ReservationDate(date);
        this.reservationTime = reservationTime;
    }

    public ReservationName getName() {
        return name;
    }

    public ReservationDate getDate() {
        return date;
    }

    public ReservationTimeDomain getReservationTime() {
        return reservationTime;
    }
}
