package heroRepository;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HeroRepositoryTests {

    @Test
    public void testCount() {
        Hero hero = new Hero("Rand", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        Assert.assertEquals(1, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testAddHeroNull() {
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeroWithNameExist() {
        Hero hero = new Hero("Rand", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero);
    }

    @Test
    public void successfulAddHero() {
        Hero hero = new Hero("Rand", 100);
        HeroRepository heroRepository = new HeroRepository();
//        heroRepository.create(hero);
        String result = heroRepository.create(hero);
        Assert.assertEquals("Successfully added hero Rand with level 100", result);
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionRemoveHeroNull() {
        Hero hero = new Hero("Rand", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionRemoveHeroWhitespace() {
        Hero hero = new Hero("Rand", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.remove("       ");
    }

    @Test
    public void testIsRemove() {
        Hero hero = new Hero("Rand", 100);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        Assert.assertTrue(heroRepository.remove("Rand"));
    }

    @Test
    public void testGetHeroHighLevel() {
        Hero hero = new Hero("Rand", 100);
        Hero hero2 = new Hero("Ivo", 90);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero2);
        Assert.assertEquals(hero, heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testGetHeroByName() {
        Hero hero = new Hero("Rand", 100);
        Hero hero2 = new Hero("Ivo", 90);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        heroRepository.create(hero2);

        Assert.assertEquals(hero, heroRepository.getHero("Rand"));
    }
}
