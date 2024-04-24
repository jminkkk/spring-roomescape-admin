package roomescape.reservation.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import roomescape.reservation.domain.ReservationDomain;
import roomescape.reservation.repository.ReservationEntity;
import roomescape.reservation.repository.ReservationRepository;
import roomescape.reservationtime.domain.ReservationTimeDomain;
import roomescape.reservationtime.repository.ReservationTimeEntity;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Map<Entry<Long, ReservationDomain>, Entry<Long, ReservationTimeDomain>> getReservations() {
        Map<ReservationEntity, ReservationTimeEntity> reservationEntities = reservationRepository.findAll();
        return reservationEntities.entrySet().stream()
                .map(entry -> {
                    ReservationTimeDomain reservationTimeDomain = entry.getValue().toDomain();
                    Entry<Long, ReservationDomain> reservation = Map.of(entry.getKey().getId(),
                                    entry.getKey().toDomain(reservationTimeDomain)).entrySet().iterator().next();
                    Entry<Long, ReservationTimeDomain> reservationTime = Map.of(entry.getValue().getId(),
                                    entry.getValue().toDomain()).entrySet().iterator().next();

                    return Map.of(reservation, reservationTime).entrySet().iterator().next();
                }).collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Long createReservation(final ReservationDomain reservationDomain, final Long timeId) {
        ReservationEntity reservationEntity = ReservationEntity.of(reservationDomain, timeId);
        return reservationRepository.save(reservationEntity);
    }

    public void deleteReservation(final Long id) {
        reservationRepository.deleteById(id);
    }
}
