package api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@ResponseBody
	public Todo putTodo(@RequestBody Todo newTodo) {
		return todoRepository.createTodo(newTodo);
	}

	@GetMapping
	@ResponseBody
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}
}
