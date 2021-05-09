package main;

public class TransitionFunction {
        private Symbol currentSymbol;
        private Symbol newSymbol;
        private int newStateIndex;
        private Move nextMove;

        TransitionFunction(Symbol currentSymbol, Symbol newSymbol, int newStateIndex, Move nextMove){
            this.currentSymbol = currentSymbol;
            this.newSymbol =newSymbol;
            this.newStateIndex =newStateIndex;
            this.nextMove=nextMove;
        }

        public Symbol getCurrentSymbol() {
            return currentSymbol;
        }

        public Symbol getNewSymbol() {
            return newSymbol;
        }
        public int getNewStateIndex() {
            return newStateIndex;
        }
        public Move getNextMove() {
            return nextMove;
        }

        @Override
        public String toString(){
            return (currentSymbol.getValue()+"|"+newSymbol.getValue()+"  "+ nextMove.toString()+ " next state: "+ newStateIndex);
        }
    }

