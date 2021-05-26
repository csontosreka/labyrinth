package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private Position position = new Position(1,1);

    @Test
    void testToString() {
        assertEquals("(1,1)", position.toString());
    }
}