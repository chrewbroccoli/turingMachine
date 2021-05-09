package main;

import java.util.*;

public class TuringMachine {
        public static void main(String[] args) {
            //todo: Geben Sie hier den Input für das Tape der Turing Maschine ein:
            TuringMachine turingMachine = new TuringMachine("111");
            turingMachine.doCalculations();
        }

        int currentState;
        private List<State> states;

        //integer = position , symbol = symbol at position
        private HashMap<Integer, Symbol> tape = new HashMap<>();

        //head position
        private int currentPosition;
        private boolean finished;

        public TuringMachine(String tapeInput){
            states=createStates();
            setInitialState();
            currentPosition = 0;
            finished = false;
            convertTape(tapeInput);
            System.out.println("this is the tape:");
        }

        private void setInitialState(){
            for (State state: states){
                if (state.isInitialState()){
                    currentState = states.indexOf(state);
                }
            }
        }

        private void doCalculations(){
            boolean doesWork;
            int counter = 1;
            do {
                checkCurrentPosition(currentPosition);
                doesWork=false;

                for (TransitionFunction transitionFunction: states.get(currentState).getTransitionFunctions()){
                    if (tape.get(currentPosition)==transitionFunction.getCurrentSymbol()){
                        print();
                        System.out.println("Move number: "+counter++);
                        System.out.println("current state: "+currentState);
                        System.out.println(transitionFunction.toString());
                        tape.put(currentPosition, transitionFunction.getNewSymbol());
                        doMove(transitionFunction.getNextMove());
                        currentState = transitionFunction.getNewStateIndex();
                        doesWork=true;
                    }
                }
                if (states.get(currentState).isAcceptableState()||!doesWork){
                    print();
                    if (!doesWork){
                        System.err.println("invalid tape");
                    } else {
                        System.out.println("arrived at final state "+currentState+" !");
                    }
                    finished = true;
                }
            }while (finished==false);
        }


        // todo: hier können die States und die transitionfunctions erstellt werden
        private List<State> createStates (){
            List<State> states = new ArrayList<>();
            //create state 0
            List<TransitionFunction> transitionFunctionsState0 = new ArrayList<>();
            transitionFunctionsState0.add(new TransitionFunction(Symbol.ONE,Symbol.ONE,0,Move.RIGHT));
            transitionFunctionsState0.add(new TransitionFunction(Symbol.BLANK,Symbol.ZERO,1,Move.STAY));
            State state0 = new State(transitionFunctionsState0, false, true);

            //create state 1
            List<TransitionFunction> transitionFunctionsState1 = new ArrayList<>();
            transitionFunctionsState1.add(new TransitionFunction(Symbol.ZERO,Symbol.ZERO,1,Move.LEFT));
            transitionFunctionsState1.add(new TransitionFunction(Symbol.X,Symbol.X,1,Move.LEFT));
            transitionFunctionsState1.add(new TransitionFunction(Symbol.ONE,Symbol.X,2,Move.RIGHT));
            transitionFunctionsState1.add(new TransitionFunction(Symbol.BLANK,Symbol.BLANK,4,Move.RIGHT));
            State state1 = new State(transitionFunctionsState1, false, false);

            //create state 2
            List<TransitionFunction> transitionFunctionsState2 = new ArrayList<>();
            transitionFunctionsState2.add(new TransitionFunction(Symbol.ONE,Symbol.ONE,2,Move.RIGHT));
            transitionFunctionsState2.add(new TransitionFunction(Symbol.ZERO,Symbol.ZERO,2,Move.RIGHT));
            transitionFunctionsState2.add(new TransitionFunction(Symbol.X,Symbol.X,2,Move.RIGHT));
            transitionFunctionsState2.add(new TransitionFunction(Symbol.BLANK,Symbol.ONE,3,Move.STAY));
            State state2 = new State(transitionFunctionsState2, false, false);

            //create state 3
            List<TransitionFunction> transitionFunctionsState3 = new ArrayList<>();
            transitionFunctionsState3.add(new TransitionFunction(Symbol.ONE,Symbol.ONE,3,Move.LEFT));
            transitionFunctionsState3.add(new TransitionFunction(Symbol.ZERO,Symbol.ZERO,1,Move.LEFT));
            State state3 = new State(transitionFunctionsState3, false, false);

            //create state 4
            List<TransitionFunction> transitionFunctionsState4 = new ArrayList<>();
            transitionFunctionsState4.add(new TransitionFunction(Symbol.X,Symbol.ONE,4,Move.RIGHT));
            transitionFunctionsState4.add(new TransitionFunction(Symbol.ZERO,Symbol.ONE,4,Move.STAY));
            State state4 = new State(transitionFunctionsState4, false,false);

            //create state 5

            //add states to list
            states.add(state0);
            states.add(state1);
            states.add(state2);
            states.add(state3);
            states.add(state4);
            return states;
        }

        private void convertTape(String tapeInput){
            int i = 0;
            for (char ch: tapeInput.toCharArray()){
                Symbol symbol;
                if (ch=='1'){
                    symbol = Symbol.ONE;
                }
                else if (ch=='0'){
                    symbol = Symbol.ZERO;
                }
                else {
                    symbol = Symbol.BLANK;
                }
                this.tape.put(i,symbol);
                i++;
            }
        }

        private void doMove (Move move){
            currentPosition = currentPosition + move.getValue();
        }

        private void checkCurrentPosition(int currentPosition){
            if (!tape.containsKey(currentPosition)){
                tape.put(currentPosition, Symbol.BLANK);
            }
        }

        private void print(){
            ArrayList<Integer>tapePositions = new ArrayList<>();
            for(Map.Entry<Integer, Symbol> entry: tape.entrySet()){
                tapePositions.add(entry.getKey());
            }
            Collections.sort(tapePositions);
            for (Integer position:tapePositions){
                System.out.print(tape.get(position).getValue());
            }
            System.out.println("");
            for (Integer position:tapePositions){
                if (position==currentPosition){
                    System.out.print("^");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }

    }



