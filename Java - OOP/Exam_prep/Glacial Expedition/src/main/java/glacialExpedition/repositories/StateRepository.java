package glacialExpedition.repositories;

import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StateRepository<T> implements Repository<State>{

    private Collection<State> states;

    public StateRepository() {
        this.states = new ArrayList<>();
    }

    @Override
    public Collection<State> getCollection() {
        return Collections.unmodifiableCollection(this.states);
    }

    @Override
    public void add(State state) {
        this.states.add(state);
    }

    @Override
    public boolean remove(State state) {
        return this.states.remove(state);
    }

    @Override
    public State byName(String name) {
        return this.states.stream().filter(s ->
                s.getName().equals(name)).findFirst().orElse(null);
    }
}
