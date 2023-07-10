package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportPlayersBySalaryDto;
import softuni.exam.models.dto.ExportPlayersDto;
import softuni.exam.models.dto.PlayerSeedDto;
import softuni.exam.models.entity.Picture;
import softuni.exam.models.entity.Player;
import softuni.exam.models.entity.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final PictureRepository pictureRepository;

    private final TeamRepository teamRepository;

    private static final String PLAYER_PATH = "src/main/resources/files/json/players.json";

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, PictureRepository pictureRepository, TeamRepository teamRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.playerRepository = playerRepository;
        this.pictureRepository = pictureRepository;
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return Files
                .readString(Path.of(PLAYER_PATH));
    }

    @Override
    public String importPlayers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readPlayersJsonFile(), PlayerSeedDto[].class))
                .filter(playerSeedDto -> {
                    boolean isValid = validationUtil.isValid(playerSeedDto);

                    Optional<Picture> pictureByUrl = this.pictureRepository
                            .findByUrl(playerSeedDto.getPicture().getUrl());

                    if (pictureByUrl.isEmpty()) {
                        isValid = false;
                    }

                    Optional<Team> teamByName = this.teamRepository
                            .findByName(playerSeedDto.getTeam().getName());

                    if (teamByName.isEmpty()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported player: %s %s",
                                    playerSeedDto.getFirstName(), playerSeedDto.getLastName())
                            :
                            "Invalid Player"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(playerSeedDto -> {
                    Player player = mapper.map(playerSeedDto, Player.class);
                    Optional<Picture> pictureByUrl = this.pictureRepository
                            .findByUrl(playerSeedDto.getPicture().getUrl());
                    Optional<Team> teamByName = this.teamRepository
                            .findByName(playerSeedDto.getTeam().getName());
                    player.setPicture(pictureByUrl.get());
                    player.setTeam(teamByName.get());
                    return player;
                })
                .forEach(this.playerRepository::save);
        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        StringBuilder sb = new StringBuilder();
        List<ExportPlayersDto> players = this.playerRepository.findAllPlayers();
        Map<String, List<String>> teamsWithPlayers = new LinkedHashMap<>();

        players.forEach(p -> {
            if (!teamsWithPlayers.containsKey(p.getTeamName())) {
                teamsWithPlayers.put(p.getTeamName(), null);
            }
        });

        for (Map.Entry<String, List<String>> player : teamsWithPlayers.entrySet()) {
            List<String> playersForTeam = new ArrayList<>();
            players.forEach(p -> {
                if (Objects.equals(player.getKey(), p.getTeamName())) {
                    playersForTeam.add(p.getFirstName());
                    playersForTeam.add(p.getLastName());
                    playersForTeam.add(p.getPosition());
                    playersForTeam.add(String.valueOf(p.getNumber()));
                }
            });
            teamsWithPlayers.put(player.getKey(), playersForTeam);
        }

        for (Map.Entry<String, List<String>> p : teamsWithPlayers.entrySet()) {
            sb.append(String.format("Team: %s%n",
                    p.getKey()));
            for (int i = 0; i < p.getValue().size() - 1; i += 4) {
                sb.append(String.format("    Player Name: %s %s - %s%n" +
                                "    Number: %s%n",
                        p.getValue().get(i), p.getValue().get(i + 1), p.getValue().get(i + 2),
                        p.getValue().get(i + 3)));
            }
        }
        return sb.toString();
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        StringBuilder sb = new StringBuilder();
        List<ExportPlayersBySalaryDto> playersBySalary = this.playerRepository.findBySalary();

        playersBySalary.forEach(p -> {
            sb.append(String.format("Player name: %s %s%n" +
                            "    Number: %d%n" +
                            "    Salary: %.2f%n" +
                            "    Team: %s%n",
                    p.getFirstName(), p.getLastName(),
                    p.getNumber(),
                    p.getSalary(),
                    p.getTeamName()
            ));
        });
        return sb.toString();
    }

}
