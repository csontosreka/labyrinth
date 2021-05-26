package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthModelTest {

    private LabyrinthModel model = new LabyrinthModel();

    @Test
    void testIsOnBoard() {
        assertTrue(model.isOnBoard(1,1));
        assertFalse(model.isOnBoard(10,10));
    }


    @Test
    void testIsValidDirection() {
        assertTrue(model.isValidDirection(7,3));
        assertFalse(model.isValidDirection(5,4));
    }


    @Test
    void isStartMove() {
        assertTrue(model.isStartMove());
    }

    @Test
    void isSolved() {
        assertFalse(model.isSolved());
    }


    @Test
    void testToString() {
        assertEquals(
        "2 3 2 3 2 5 2 3 2 \n" +
        "3 0 4 0 4 0 6 0 3 \n" +
        "2 5 2 6 2 5 2 4 2 \n" +
        "3 0 5 0 4 0 5 0 3 \n" +
        "2 6 2 5 2 6 2 4 2 \n" +
        "3 0 5 0 4 0 5 0 3 \n" +
        "2 4 2 6 2 5 2 6 2 \n" +
        "3 0 5 0 5 0 4 0 3 \n" +
        "2 3 2 4 2 3 2 3 2 \n", model.toString());
    }
}