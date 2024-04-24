package roomescape.reservation.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;
import roomescape.reservation.controller.request.CreateReservationRequest;
import roomescape.reservation.controller.response.FindReservationResponse;
import roomescape.reservation.repository.ReservationEntity;
import roomescape.reservation.repository.ReservationRepository;
import roomescape.reservationtime.domain.ReservationTimeDomain;
import roomescape.reservationtime.repository.ReservationTimeEntity;
import roomescape.reservationtime.repository.ReservationTimeRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationService(final ReservationRepository reservationRepository,
                              final ReservationTimeRepository reservationTimeRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public List<FindReservationResponse> getReservations() {
        Map<ReservationEntity, ReservationTimeEntity> reservationEntities = reservationRepository.findAll();
        return reservationEntities.entrySet().stream()
                .map(entry -> FindReservationResponse.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    public Long createReservation(final CreateReservationRequest createReservationRequest) {
        ReservationTimeEntity reservationTimeEntity = reservationTimeRepository.findById(
                createReservationRequest.timeId());
        ReservationEntity reservationEntity = ReservationEntity.of(
                createReservationRequest.toDomain(reservationTimeEntity.toDomain()), createReservationRequest.timeId());
        return reservationRepository.save(reservationEntity);
    }

    public void deleteReservation(final Long id) {
        reservationRepository.deleteById(id);
    }
}
