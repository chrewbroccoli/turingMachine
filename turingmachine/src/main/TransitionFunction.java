package main;

public class TransitionFunction {
        private Symbol readSymbol;
        private Symbol writeSymbol;
        private int newStateIndex;
        private Direction nextDirection;

        TransitionFunction(Symbol currentSymbol, Symbol newSymbol, int newStateIndex, Direction nextDirection){
            this.readSymbol = currentSymbol;
            this.writeSymbol =newSymbol;
            this.newStateIndex =newStateIndex;
            this.nextDirection = nextDirection;
        }

        public Symbol getReadSymbol() {
            return readSymbol;
        }

        public Symbol getWriteSymbol() {
            return writeSymbol;
        }
        public int getNewStateIndex() {
            return newStateIndex;
        }
        public Direction getNextMove() {
            return nextDirection;
        }

        @Override
        public String toString(){
            return (readSymbol.getValue()+"|"+ writeSymbol.getValue()+"  "+ nextDirection.toString()+ " next state: "+ newStateIndex);
        }
    }

