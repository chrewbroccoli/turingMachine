package main;

public enum Direction {
    RIGHT (1),
    LEFT(-1),
    STAY(0);

    Direction(int value){
        this.value = value;
    }
    private final int value;

    public int getValue(){
        return value;
    }
}

