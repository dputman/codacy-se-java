package api.controller;

import org.junit.Assert;
import org.junit.Test;

public class TodoControllerTest {

    @Test
    public void putTodoReturnsInputPlus1() {
        Assert.assertEquals(new TodoController().putTodo(1), 2);
    }
}
