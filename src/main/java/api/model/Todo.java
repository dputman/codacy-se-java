package api.model;

public class Todo {
	
	private Number id;
	private String title;
	private Boolean completed;
	
	public Todo() {}
	
	public Todo(String title, Boolean completed) {
		this.title = title;
		this.completed = completed;
	}
	
	public Todo(Number id, String title, Boolean completed) {
		this.id = id;
		this.title = title;
		this.completed = completed;
	}
	
	public Number getId() {
		return id;
	}

	public String getTitle() {
		return this.title;
	}

	public Boolean isCompleted() {
		return this.completed;
	}
	
}
