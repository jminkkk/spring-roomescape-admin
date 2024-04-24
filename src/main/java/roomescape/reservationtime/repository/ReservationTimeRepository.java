package roomescape.reservationtime.repository;

import java.util.List;

public interface ReservationTimeRepository {

    Long save(ReservationTimeEntity reservationTime);

    List<ReservationTimeEntity> findAll();

    void deleteById(Long id);

    ReservationTimeEntity findById(Long timeId);
}
