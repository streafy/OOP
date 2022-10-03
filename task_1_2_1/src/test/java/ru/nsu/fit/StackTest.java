package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTest {

    @Test
    void testPop() {
        Stack<String> stack = new Stack<>();
        stack.push("stack element 1");
        stack.push("stack element 2");
        stack.push("stack element 3");

        Assertions.assertEquals("stack element 3", stack.pop());
    }

    @Test
    void testPopStack() {
        Stack<String> stack = new Stack<>(new String[]{"str1", "str2", "str3"});

        stack.popStack(2);
        Assertions.assertEquals("str1", stack.pop());
    }

    @Test
    void testResize() {
        Stack<String> stack = new Stack<>(1);
        Stack<String> anotherStack = new Stack<>(new String[]{"1", "2"});
        stack.pushStack(anotherStack);

        Assertions.assertEquals(2, stack.count());
        Assertions.assertEquals("2", stack.pop());
    }

    @Test()
    void testExceptions() {
        Stack<String> stack = new Stack<>(new String[]{"1", "2"});
        Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> stack.popStack(3)
        );
        Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> stack.popStack(-1)
        );

        Stack<String> emptyStack = new Stack<>();
        Assertions.assertThrowsExactly(
                IllegalStateException.class,
                emptyStack::pop
        );
    }
}
