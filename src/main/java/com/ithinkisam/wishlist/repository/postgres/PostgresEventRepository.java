package com.ithinkisam.wishlist.repository.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ithinkisam.wishlist.domain.Event;
import com.ithinkisam.wishlist.repository.EventRepository;

@Repository("postgresEventRepository")
public class PostgresEventRepository extends AbstractPostgresRepository implements EventRepository {

	@Override
	public Event findById(int id) {
		String sql = "select * from events where eid = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id);
		
		try {
			return jdbcTemplate.queryForObject(sql, parameters, PostgresEventMapper.DEFAULT);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Event> findByAdministration(String username) {
		String sql = "select * from events where admin = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		return jdbcTemplate.query(sql, parameters, PostgresEventMapper.DEFAULT);
	}

	@Override
	public List<Event> findByMember(String username) {
		String sql = "select * from events "
				+ "where exists ("
					+ "select * "
					+ "from event_members "
					+ "where event_members.eid = events.eid "
					+ "and event_members.member = :username "
					+ "and confirmed is not null"
				+ ")";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		return jdbcTemplate.query(sql, parameters, PostgresEventMapper.DEFAULT);
	}
	
	@Override
	public List<String> findMembers(int eventId) {
		String sql = "select member from event_members where eid = :eventId";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("eventId", eventId);
		
		return jdbcTemplate.query(sql, parameters, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int i) throws SQLException {
				return rs.getString("member");
			}
		});
	}

	@Override
	public List<Event> findEventInvitations(String username) {
		String sql = "select * from events "
				+ "where exists ("
					+ "select * "
					+ "from event_members "
					+ "where event_members.eid = events.eid "
					+ "and event_members.member = :username "
					+ "and confirmed is null"
				+ ")";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("username", username);
		
		return jdbcTemplate.query(sql, parameters, PostgresEventMapper.DEFAULT);
	}
	
	@Override
	public Event save(Event event) {
		String sql = "insert into events (name, description, date, admin) "
				+ "values (:name, :description, :date, :admin)";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("name", event.getName())
				.addValue("description", event.getDescription())
				.addValue("date", Timestamp.valueOf(event.getDate()))
				.addValue("admin", event.getAdmin().getUsername());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql, parameters, keyHolder, new String[] { "eid" });
		return findById(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Event event) {
		String sql = "update events set "
				+ "name = :name, "
				+ "description = :description, "
				+ "date = :date, "
				+ "where eid = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("name", event.getName())
				.addValue("description", event.getDescription())
				.addValue("date", Timestamp.valueOf(event.getDate()))
				.addValue("eid", event.getId());
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void remove(int id) {
		String sql = "delete from events where eid = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void addMember(int id, String username) {
		String sql = "insert into event_members (eid, member) "
				+ "values (:eventId, :username)";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("eventId", id)
				.addValue("username", username);
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void removeMember(int id, String username) {
		String sql = "delete from event_members where eid = :eventId and member = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("eventId", id)
				.addValue("username", username);
		
		jdbcTemplate.update(sql, parameters);
	}

	@Override
	public void confirmMembership(int id, String username) {
		String sql = "update event_members "
				+ "set confirmed = localtimestamp "
				+ "where eid = :eventId and member = :username";
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("eventId", id)
				.addValue("username", username);
		
		jdbcTemplate.update(sql, parameters);
	}

}
