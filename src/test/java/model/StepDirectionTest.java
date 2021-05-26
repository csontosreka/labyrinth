package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepDirectionTest {

    @Test
    void testOf() {
        assertEquals(StepDirection.UP, StepDirection.of(-2, 0));
        assertEquals(StepDirection.RIGHT, StepDirection.of(0, 2));
        assertEquals(StepDirection.DOWN, StepDirection.of(2, 0));
        assertEquals(StepDirection.LEFT, StepDirection.of(0, -2));
    }

    @Test
    void testOf_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> StepDirection.of(1, 1));
    }

}