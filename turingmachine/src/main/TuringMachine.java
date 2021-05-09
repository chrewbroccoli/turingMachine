package main;

import java.util.*;

public class TuringMachine {
    //list with all possible states
    private List<State> states;

    //current state of the turing machine
    private int currentStateNumber;
    private State currentState;

    //integer to count the executed moves
    private int moveCounter = 1;

    //integer = position , symbol = symbol at position
    private HashMap<Integer, Symbol> tape = new HashMap<>();

    //head position
    private int currentPosition;
    private boolean isFinalState;

    //stepModus
    private boolean isStepModus = false;
    ArrayList<String> printAtEnd = new ArrayList<>();

    public static void main(String[] args) {
        //todo: Geben Sie hier den Input für das Tape der Turing Maschine ein:
        TuringMachine turingMachine = new TuringMachine();
        turingMachine.initializeStates();
        turingMachine.readTapeInput("00100000000000000000000000001");
        turingMachine.doCalculations();
    }

    // todo: hier können die States und die transitionfunctions erstellt werden
    private List<State> createStates() {
        List<State> states = new ArrayList<>();
        //create state 0
        List<TransitionFunction> transitionFunctionsState0 = new ArrayList<>();
        transitionFunctionsState0.add(new TransitionFunction(Symbol.ZERO, Symbol.BLANK, 6, Direction.RIGHT));
        transitionFunctionsState0.add(new TransitionFunction(Symbol.ONE, Symbol.BLANK, 13, Direction.RIGHT));
        State state0 = new State(transitionFunctionsState0, false, true);

        //create state 1
        List<TransitionFunction> transitionFunctionsState1 = new ArrayList<>();
        transitionFunctionsState1.add(new TransitionFunction(Symbol.ZERO, Symbol.TWO, 2, Direction.RIGHT));
        transitionFunctionsState1.add(new TransitionFunction(Symbol.ONE, Symbol.ONE, 4, Direction.LEFT));
        State state1 = new State(transitionFunctionsState1, false, false);

        //create state 2
        List<TransitionFunction> transitionFunctionsState2 = new ArrayList<>();
        transitionFunctionsState2.add(new TransitionFunction(Symbol.ZERO, Symbol.ZERO, 2, Direction.RIGHT));
        transitionFunctionsState2.add(new TransitionFunction(Symbol.ONE, Symbol.ONE, 2, Direction.RIGHT));
        transitionFunctionsState2.add(new TransitionFunction(Symbol.BLANK, Symbol.ZERO, 3, Direction.LEFT));
        State state2 = new State(transitionFunctionsState2, false, false);

        //create state 3
        List<TransitionFunction> transitionFunctionsState3 = new ArrayList<>();
        transitionFunctionsState3.add(new TransitionFunction(Symbol.ZERO, Symbol.ZERO, 3, Direction.LEFT));
        transitionFunctionsState3.add(new TransitionFunction(Symbol.ONE, Symbol.ONE, 3, Direction.LEFT));
        transitionFunctionsState3.add(new TransitionFunction(Symbol.TWO, Symbol.TWO, 1, Direction.RIGHT));
        State state3 = new State(transitionFunctionsState3, false, false);

        //create state 4
        List<TransitionFunction> transitionFunctionsState4 = new ArrayList<>();
        transitionFunctionsState4.add(new TransitionFunction(Symbol.ONE, Symbol.ONE, 5, Direction.RIGHT));
        transitionFunctionsState4.add(new TransitionFunction(Symbol.TWO, Symbol.ZERO, 4, Direction.LEFT));
        State state4 = new State(transitionFunctionsState4, false, false);

        //create state 5
        List<TransitionFunction> transitionFunctionsState5 = new ArrayList<>();
        transitionFunctionsState5.add(new TransitionFunction(Symbol.ZERO, Symbol.ZERO, 7, Direction.LEFT));
        State state5 = new State(transitionFunctionsState5, false, false);

        //create state 6
        List<TransitionFunction> transitionFunctionsState6 = new ArrayList<>();
        transitionFunctionsState6.add(new TransitionFunction(Symbol.ZERO, Symbol.ZERO, 6, Direction.RIGHT));
        transitionFunctionsState6.add(new TransitionFunction(Symbol.ONE, Symbol.ONE, 14, Direction.RIGHT));
        State state6 = new State(transitionFunctionsState6, false, false);

        //create state 7
        List<TransitionFunction> transitionFunctionsState7 = new ArrayList<>();
        transitionFunctionsState7.add(new TransitionFunction(Symbol.ONE, Symbol.ONE, 8, Direction.LEFT));
        State state7 = new State(transitionFunctionsState7, false, false);

        //create state 8
        List<TransitionFunction> transitionFunctionsState8 = new ArrayList<>();
        transitionFunctionsState8.add(new TransitionFunction(Symbol.ZERO, Symbol.ZERO, 9, Direction.LEFT));
        transitionFunctionsState8.add(new TransitionFunction(Symbol.BLANK, Symbol.BLANK, 10, Direction.RIGHT));
        State state8 = new State(transitionFunctionsState8, false, false);

        //create state 9
        List<TransitionFunction> transitionFunctionsState9 = new ArrayList<>();
        transitionFunctionsState9.add(new TransitionFunction(Symbol.ZERO, Symbol.ZERO, 9, Direction.LEFT));
        transitionFunctionsState9.add(new TransitionFunction(Symbol.BLANK, Symbol.BLANK, 0, Direction.RIGHT));
        State state9 = new State(transitionFunctionsState9, false, false);

        //create state 10
        List<TransitionFunction> transitionFunctionsState10 = new ArrayList<>();
        transitionFunctionsState10.add(new TransitionFunction(Symbol.ONE, Symbol.BLANK, 11, Direction.RIGHT));
        State state10 = new State(transitionFunctionsState10, false, false);

        //create state 11
        List<TransitionFunction> transitionFunctionsState11 = new ArrayList<>();
        transitionFunctionsState11.add(new TransitionFunction(Symbol.ZERO, Symbol.BLANK, 11, Direction.RIGHT));
        transitionFunctionsState11.add(new TransitionFunction(Symbol.ONE, Symbol.BLANK, 12, Direction.RIGHT));
        State state11 = new State(transitionFunctionsState11, false, false);

        //create state 12
        List<TransitionFunction> transitionFunctionsState12 = new ArrayList<>();
        State state12 = new State(transitionFunctionsState12, true, false);

        //create state 13
        List<TransitionFunction> transitionFunctionsState13 = new ArrayList<>();
        transitionFunctionsState13.add(new TransitionFunction(Symbol.ZERO, Symbol.BLANK, 13, Direction.RIGHT));
        transitionFunctionsState13.add(new TransitionFunction(Symbol.ONE, Symbol.BLANK, 13, Direction.RIGHT));
        transitionFunctionsState13.add(new TransitionFunction(Symbol.BLANK, Symbol.BLANK, 12, Direction.STAY));
        State state13 = new State(transitionFunctionsState13, false, false);

        //create state 14
        List<TransitionFunction> transitionFunctionState14 = new ArrayList<>();
        transitionFunctionState14.add(new TransitionFunction(Symbol.ONE, Symbol.BLANK, 15, Direction.LEFT));
        transitionFunctionState14.add(new TransitionFunction(Symbol.ZERO, Symbol.ZERO, 1, Direction.STAY));
        State state14 = new State(transitionFunctionState14, false, false);

        //create state 14
        List<TransitionFunction> transitionFunctionState15 = new ArrayList<>();
        transitionFunctionState15.add(new TransitionFunction(Symbol.ONE, Symbol.BLANK, 15, Direction.LEFT));
        transitionFunctionState15.add(new TransitionFunction(Symbol.ZERO, Symbol.BLANK, 15, Direction.LEFT));
        transitionFunctionState15.add(new TransitionFunction(Symbol.BLANK, Symbol.BLANK, 12, Direction.STAY));
        State state15 = new State(transitionFunctionState15, false, false);


        //add states to list
        states.add(state0);
        states.add(state1);
        states.add(state2);
        states.add(state3);
        states.add(state4);
        states.add(state5);
        states.add(state6);
        states.add(state7);
        states.add(state8);
        states.add(state9);
        states.add(state10);
        states.add(state11);
        states.add(state12);
        states.add(state13);
        states.add(state14);
        states.add(state15);

        return states;
    }

    public void initializeStates() {
        isFinalState = false;
        states = createStates();
        setInitialState();
        currentPosition = 0;
    }


    public void readTapeInput(String tapeInput) {
        int i = 0;
        for (char ch : tapeInput.toCharArray()) {
            Symbol symbol;
            if (ch == '1') {
                symbol = Symbol.ONE;
            } else if (ch == '0') {
                symbol = Symbol.ZERO;
            } else {
                symbol = Symbol.BLANK;
            }
            this.tape.put(i, symbol);
            i++;
        }
        System.out.println("this is the tape:");
    }

    private void doCalculations() {
        do {
            checkCurrentPosition(currentPosition);
            TransitionFunction nextTransition = null;

            //current state will be analized and it will check if transition function exists for the current value on the tape
            for (TransitionFunction transitionFunction : currentState.getTransitionFunctions()) {
                if (transitionFunction.getReadSymbol().getValue().equals(tape.get(currentPosition).getValue())) {
                    nextTransition = transitionFunction;
                }
            }
            if (isStepModus) {
                System.out.println(print(nextTransition));
            }else{
                printAtEnd.add(print(nextTransition));
            }
            //check if current state is final state or invalid state
            if (states.get(currentStateNumber).isAcceptableState()) {
                if (!isStepModus){
                    for(String string:printAtEnd){
                        System.out.println(string);
                    }
                }
                System.out.println("arrived at final state " + currentStateNumber + " !");
                isFinalState = true;
            } else if (nextTransition == null) {
                if (!isStepModus){
                    for(String string:printAtEnd){
                        System.out.println(string);
                    }
                }
                System.err.println("invalid tape");
                isFinalState = true;
            } else {
                executeTransition(nextTransition);
            }
        } while (!isFinalState);
    }

    private void executeTransition(TransitionFunction transitionFunction) {
        tape.put(currentPosition, transitionFunction.getWriteSymbol());
        currentStateNumber = transitionFunction.getNewStateIndex();
        currentState = states.get(currentStateNumber);
        movePointer(transitionFunction.getNextMove());
    }

    private void setInitialState() {
        for (State state : states) {
            if (state.isInitialState()) {
                currentStateNumber = states.indexOf(state);
                currentState = states.get(currentStateNumber);
            }
        }
    }

    private void movePointer(Direction direction) {
        currentPosition = currentPosition + direction.getValue();
    }

    private void checkCurrentPosition(int currentPosition) {
        for(int i=currentPosition-15; i<=currentPosition+15; i++) {
            if (!tape.containsKey(i)) {
                tape.put(i, Symbol.BLANK);
            }
        }
    }

    private String print(TransitionFunction transitionFunction) {
        StringBuilder informations = new StringBuilder();
        informations.append("Move number: " + moveCounter++ +"\n");
        informations.append("current state: " + currentStateNumber+"\n");
        if (transitionFunction != null) {
            informations.append(transitionFunction +"\n");
        }
        for (int i=currentPosition-15; i<=currentPosition+15; i++) {
            informations.append(tape.get(i).getValue());
        }
        informations.append("\n");
        for (int i=currentPosition-15; i<=currentPosition+15; i++) {
            if (i == currentPosition) {
                informations.append("^");
            } else {
                informations.append(" ");
            }
        }
        informations.append("\n");
        return informations.toString();
    }

}



