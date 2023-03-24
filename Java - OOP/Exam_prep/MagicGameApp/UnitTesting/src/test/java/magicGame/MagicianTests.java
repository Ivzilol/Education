package magicGame;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MagicianTests {

    @Test(expected = NullPointerException.class)
    public void testExceptionNullUsername() {
        Magician magician = new Magician(null, 10);
    }

    @Test
    public void testUsernameWork() {
        Magician magician = new Magician("Ivo", 10);
        Assert.assertEquals("Ivo", magician.getUsername());
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionWhitespaceUsername() {
        Magician magician = new Magician("     ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionHealth(){
        Magician magician = new Magician("Ivo", -5);
    }

    @Test
    public void testGetHealthWork() {
        Magician magician = new Magician("Ivo", 10);
        Assert.assertEquals(10, magician.getHealth());
    }

    @Test
    public void testGetMagicsWork() {
        Magic magic = new Magic("First", 10);
        Magician magician = new Magician("Ivo", 10);
        magician.addMagic(magic);

        List<Magic> expected = List.of(magic);
        Assert.assertEquals(expected, magician.getMagics());
    }

    @Test
    public void testGetDamageWork() {
        Magician magician = new Magician("Ivo", 10);
        magician.takeDamage(5);
        Assert.assertEquals(5, magician.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageThrowException(){
        Magician magician = new Magician("Pesho", 0);
        magician.takeDamage(10);
    }


    @Test
    public void testZeroIfDamageMoreFromHealth() {
        Magician magician = new Magician("Ivo", 10);
        magician.takeDamage(15);
        Assert.assertEquals(0, magician.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionMagicNull() {
        Magician magician = new Magician("Ivo", 10);
        magician.addMagic(null);
    }

    @Test
    public void testSuccessfulAddMagic() {
        Magic magic = new Magic("First", 10);
        Magician magician = new Magician("Ivo", 10);
        magician.addMagic(magic);
        Assert.assertEquals(1, magician.getMagics().size());
    }

    @Test
    public void successfulRemoveMagic() {
        Magician magician = new Magician("Ivo", 10);
        Magic magic = new Magic("First", 10);
        magician.addMagic(magic);
        Assert.assertTrue(magician.removeMagic(magic));
    }

    @Test
    public void testGetMagicSuccessful() {
        Magician magician = new Magician("Ivo", 10);
        Magic magic = new Magic("First", 10);
        magician.addMagic(magic);
        Assert.assertEquals(magic, magician.getMagic("First"));
    }

    @Test
    public void testGetMagicReturnNull() {
        Magician magician = new Magician("Ivo", 10);
        Magic magic = new Magic("First", 10);
        magician.addMagic(magic);
        Assert.assertNull(magician.getMagic("Second"));
    }
}
