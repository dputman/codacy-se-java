package api.model;

public class Todo {
	
	private long id;
	private String title;
	private boolean completed;

	public Todo(String title, boolean completed) {
		this.title = title;
		this.completed = completed;
	}
	
	public Todo(long id, String title, boolean completed) {
		this.id = id;
		this.title = title;
		this.completed = completed;
	}
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean isCompleted() {
		return this.completed;
	}
	
}
