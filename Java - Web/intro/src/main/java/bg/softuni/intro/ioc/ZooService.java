package bg.softuni.intro.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooService{
    private List<Animal> animals;

    private final Animal animal;


    public ZooService(@Qualifier("mySuperDog") Animal animal){
        this.animal = animal;
    }

    public void doWork(){
        animal.makeNoise();
    }

//    public List<Animal> animals;

//    public ZooService(List<Animal> animals){
//        this.animals = animals;
//    }
//
//    public void doWork(){
//        animals.forEach(
//                Animal::makeNoise
//        );
//    }



}
