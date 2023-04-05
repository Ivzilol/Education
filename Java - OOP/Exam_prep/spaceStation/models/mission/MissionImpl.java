package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission {


    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        Collection<String> itemsOnPlanet = planet.getItems();

        for (Astronaut astronaut : astronauts) {
            if (!itemsOnPlanet.iterator().hasNext()) {
                break;
            }
            while (astronaut.canBreath()) {
                if (!itemsOnPlanet.iterator().hasNext()) {
                    break;
                }
                if (astronaut.canBreath() && itemsOnPlanet.iterator().hasNext()) {
                    String currentItem = itemsOnPlanet.iterator().next();
                    astronaut.getBag().getItems().add(currentItem);
                    astronaut.breath();
                    itemsOnPlanet.remove(currentItem);
                }
            }
        }
    }
}
