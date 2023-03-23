package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private final AstronautRepository<Astronaut> astronautRepository = new AstronautRepository<>();

    private final PlanetRepository<Planet> planetRepository = new PlanetRepository<>();

    private int exploredPlanetsCount = 0;


    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        for (String item : items) {
            planet.getItems().add(item);
        }
        this.planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = this.astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        this.astronautRepository.remove(astronaut);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> astronautsForMission = this.astronautRepository.getModels()
                .stream().filter(a -> a.getOxygen() > 60).collect(Collectors.toList());
        if (astronautsForMission.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        } else {
            Planet planet = this.planetRepository.findByName(planetName);
            Mission mission = new MissionImpl();
            mission.explore(planet, astronautsForMission);
            long retired = astronautsForMission.stream().filter(a -> a.getOxygen() == 0).count();
            exploredPlanetsCount++;
            return String.format(PLANET_EXPLORED, planetName, retired);
        }

    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(REPORT_PLANET_EXPLORED, exploredPlanetsCount));
        sb.append(System.lineSeparator());
        sb.append(REPORT_ASTRONAUT_INFO);
        sb.append(System.lineSeparator());
        for (Astronaut astronaut : this.astronautRepository.getModels()) {
            sb.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen()));
            sb.append(System.lineSeparator());
            if (astronaut.getBag().getItems().isEmpty()) {
                sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none"));
            } else {
                sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS,
                        String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER,
                                astronaut.getBag().getItems())));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
