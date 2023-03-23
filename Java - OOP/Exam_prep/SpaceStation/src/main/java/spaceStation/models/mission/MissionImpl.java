package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission{

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Collection<String> planetItems =  planet.getItems();
        for (Astronaut currentAstronaut : astronauts) {
            while (currentAstronaut.canBreath() && planetItems.iterator().hasNext()) {
                currentAstronaut.breath();
                String currentItem = planetItems.iterator().next();
                currentAstronaut.getBag().getItems().add(currentItem);
                planetItems.remove(currentItem);
            }
        }
    }
}
