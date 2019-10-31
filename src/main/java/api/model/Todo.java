package api.model;

public class Todo {
	
	private Number id;
	private String title;
	private boolean completed;
	
	public Todo() {}
	
	public Todo(String title, boolean completed) {
		this.title = title;
		this.completed = completed;
	}
	
	public Todo(Number id, String title, boolean completed) {
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

	public boolean isCompleted() {
		return this.completed;
	}
	
}
