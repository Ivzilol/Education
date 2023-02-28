package magicGame;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MagicianTests {

    @Test(expected = NullPointerException.class)
    public void usernameMustThrowException() {
        Magician magician = new Magician(null, 100);
    }

    @Test
    public void getUsername() {
        Magician magician = new Magician("Ivo", 100);
        Assert.assertEquals("Ivo", magician.getUsername());
    }


    @Test
    public void getHealth() {
        Magician magician = new Magician("Ivo", 100);
        Assert.assertEquals(100, magician.getHealth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullHealthMustThrowException() {
        Magician magician = new Magician("Ivo", -1);
    }

    @Test
    public void getMagics() {
        Magic magic = new Magic("Ivo", 100);
        Magician magician = new Magician("Pesho", 200);
        magician.addMagic(magic);

        List<Magic> expected = List.of(magic);
        Assert.assertEquals(expected, magician.getMagics());
    }

    @Test
    public void takeDamageIsWork() {
        Magician magician = new Magician("Ivo", 100);
        magician.takeDamage(5);
        Assert.assertEquals(95, magician.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void isPlayerIsDeadException() {
        Magician magician = new Magician("Ivo", 0);
        magician.takeDamage(120);
    }

    @Test
    public void healthMustBeZero() {
        Magician magician = new Magician("Ivo", 50);
        magician.takeDamage(60);
        Assert.assertEquals(0, magician.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void addMagicException() {
        Magic magic = null;
        Magician magician = new Magician("Ivo", 200);
        magician.addMagic(null);
    }

    @Test
    public void removeMagic(){
        Magic magic = new Magic("Ivo", 100);
        Magic magic2 = new Magic("Pesho", 100);
        Magician magician = new Magician("Gosho", 100);
        magician.addMagic(magic);
        magician.removeMagic(magic);
    }

    @Test
    public void getMagic() {
        Magician magician = new Magician("Pesho", 50);
        Magic magic = new Magic("Pecata",5);
        magician.addMagic(magic);

        Assert.assertEquals(magic, magician.getMagic("Pecata"));
    }

    @Test
    public void testGetGunReturnNull(){
        Magician magician = new Magician("Pesho", 50);
        Magic magic = new Magic("Pecata",5);
        magician.addMagic(magic);

        Assert.assertNull(magician.getMagic("Gosho"));
    }
}
