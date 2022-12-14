package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

import static zoo.common.ConstantMessages.AREA_NOT_SUITABLE;
import static zoo.common.ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA;
import static zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository;

    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        if (!(areaType.equals("WaterArea") || areaType.equals("LandArea"))) {
            throw new NullPointerException(INVALID_AREA_TYPE);
        } else {
            Area area;
            switch (areaType) {
                case "WaterArea":
                    area = new WaterArea(areaName);
                    this.areas.add(area);
                    break;
                case "LandArea":
                    area = new LandArea(areaName);
                    this.areas.add(area);
                    break;
            }
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;
        if (!(foodType.equals("Vegetable") || foodType.equals("Meat"))) {
            throw new IllegalStateException(INVALID_FOOD_TYPE);
        } else {

            switch (foodType) {
                case "Vegetable":
                    food = new Vegetable();
                    foodRepository.add(food);
                    break;
                case "Meat":
                    food = new Meat();
                    foodRepository.add(food);
                    break;
            }
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food food;
        Area area = getAreaByName(areaName);
        food = foodRepository.findByType(foodType);

        if (food == null) {
            throw new IllegalStateException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));
        } else {
            area.addFood(food);
            foodRepository.remove(food);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
        }
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Area area = getAreaByName(areaName);
        Animal animal;
        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, kind, price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }
        if (area.getClass().getSimpleName().equals("WaterArea") && animalType.equals("AquaticAnimal")) {
            area.addAnimal(animal);
        } else if (area.getClass().getSimpleName().equals("LandArea") && animalType.equals("TerrestrialAnimal")) {
            area.addAnimal(animal);
        } else {
            return AREA_NOT_SUITABLE;
        }
        return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = getAreaByName(areaName);
        for (Animal animal : area.getAnimals()) {
            animal.eat();
        }
        return String.format(ConstantMessages.ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = getAreaByName(areaName);
        double sumKg = 0;
        for (Animal animal : area.getAnimals()) {
            sumKg += animal.getKg();
        }
        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, sumKg);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb=new StringBuilder();
        for (Area area : this.areas) {
            sb.append(area.getInfo());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private Area getAreaByName(String areaName) {
        return this.areas
                .stream()
                .filter(arena -> arena.getName().equals(areaName))
                .findFirst()
                .orElse(null);
    }
}
