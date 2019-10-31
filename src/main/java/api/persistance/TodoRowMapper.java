package api.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import api.model.Todo;

public class TodoRowMapper implements RowMapper<Todo> {

	@Override
	public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Todo(rs.getLong("id"), rs.getString("title"), rs.getBoolean("completed"));
	}

}
