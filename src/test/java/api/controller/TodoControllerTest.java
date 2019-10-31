package api.controller;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import api.model.Todo;
import api.persistance.TodoRepository;

public class TodoControllerTest {
	
	@Mock
	private TodoRepository todoRepository;
	
	@InjectMocks
	private TodoController controller;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void putTodo_SavesTodoToRepositoryAndReturnsTheResult() {
    	Todo outputTodo = new Todo(101, "Buy milk", false);
    	Todo inputTodo = new Todo("Buy milk", false);
    	
    	when(todoRepository.createTodo(inputTodo)).thenReturn(outputTodo);
    	
    	Todo result = this.controller.putTodo(inputTodo);
    	assertThat(result).isSameInstanceAs(outputTodo);
    }
    
    @Test
    public void getAllTodos_ReturnsAllTodosFromTheDAO() {
    	List<Todo> allTodos = List.of(new Todo("todo_1", false), new Todo("todo_2", true));
		when(todoRepository.findAll()).thenReturn(allTodos);
    	List<Todo> result = this.controller.getAllTodos();
    	assertThat(result).isSameInstanceAs(allTodos);
    }
}
