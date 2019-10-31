package api.persistance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import api.model.Todo;

@Repository
public class TodoRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;  
	
	public List<Todo> findAll() {
		return template.query("SELECT * FROM todo", new TodoRowMapper());
	}

}


