package petStore;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetStoreTests {

    @Test
    public void testGetAnimalsWork() {
        Animal animal = new Animal("Vat", 50, 100);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        List<Animal> expected = List.of(animal);
        Assert.assertEquals(expected, petStore.getAnimals());
    }

    @Test
    public void testGetCountWork() {
        Animal animal = new Animal("Cat", 50, 100);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        Assert.assertEquals(1, petStore.getCount());
    }

    @Test
    public void teatMaxKgWork(){
        Animal animal = new Animal("Cat", 50, 100);
        Animal animal1 = new Animal("Dog", 100, 200);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        List<Animal> expected = List.of(animal1);
        Assert.assertEquals(expected, petStore.findAllAnimalsWithMaxKilograms(60));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAddAnimal(){
        PetStore petStore = new PetStore();
        petStore.addAnimal(null);
    }

    @Test
    public void testSuccessfulAddAnimal(){
        Animal animal = new Animal("Cat", 50, 100);
        Animal animal1 = new Animal("Dog", 100, 200);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        List<Animal> expected = new ArrayList<>();
        expected.add(animal);
        expected.add(animal1);
        Assert.assertEquals(expected, petStore.getAnimals());
    }

    @Test
    public void testMoatExpensiveAnimal() {
        Animal animal = new Animal("Cat", 50, 100);
        Animal animal1 = new Animal("Dog", 100, 200);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        Assert.assertEquals(animal1, petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void testFindAnimalsBySpecie(){
        Animal animal = new Animal("Cat", 50, 100);
        Animal animal1 = new Animal("Dog", 100, 200);
        PetStore petStore = new PetStore();
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        List<Animal> expected = List.of(animal);
        Assert.assertEquals(expected, petStore.findAllAnimalBySpecie("Cat"));
    }
}

