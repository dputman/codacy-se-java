package api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.model.Todo;
import api.persistance.TodoRepository;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;

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

	@GetMapping
	@ResponseBody
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}
}
