package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentSeedDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;

    private final TownRepository townRepository;

    private static final String AGENT_PATH = "src/main/resources/files/json/agents.json";

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;


    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files
                .readString(Path.of(AGENT_PATH));
    }

    @Override
    public String importAgents() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readAgentsFromFile(), AgentSeedDto[].class))
                .filter(agentSeedDto -> {
                    boolean isValid = validationUtil.isValid(agentSeedDto);

                    Optional<Agent> agentByName = this.agentRepository.findByFirstName(agentSeedDto.getFirstName());

                    if (agentByName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                                    ? String.format("Successfully imported agent - %s %s", agentSeedDto.getFirstName(), agentSeedDto.getLastName())
                                    : "Invalid agent")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(agentSeedDto -> {
                    Agent agent = mapper.map(agentSeedDto, Agent.class);
                    Optional<Town> townByName = this.townRepository.findTownByTownName(agentSeedDto.getTown());
                    if (townByName.isEmpty()) {
                        System.out.println("ERROR: " + agent.getFirstName() + agent.getLastName());
                        return agent;
                    }
                    agent.setTown(townByName.get());
                    return agent;
                })
                .forEach(agentRepository::save);
        return sb.toString();
    }
}
