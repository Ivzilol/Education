package com.example.football.service.impl;

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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final StatRepository statRepository;

    private final TeamRepository teamRepository;

    private final TownRepository townRepository;

    private static final String PLAYER_PATH = "src/main/resources/files/xml/players.xml";
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, StatRepository statRepository, TeamRepository teamRepository, TownRepository townRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.playerRepository = playerRepository;
        this.statRepository = statRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
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

        PlayerSeedRootDto playerSeedRootDto = xmlParser
                .fromFile(PLAYER_PATH, PlayerSeedRootDto.class);

        playerSeedRootDto.getPlayerSeedDtoList()
                .stream()
                .filter(playerSeedDto -> {
                    boolean isValid = validationUtil.isValid(playerSeedDto);

                    Optional<Player> byEmail = this.playerRepository.findByEmail(playerSeedDto.getEmail());

                    if (byEmail.isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid
                                    ?
                                    String.format("Successfully imported Player %s %s - %s",
                                            playerSeedDto.getFirstName(), playerSeedDto.getLastName(),
                                            playerSeedDto.getPosition())
                                    :
                                    "Invalid Player")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(playerSeedDto -> {
                    Player player = mapper.map(playerSeedDto, Player.class);
                    Optional<Stat> stat = this.statRepository.findById(playerSeedDto.getStatId().getId());
                    Optional<Team> team = this.teamRepository.findByName(playerSeedDto.getTeamName().getName());
                    Optional<Town> town = this.townRepository.findByName(playerSeedDto.getTownName().getName());
                    player.setStat(stat.get());
                    player.setTeam(team.get());
                    player.setTown(town.get());
                    return player;
                })
                .forEach(playerRepository::save);
        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();
        LocalDate before = LocalDate.of(2003, 1, 1);
        LocalDate after = LocalDate.of(1995, 1, 1);
        List<Player> playerList = this.playerRepository.findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(after, before);

        playerList
                .forEach(player -> {
                    sb.append(String.format("Player - %s %s%n" +
                                            "   Position - %s%n" +
                                            "   Team - %s%n" +
                                            "   Stadium - %s",
                                    player.getFirstName(), player.getLastName(),
                                    player.getPosition(),
                                    player.getTeam().getName(),
                                    player.getTeam().getStadiumName()))
                            .append(System.lineSeparator());
                });
        return sb.toString();
    }
}
