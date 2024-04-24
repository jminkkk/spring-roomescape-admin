package roomescape.reservationtime.service;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import roomescape.reservationtime.domain.ReservationTimeDomain;
import roomescape.reservationtime.repository.ReservationTimeEntity;
import roomescape.reservationtime.repository.ReservationTimeRepository;

@Service
public class ReservationTimeService {
    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeService(final ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public Long createReservationTime(final ReservationTimeDomain reservationTimeDomain) {
        ReservationTimeEntity reservationTime = new ReservationTimeEntity(null, reservationTimeDomain.getTime());
        return reservationTimeRepository.save(reservationTime);
    }

    public ReservationTimeDomain getReservationTime(final Long id) {
        ReservationTimeEntity reservationTimeEntity = reservationTimeRepository.findById(id);
        return reservationTimeEntity.toDomain();
    }

    public Map<Long, ReservationTimeDomain> getReservationTimes() {
        return reservationTimeRepository.findAll().stream()
                .collect(Collectors.toMap(
                        ReservationTimeEntity::getId,
                        ReservationTimeEntity::toDomain));
    }

    public void deleteReservationTime(final Long id) {
        reservationTimeRepository.deleteById(id);
    }
}

// 1. Service는 Entity로 받아야 할까?
// 2. Service는 Domain으로 받아 Entity로 변경해야 할까? -> 일단 이걸로
