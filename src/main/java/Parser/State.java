package Parser;

import Parser.combiner.ItemCombiner;
import Parser.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/*
*   A state has some possible transitions to other states
*
* */
public class State implements Iterable<Transition>{
    private ArrayList<Transition> possibleTransitions;

    private ItemCombiner[] postAppendCombiners;

    private final boolean finalState;
    public State(Transition[] possibleTransitions){
        this(possibleTransitions, false);
    }
    public State(Transition[] possibleTransitions, boolean isFinalState){
        this(possibleTransitions, isFinalState, new ItemCombiner[0]);
    }

    public State(Transition[] possibleTransitions, ItemCombiner[] postAppendCombiners){
        this(possibleTransitions, false, postAppendCombiners);
    }

    public State(Transition[] possibleTransitions, boolean isFinalState, ItemCombiner[] postAppendCombiners){
        this.possibleTransitions = new ArrayList<Transition>();
        this.possibleTransitions.addAll(Arrays.asList(possibleTransitions));
        this.finalState = isFinalState;
        this.postAppendCombiners = postAppendCombiners;
    }

    public boolean isFinalState(){
        return this.finalState;
    }

    // Must have a way to add transitions in post to make
    // recursions possible.
    public void addTransition(Transition t){
        possibleTransitions.add(t);
    }
    
    public void addPostCombiners(ArrayList<Item> items){
        for (Item item: postAppendCombiners) {
            items.add(item);
        }
    }
    
    public static class TransitionItterator implements Iterator{

        Transition[] transitions;
        int ittrPosition=0;
        public TransitionItterator(ArrayList<Transition> transitionList){
            this.transitions = new Transition[transitionList.size()];
            for (int i = 0; i<transitions.length; i++)
                transitions[i] = transitionList.get(i);
        }

        @Override
        public boolean hasNext() {
            return ittrPosition<transitions.length;
        }

        @Override
        public Object next() {
            return transitions[ittrPosition++];
        }
    }

    @Override
    public Iterator<Transition> iterator() {
        return new TransitionItterator(possibleTransitions);
    }
}
