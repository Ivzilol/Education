package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {


    private final ExplorerRepository<Explorer> explorerRepository = new ExplorerRepository<>();

    private final StateRepository<State> stateRepository = new StateRepository<>();

    private int exploreCount = 0;

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
        this.explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);

    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }
        this.stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = this.explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        this.explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> explorers = new ArrayList<>();
        for (Explorer explorer : this.explorerRepository.getCollection()) {
            if (explorer.getEnergy() > 50) {
                explorers.add(explorer);
            }
        }
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        } else {
            State state = this.stateRepository.byName(stateName);
            Mission mission = new MissionImpl();
            mission.explore(state, explorers);
            long retired = explorers.stream().filter(d -> d.getEnergy() == 0).count();
            exploreCount++;
            return String.format(STATE_EXPLORER, stateName, retired);
        }
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_STATE_EXPLORED, exploreCount));
        sb.append(System.lineSeparator());
        sb.append(FINAL_EXPLORER_INFO);
        sb.append(System.lineSeparator());
        Collection<Explorer> explorers = this.explorerRepository.getCollection();
        for (Explorer explorer : explorers) {
            sb.append(String.format(FINAL_EXPLORER_NAME, explorer.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy()));
            sb.append(System.lineSeparator());
            if (explorer.getSuitcase().getExhibits().isEmpty()) {
                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, "None"));
            } else {
                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS,
                        String.join(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER,
                                explorer.getSuitcase().getExhibits())));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
