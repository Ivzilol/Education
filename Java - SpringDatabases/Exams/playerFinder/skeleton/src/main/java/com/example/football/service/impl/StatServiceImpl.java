package com.example.football.service.impl;

import com.example.football.models.dto.StatSeedRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    private static final String STAT_PATH = "src/main/resources/files/xml/stats.xml";

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public StatServiceImpl(StatRepository statRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.statRepository = statRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files
                .readString(Path.of(STAT_PATH));
    }

    @Override
    public String importStats() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(STAT_PATH, StatSeedRootDto.class)
                .getStatSeedDtoList()
                .stream()
                .filter(statSeedDto -> {
                    boolean isValid = this.validationUtil.isValid(statSeedDto);

                    Optional<Stat> stats = this.statRepository
                            .findByPassingAndShootingAndEndurance(
                                    statSeedDto.getPassing(), statSeedDto.getShooting(),
                                    statSeedDto.getEndurance()
                            );
                    if (stats.isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid
                            ?
                            String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                                    statSeedDto.getShooting(), statSeedDto.getPassing(),
                                    statSeedDto.getEndurance())
                            :
                            "Invalid Stat"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(statSeedDto -> mapper.map(statSeedDto, Stat.class))
                .forEach(this.statRepository::save);
        return sb.toString();
    }
}
