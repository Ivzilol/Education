package com.example.football.service.impl;

import com.example.football.models.dto.TeamSeedDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final TownRepository townRepository;
    private static final String TEAM_PATH = "src/main/resources/files/json/teams.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;


    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files
                .readString(Path.of(TEAM_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();


        Arrays.stream(gson
                        .fromJson(readTeamsFileContent(), TeamSeedDto[].class))
                .filter(teamSeedDto -> {
                    boolean isValid = validationUtil.isValid(teamSeedDto);
                    Optional<Team> teamByName = this.teamRepository.findByName(teamSeedDto.getName());

                    if (teamByName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Team %s - %d",
                                    teamSeedDto.getName(), teamSeedDto.getFanBase())
                            :
                            "Invalid Team"
                    );
                    sb.append(System.lineSeparator());
                    return isValid;
                })
                .map(teamSeedDto -> {
                    Team team = mapper.map(teamSeedDto, Team.class);
                    Optional<Town> townByName = this.townRepository.findTownByName(teamSeedDto.getTownName());
                    team.setTown(townByName.get());
                    return team;
                })
                .forEach(this.teamRepository::save);
        return sb.toString();
    }
}
