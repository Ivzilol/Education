package Exercises_04.MilitaryElite;

public class SpecialisedSoldierImpl extends SoldierImpl {

    private Corps corps;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public Corps getCorps() {
        return this.corps;
    }

    @Override
    public String toString() {
        return "SpecialisedSoldierImpl{" +
                "corps=" + getCorps().name() +
                '}';
    }
}
