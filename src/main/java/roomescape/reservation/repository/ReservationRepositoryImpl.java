package roomescape.reservation.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.reservationtime.repository.ReservationTimeEntity;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReservationRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<ReservationEntity, ReservationTimeEntity> findAll() {
        String sql = """
                select r.id, r.name, r.date, t.id as time_id, t.start_at
                from reservation as r 
                inner join reservation_time as t 
                on r.time_id = t.id
                """;
        Map<ReservationEntity, ReservationTimeEntity> result = new HashMap<>();
        jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            ReservationEntity reservation = new ReservationEntity(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getDate("date").toLocalDate(),
                    resultSet.getLong("time_id")
            );
            ReservationTimeEntity time = new ReservationTimeEntity(
                    resultSet.getLong("time_id"),
                    resultSet.getTime("start_at").toLocalTime()
            );
            result.put(reservation, time);
            return result.entrySet().stream().iterator().next();
        });
        return result;
    }

    @Override
    public ReservationEntity findById(final Long id) {
        String sql = "select * from reservation where id = ?";
        return jdbcTemplate.queryForObject(sql, ReservationEntity.class, id);
    }

    @Override
    public Long save(final ReservationEntity reservationEntity) {
        String sql = "insert into reservation (name, date, time_id) values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, reservationEntity.getName());
            ps.setDate(2, Date.valueOf(reservationEntity.getDate()));
            ps.setLong(3, reservationEntity.getTimeId());
            return ps;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(final Long id) {
        String sql = "delete from reservation where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
