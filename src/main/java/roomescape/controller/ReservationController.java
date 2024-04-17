package roomescape.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.domain.Reservation;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<FindReservationResponse>> getReservation() {
        List<FindReservationResponse> reservationsDtos = reservations.stream()
                .map(FindReservationResponse::of)
                .toList();
        return ResponseEntity.ok(reservationsDtos);
    }

    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(
            @RequestBody CreateReservationRequest createReservationRequest) {
        Reservation newReservation = createReservationRequest.to(index.getAndIncrement());
        reservations.add(newReservation);
        return ResponseEntity.ok()
                .body(CreateReservationResponse.of(newReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservations.removeIf(reservation -> Objects.equals(reservation.getId(), id));
        return ResponseEntity.ok().build();
    }
}