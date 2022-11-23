package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.common.ExceptionMessages;
import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private final Repository<Discoverer> discovererRepository;
    private final Repository<Spot> spotRepository;
    private int spotCount;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        switch (kind) {
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);

        }
        this.discovererRepository.add(discoverer);
        return String.format(ConstantMessages.DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }
        this.spotRepository.add(spot);
        return String.format(ConstantMessages.SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer != null) {
            discovererRepository.remove(discoverer);
            return String.format(ConstantMessages.DISCOVERER_EXCLUDE, discovererName);
        } else {
            throw new IllegalArgumentException(String.format(
                    ExceptionMessages.DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> discoverer = discovererRepository.getCollection()
                .stream()
                .filter(currentDiscoverer -> currentDiscoverer.getEnergy() > 45)
                .collect(Collectors.toList());
        if (discoverer.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        Spot spot = new SpotImpl(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot, discoverer);
        long excluded = discoverer
                .stream().filter(d -> d.getEnergy() == 0).count();
        this.spotCount++;
        return String.format(ConstantMessages.INSPECT_SPOT, spotName, excluded);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(ConstantMessages.FINAL_SPOT_INSPECT, this.spotCount));
        sb.append(System.lineSeparator());
        sb.append(ConstantMessages.FINAL_DISCOVERER_INFO);

        Collection<Discoverer> discoverers = discovererRepository.getCollection();
        for (Discoverer discoverer : discoverers) {
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_NAME, discoverer.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_ENERGY, discoverer.getEnergy()));
            sb.append(System.lineSeparator());
            if (discoverer.getMuseum().getExhibits().isEmpty()) {
                sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None"));

            } else {
                sb.append(String.format(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS,
                        String.join(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, discoverer.getMuseum().getExhibits())));
            }
        }
        return sb.toString();
    }
}
