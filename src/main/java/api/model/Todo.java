package api.model;

public class Todo {

	private String title;
	private boolean completed;

	public Todo(String title, boolean completed) {
		this.title = title;
		this.completed = completed;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean isCompleted() {
		return this.completed;
	}
	
}
