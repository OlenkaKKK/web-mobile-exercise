package core.constants;

public enum Direction {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right"),
    WIDTH("width"),
    HEIGHT("height"),
    TOP("top");


    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getValue() {
        return direction;
    }
}
