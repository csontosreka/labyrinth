package model;


public interface Levels {

    Square lvl1Board[][] = {
            {Square.X, Square.BLACK, Square.X, Square.BLACK, Square.X, Square.BLUE, Square.X, Square.BLACK, Square.X},
            {Square.BLACK, Square.NONE, Square.RED, Square.NONE, Square.RED, Square.NONE, Square.WHITE, Square.NONE, Square.BLACK},
            {Square.X, Square.BLUE, Square.X, Square.WHITE, Square.X, Square.BLUE, Square.X, Square.RED, Square.X},
            {Square.BLACK, Square.NONE, Square.BLUE, Square.NONE, Square.RED, Square.NONE, Square.BLUE, Square.NONE, Square.BLACK},
            {Square.X, Square.WHITE, Square.X, Square.BLUE, Square.X, Square.WHITE, Square.X, Square.RED, Square.X},
            {Square.BLACK, Square.NONE, Square.BLUE, Square.NONE, Square.RED, Square.NONE, Square.BLUE, Square.NONE, Square.BLACK},
            {Square.X, Square.RED, Square.X, Square.WHITE, Square.X, Square.BLUE, Square.X, Square.WHITE, Square.X},
            {Square.BLACK, Square.NONE, Square.BLUE, Square.NONE, Square.BLUE, Square.NONE, Square.RED, Square.NONE, Square.BLACK},
            {Square.X, Square.BLACK, Square.X, Square.RED, Square.X, Square.BLACK, Square.X, Square.BLACK, Square.X}
    };

}
