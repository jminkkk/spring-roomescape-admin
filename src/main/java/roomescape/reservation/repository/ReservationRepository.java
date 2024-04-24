package roomescape.reservation.repository;

import java.util.Map;
import roomescape.reservationtime.repository.ReservationTimeEntity;

public interface ReservationRepository {

    Map<ReservationEntity, ReservationTimeEntity> findAll();

    ReservationEntity findById(Long id);

    Long save(ReservationEntity reservation);

    void deleteById(Long id);
}
