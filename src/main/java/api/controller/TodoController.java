package api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.model.Todo;
import api.persistance.TodoRepository;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@GetMapping
	public ResponseEntity<List<Todo>> getAllTodos(@RequestParam(required=false) String q) {
		boolean shouldFilter = q != null && q.equals("completed");
		
		if(shouldFilter)
			return ResponseEntity.ok(todoRepository.findAllCompleted());
		else
			return ResponseEntity.ok(todoRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable("id") long id) {
		Todo todo = todoRepository.findTodoById(id);
		
		if(todo != null) 
			return ResponseEntity.ok(todo);
		else 
			return ResponseEntity.notFound().build();
	}

	@PutMapping
	public ResponseEntity<Todo> putTodo(@RequestBody Todo newTodo) {
		boolean titleIsValid = newTodo.getTitle() != null && newTodo.getTitle().length() > 0;
		
		if(titleIsValid) 
			return ResponseEntity.ok(todoRepository.createTodo(newTodo)); 
		else
			return ResponseEntity.unprocessableEntity().build();	
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<Todo> putTodo(@PathVariable("id") long id, @RequestBody Todo updatedTodo) {
			boolean shouldUpdateTitle = (updatedTodo.getTitle() != null && updatedTodo.getTitle().length() > 0);
			boolean shouldUpdateCompleted = updatedTodo.isCompleted() != null;
			
			if(shouldUpdateTitle || shouldUpdateCompleted) {
				boolean success = todoRepository.updateTodo(id, updatedTodo);
				if(success)
					return ResponseEntity.ok(todoRepository.findTodoById(id));
				else
					return ResponseEntity.notFound().build(); 
			}
			return ResponseEntity.unprocessableEntity().build(); 
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Long>> deleteTodo(@PathVariable("id") long id) {
		boolean todoWasDeleted = todoRepository.deleteTodoById(id);
		
		if(todoWasDeleted) 
			return ResponseEntity.ok(Map.of("id", id));
		else 
			return ResponseEntity.notFound().build();

	}
}
