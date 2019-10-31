package api.controller;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

import api.model.Todo;

public class TodoControllerTest {
	
	private TodoController controller;

	@Before
	public void setup() {
		this.controller = new TodoController();
	}

    @Test
    public void putTodo_CreatesNewTodoAndReturnsFullObject() {
    	Todo result = this.controller.putTodo("Buy milk", false);
        assertThat(result.getTitle()).isEqualTo("Buy milk");
        assertThat(result.isCompleted()).isFalse();
    }
}
