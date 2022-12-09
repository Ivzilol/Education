package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportAgentDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AgentServiceImpl implements AgentService {

    private static final String AGENT_FILE_PATH = "src/main/resources/files/json/agents.json";

    private final AgentRepository agentRepository;

    private final TownRepository townRepository;
    private final Gson json;
    private final Validator validator;
    private final ModelMapper modelMapper;


    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository, Gson json) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.json = json;
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files
                .readString(Path.of(AGENT_FILE_PATH));
    }

    @Override
    public String importAgents() throws IOException {
        String json = readAgentsFromFile();
        ImportAgentDTO[] importAgentDTOS = this.json.fromJson(json, ImportAgentDTO[].class);
        List<String> result = new ArrayList<>();

        for (ImportAgentDTO importAgentDTO : importAgentDTOS) {
            Set<ConstraintViolation<ImportAgentDTO>> validationErrors =
                    this.validator.validate(importAgentDTO);
            if (validationErrors.isEmpty()) {
                Optional<Agent> optionalAgent = this.agentRepository
                        .findAgentByFirstName(importAgentDTO.getFirstName());
                if (optionalAgent.isPresent()) {
                    result.add("Invalid agent");
                } else {
                    Agent agent = this.modelMapper.map(importAgentDTO, Agent.class);
                    agent.setTown(townRepository.findByTownName(importAgentDTO.getTown()));
                    this.agentRepository.save(agent);
                    result.add(String.format("Successfully imported agent - %s %s",
                            importAgentDTO.getFirstName(), importAgentDTO.getLastName()));
                }
            } else {
                result.add("Invalid agent");
            }
        }
        return String.join("\n", result);
    }
}
