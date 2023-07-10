package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TeamSeedRootDto;
import softuni.exam.models.entity.Picture;
import softuni.exam.models.entity.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.security.sasl.SaslServer;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final PictureRepository pictureRepository;

    private static final String TEAM_PATH = "src/main/resources/files/xml/teams.xml";

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public TeamServiceImpl(TeamRepository teamRepository, PictureRepository pictureRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return Files
                .readString(Path.of(TEAM_PATH));
    }

    @Override
    public String importTeams() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(TEAM_PATH, TeamSeedRootDto.class)
                .getTeamSeedDtoList()
                .stream()
                .filter(teamSeedDto -> {
                    boolean isValid = validationUtil.isValid(teamSeedDto);

                    Optional<Picture> pictureByUrl = this.pictureRepository
                            .findByUrl(teamSeedDto.getPictureUrl().getUrl());

                    if (pictureByUrl.isEmpty()) {
                        isValid = false;
                    }
                    sb.append(isValid
                            ?
                            String.format("Successfully imported - %s",
                                    teamSeedDto.getName())
                            :
                            "Invalid team"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(teamSeedDto -> {
                    Team team = mapper.map(teamSeedDto, Team.class);
                    Optional<Picture> pictureByUrl = this.pictureRepository
                            .findByUrl(teamSeedDto.getPictureUrl().getUrl());
                    team.setPicture(pictureByUrl.get());
                    return team;
                })
                .forEach(this.teamRepository::save);
        return sb.toString();

    }
}
