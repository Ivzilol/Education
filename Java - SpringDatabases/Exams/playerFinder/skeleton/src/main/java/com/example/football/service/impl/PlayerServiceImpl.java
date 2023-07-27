package com.example.football.service.impl;

import com.example.football.models.dto.ExportBestPlayersDto;
import com.example.football.models.dto.PlayerSeedRootDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final TownRepository townRepository;

    private final StatRepository statRepository;

    private final TeamRepository teamRepository;

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;


    private static final String PLAYER_PATH = "src/main/resources/files/xml/players.xml";

    public PlayerServiceImpl(PlayerRepository playerRepository, TownRepository townRepository, StatRepository statRepository, TeamRepository teamRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
        this.teamRepository = teamRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files
                .readString(Path.of(PLAYER_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(PLAYER_PATH, PlayerSeedRootDto.class)
                .getPlayerSeedDtoList()
                .stream()
                .filter(playerSeedDto -> {
                    boolean isValid = validationUtil.isValid(playerSeedDto);

                    Optional<Player> player = this.playerRepository
                            .findByEmail(playerSeedDto.getEmail());

                    if (player.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Player %s %s - %s",
                                    playerSeedDto.getFirstName(), playerSeedDto.getLastName(),
                                    playerSeedDto.getPosition())
                            :
                            "Invalid Player"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(playerSeedDto -> {
                    Player player = mapper.map(playerSeedDto, Player.class);
                    Optional<Town> town = this.townRepository
                            .findByName(playerSeedDto.getTowns().getName());
                    Optional<Team> team = this.teamRepository
                            .findByName(playerSeedDto.getTeams().getName());
                    Optional<Stat> stat = this.statRepository
                            .findById(playerSeedDto.getStats().getId());
                    player.setTown(town.get());
                    player.setTeam(team.get());
                    player.setStat(stat.get());
                    return player;
                })
                .forEach(this.playerRepository::save);
        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();

        List<ExportBestPlayersDto> bestPlayers = this.playerRepository
                .exportBestPlayers();

        bestPlayers.forEach(p -> {
            sb.append(String.format("Player - %s %s%n" +
                            "    Position - %s%n" +
                            "    Team - %s%n" +
                            "    Stadium - %s%n",
                    p.getFirstName(), p.getLastName(),
                    p.getPosition(),
                    p.getTownName(),
                    p.getStadiumName()
            ));
        });

        return sb.toString();
    }
}
