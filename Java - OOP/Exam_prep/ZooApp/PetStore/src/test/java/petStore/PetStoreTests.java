package petStore;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetStoreTests {

    @Test
    public void getCountTest() {
        Animal animal = new Animal("Dog", 50, 100);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        Assert.assertEquals(1, petStore.getCount());
    }

    @Test
    public void testFindMaxKilograms() {
        Animal animal = new Animal("Dog", 120, 100);
        Animal animal2 = new Animal("Cat", 50, 100);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal2);
        int kg = 100;
        List<Animal> actualPet = petStore.findAllAnimalsWithMaxKilograms(kg);

        Assert.assertEquals(1, actualPet.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalException() {
        PetStore petStore = new PetStore();
        petStore.addAnimal(null);
    }

    @Test
    public void addAnimalTest() {
        Animal animal = new Animal("Dog", 120, 100);
        Animal animal2 = new Animal("Cat", 50, 100);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal2);
        Assert.assertEquals(2, petStore.getAnimals().size());
    }

    @Test
    public void getMostExpensiveAnimal() {
        Animal animal = new Animal("Dog", 120, 120);
        Animal animal2 = new Animal("Cat", 50, 100);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal2);

        Animal result = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(animal, result);
    }

    @Test
    public void findAnimalBySpecie() {
        Animal animal = new Animal("Dog", 120, 120);
        Animal animal2 = new Animal("Cat", 50, 100);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal2);
        List<Animal> expected = new ArrayList<>(List.of(animal));
        List<Animal> result = petStore.findAllAnimalBySpecie("Dog");
        Assert.assertEquals(expected, result);
    }


}

