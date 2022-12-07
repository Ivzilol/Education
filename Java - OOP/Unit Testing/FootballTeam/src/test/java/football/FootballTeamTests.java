package football;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FootballTeamTests {

    @Test(expected = NullPointerException.class)
    public void testSetNameNull() {
        new FootballTeam(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameOnlyWhitespace() {
        new FootballTeam("      ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacityZeroOrLess() {
        new FootballTeam("Inter", -3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerInFullTeam() {
        FootballTeam footballTeam = new FootballTeam("Real", 1);
        footballTeam.addFootballer(new Footballer("Ivan"));
        footballTeam.addFootballer(new Footballer("Iordan"));
    }

    @Test
    public void testGetName() {
        FootballTeam footballTeam = new FootballTeam("Real", 1);
        assertEquals("Real", footballTeam.getName());
    }

    @Test
    public void testSize() {
        FootballTeam footballTeam = new FootballTeam("Real", 1);
        footballTeam.addFootballer(new Footballer("Ivan"));
        assertEquals(1, footballTeam.getCount());
    }

    @Test
    public void testAddPlayer() {
        FootballTeam footballTeam = new FootballTeam("Real", 1);
        Footballer footballer = new Footballer("Ivan");
        footballTeam.addFootballer(new Footballer("Ivan"));
        assertEquals("Ivan", footballer.getName());
    }

    @Test
    public void testGetCountAfterAdding() {
        FootballTeam footballTeam = new FootballTeam("Real", 1);
        Footballer footballer = new Footballer("Raul");
        footballTeam.addFootballer(footballer);

        assertEquals(1, footballTeam.getCount());
    }

    @Test
    public void testCountAfterRemove() {
        FootballTeam footballTeam = new FootballTeam("Real", 3);
        Footballer footballer = new Footballer("Raul");
        Footballer footballer2 = new Footballer("Zidan");
        Footballer footballer3 = new Footballer("Carlosh");
        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer2);
        footballTeam.addFootballer(footballer3);
        footballTeam.removeFootballer("Raul");
        footballTeam.removeFootballer("Zidan");
        assertEquals(1, footballTeam.getCount());
    }

    @Test
    public void testStatistics() {
        FootballTeam footballTeam = new FootballTeam("Real", 3);
        footballTeam.addFootballer(new Footballer("Raul"));
        footballTeam.addFootballer(new Footballer("Zidan"));
        footballTeam.addFootballer(new Footballer("Carlosh"));

        String expected = "The footballer Raul, Zidan, Carlosh is in the team Real.";

        assertEquals(expected, footballTeam.getStatistics());
    }

    @Test
    public void testVacantPosition() {
        FootballTeam footballTeam = new FootballTeam("Real", 3);
        footballTeam.addFootballer(new Footballer("Raul"));
        assertEquals(1, footballTeam.getCount());
    }

    @Test
    public void testCatForSaleHungry() {
        FootballTeam footballTeam = new FootballTeam("Real", 1);
        Footballer footballer = new Footballer("Raul");
        footballTeam.addFootballer(footballer);
        footballTeam.footballerForSale("Raul");

        assertFalse(footballer.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleUnknownCat() {
        FootballTeam footballTeam = new FootballTeam("Real", 1);
        footballTeam.footballerForSale("Raul");
    }
}
