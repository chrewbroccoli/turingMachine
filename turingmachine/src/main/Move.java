package main;

public enum Move {
    RIGHT (1),
    LEFT(-1),
    STAY(0);

    Move(int value){
        this.value = value;
    }
    private final int value;

    public int getValue(){
        return value;
    }
}

