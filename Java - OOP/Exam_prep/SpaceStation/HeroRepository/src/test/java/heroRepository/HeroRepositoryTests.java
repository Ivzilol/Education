package heroRepository;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.plaf.PanelUI;
import java.lang.instrument.UnmodifiableClassException;
import java.util.*;

public class HeroRepositoryTests {


    @Test
    public void testCount() {
        Hero hero = new Hero("Ivo", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        Assert.assertEquals(1, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testException1() {
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException2() {
        Hero hero = new Hero("Ivo", 100);
        Hero hero2 = new Hero("Ivo", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero2);
    }

    @Test
    public void successfulCreateHero() {
        Hero hero = new Hero("Ivo", 100);
        Hero hero2 = new Hero("Ivan", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero2);
        Assert.assertEquals(2, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionRemove1() {
        Hero hero = new Hero(null, 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.remove(hero.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionRemove2() {
        Hero hero = new Hero("       ", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.remove(hero.getName());
    }

    @Test
    public void testRemoveSuccessful() {
        Hero hero = new Hero("Ivo", 100);
        Hero hero2 = new Hero("Ivan", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero2);
        boolean isRemove = heroRepository.remove("Ivo");
        Assert.assertTrue(isRemove);
        Assert.assertEquals(1, heroRepository.getCount());
    }

    @Test
    public void testRemoveUnsuccessful() {
        Hero hero = new Hero("Ivo", 100);
        Hero hero2 = new Hero("Ivan", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero2);
        boolean isRemove = heroRepository.remove("Gosho");
        Assert.assertFalse(isRemove);
        Assert.assertEquals(2, heroRepository.getCount());
    }

    @Test
    public void testHeroMaxLevel() {
        Hero hero = new Hero("Ivo", 90);
        Hero hero2 = new Hero("Ivan", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero2);

        Assert.assertEquals(hero2, heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testNullMaxLevel() {
        HeroRepository heroRepository = new HeroRepository();
        Assert.assertNull(heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testGetHero() {
        Hero hero = new Hero("Ivo", 90);
        Hero hero2 = new Hero("Ivan", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero2);
        Hero expectedHero = heroRepository.getHero("Ivo");
        Assert.assertEquals("Ivo", expectedHero.getName());
    }

    @Test
    public void testGetHeroNull() {
        HeroRepository heroRepository = new HeroRepository();
        Assert.assertNull(heroRepository.getHero("Ivo"));
    }

    @Test
    public void testConstructor() {
        HeroRepository heroRepository = new HeroRepository();
        Assert.assertSame(heroRepository, heroRepository);
    }

    @Test
    public void unmodifiableCollection() {
        Hero hero = new Hero("Ivo", 90);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        Collection<Hero> unmodifiable = Collections.unmodifiableCollection(heroRepository.getHeroes());
        Assert.assertSame(unmodifiable, heroRepository.getHeroes());
    }

}
