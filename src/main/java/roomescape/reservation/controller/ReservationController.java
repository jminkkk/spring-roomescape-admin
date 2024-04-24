package roomescape.reservation.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.reservation.controller.request.CreateReservationRequest;
import roomescape.reservation.controller.response.FindReservationResponse;
import roomescape.reservation.domain.ReservationDomain;
import roomescape.reservation.service.ReservationService;
import roomescape.reservationtime.domain.ReservationTimeDomain;
import roomescape.reservationtime.service.ReservationTimeService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationTimeService reservationTimeService;

    public ReservationController(final ReservationService reservationService,
                                 final ReservationTimeService reservationTimeService) {
        this.reservationService = reservationService;
        this.reservationTimeService = reservationTimeService;
    }

    @GetMapping
    public ResponseEntity<List<FindReservationResponse>> getReservations() {
        Map<Entry<Long, ReservationDomain>, Entry<Long, ReservationTimeDomain>> reservations = reservationService.getReservations();
        List<FindReservationResponse> findReservationResponses = reservations.entrySet().stream()
                .map(entry -> FindReservationResponse.of(entry.getKey(), entry.getValue()))
                .toList();
        return ResponseEntity.ok(findReservationResponses);
    }

    @PostMapping
    public ResponseEntity<Void> createReservation(
            @RequestBody CreateReservationRequest createReservationRequest) {
        // TODO: Controller -> 한개의 Service, // Service에서 다른 Service
        ReservationTimeDomain reservationTimeDomain = reservationTimeService.getReservationTime(
                createReservationRequest.timeId());
        Long reservationId = reservationService.createReservation(
                createReservationRequest.toDomain(reservationTimeDomain), createReservationRequest.timeId());
        return ResponseEntity.created(URI.create(
                "/reservations/" + reservationId)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
