package Exercises_04.MilitaryElite;

import java.util.Set;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {


     public CommandoImpl(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    @Override
    public Set<Missions> getMissions() {
        return null;
    }
}
