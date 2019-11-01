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
import org.springframework.web.bind.annotation.ResponseBody;
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
		if(q != null && q.equals("completed")) {
			return ResponseEntity.ok(todoRepository.findAllCompleted());
		}
		else {
			return ResponseEntity.ok(todoRepository.findAll());
		}

	}
	
	@GetMapping("{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable("id") long id) {
		Todo result = todoRepository.findTodoById(id);
		if(result != null) return ResponseEntity.ok(result);
		else return ResponseEntity.notFound().build();
	}

	@PutMapping
	public ResponseEntity<Todo> putTodo(@RequestBody Todo newTodo) {
		if(newTodo.getTitle() != null && newTodo.getTitle().length() > 0) {
			Todo saved = todoRepository.createTodo(newTodo);
			return ResponseEntity.ok(saved); 
		} 
		else {
			return ResponseEntity.unprocessableEntity().body(null);
		}
		
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<Todo> putTodo(@PathVariable("id") long id, @RequestBody Todo updatedTodo) {
			Todo saved = todoRepository.updateTodo(id, updatedTodo);
			return ResponseEntity.ok(saved); 
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Long>> deleteTodo(@PathVariable("id") long id) {
		boolean todoWasDeleted = todoRepository.deleteTodoById(id);
		if(todoWasDeleted) return ResponseEntity.ok(Map.of("id", id));
		else return ResponseEntity.notFound().build();

	}
}
