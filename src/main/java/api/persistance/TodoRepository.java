package api.persistance;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import api.model.Todo;

@Repository
public class TodoRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;  
	
	public List<Todo> findAll() {
		return template.query("SELECT * FROM Todo", new TodoRowMapper());
	}
	
	public List<Todo> findAllCompleted() {
		return template.query("SELECT * FROM Todo WHERE completed = True", new TodoRowMapper());
	}

	public Todo createTodo(Todo inputTodo) {
		String INSERT_SQL = "INSERT into Todo (title, completed) values(:title, :completed)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource data = new BeanPropertySqlParameterSource(inputTodo);
		template.update(INSERT_SQL, data, keyHolder, new String[] {"id"});
		 
	    return new Todo(keyHolder.getKey(), inputTodo.getTitle(), inputTodo.isCompleted());
	}

	public Todo findTodoById(long id) {
		Todo result;
		try {
			result = template.queryForObject("SELECT * FROM Todo WHERE id = :id", Map.of("id", id), new TodoRowMapper());
		}
		catch (IncorrectResultSizeDataAccessException e) {
			result = null;
		}
		return result;
	}



}


