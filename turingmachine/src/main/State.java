package main;

import java.util.ArrayList;
import java.util.List;

public class State {
        private List<TransitionFunction> transitionFunctions = new ArrayList<>();
        private boolean isInitialState;
        private boolean isAcceptableState;

        public State(List<TransitionFunction> transitionFunctions, boolean isAcceptableState, boolean isInitialState){
            this.transitionFunctions = transitionFunctions;
            this.isAcceptableState = isAcceptableState;
            this.isInitialState = isInitialState;
        }

        public boolean isAcceptableState() {
            return isAcceptableState;
        }

        public boolean isInitialState() {
            return isInitialState;
        }

        public List<TransitionFunction> getTransitionFunctions() {
            return transitionFunctions;
        }
    }

