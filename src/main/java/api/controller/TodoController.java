package api.controller;

import api.model.Todo;

public class TodoController {

	public Todo putTodo(String title, boolean completed) {
		return new Todo(title, completed);
	}
}
