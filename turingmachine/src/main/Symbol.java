package main;

public enum Symbol {
    ONE("1"),
    ZERO("0"),
    BLANK("-"),
    X("x");

    Symbol(String value){
        this.value = value;
    }
    private final String value;

    public String getValue(){
        return value;
    }
}
