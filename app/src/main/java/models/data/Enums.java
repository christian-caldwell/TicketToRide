package models.data;

import java.io.Serializable;

public class Enums {
    public enum Color implements Serializable{
        GREEN(1), RED(2), YELLOW(3), BLUE(4), ORANGE(5), PURPLE(6), WHITE(7), BLACK(8), WILD(9);

        Color(int i) {
        }
    }
    public enum PlayerColor implements Serializable {
        RED, GREEN, BLUE, YELLOW, BLACK
    }
}

