package api.controller;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import api.model.Todo;
import api.persistance.TodoRepository;

public class TodoControllerTest {
	
	@Mock
	private TodoRepository todoRepository;
	
	@InjectMocks
	private TodoController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void putTodoSavesTodoToRepositoryAndReturnsTheResult() {
    	Todo outputTodo = new Todo(101, "Buy milk", false);
    	Todo inputTodo = new Todo("Buy milk", false);
    	
    	when(todoRepository.createTodo(inputTodo)).thenReturn(outputTodo);
    	
    	Todo result = this.controller.putTodo(inputTodo).getBody();
    	assertThat(result).isSameInstanceAs(outputTodo);
    }
    
    @Test
    public void putTodoReturnsHttpOkStatusOnSuccess() {
    	Todo outputTodo = new Todo(101, "Buy milk", false);
    	Todo inputTodo = new Todo("Buy milk", false);
    	
    	when(todoRepository.createTodo(inputTodo)).thenReturn(outputTodo);
    	
    	HttpStatus status = this.controller.putTodo(inputTodo).getStatusCode();
    	assertThat(status).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    public void putTodoRequiresATitleAndReturnsUnprocessableEntityStatus() {
    	Todo inputTodo = new Todo("", false);
    	
    	HttpStatus status = this.controller.putTodo(inputTodo).getStatusCode();
    	assertThat(status).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @Test
    public void getAllTodosReturnsAllTodosFromTheRepository() {
    	List<Todo> allTodos = List.of(new Todo("todo_1", false), new Todo("todo_2", true));
		when(todoRepository.findAll()).thenReturn(allTodos);
    	List<Todo> result = this.controller.getAllTodos(null).getBody();
    	assertThat(result).isSameInstanceAs(allTodos);
    }
}
