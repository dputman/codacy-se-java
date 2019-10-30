package api.controller;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Assert;
import org.junit.Test;

public class TodoControllerTest {

    @Test
    public void putTodoReturnsInputPlus1() {
        assertThat("awesome").startsWith("awe");
        Assert.assertEquals(new TodoController().putTodo(1), 2);
    }
}
