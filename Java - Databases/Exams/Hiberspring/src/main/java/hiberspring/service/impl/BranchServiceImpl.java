package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.models.dtos.BranchSeedDto;
import hiberspring.models.entities.Branch;
import hiberspring.models.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    private final TownRepository townRepository;

    private static final String BRANCH_PATH = "src/main/resources/files/branches.json";

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return Files
                .readString(Path.of(BRANCH_PATH));
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readBranchesJsonFile(), BranchSeedDto[].class))
                .filter(branchSeedDto -> {
                    boolean isValid = validationUtil.isValid(branchSeedDto);

                    Optional<Town> townByName = townRepository
                            .findByName(branchSeedDto.getTown());

                    if (townByName.isEmpty()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Branch %s",
                                    branchSeedDto.getName())
                            :
                            "Error: Invalid data."
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(branchSeedDto -> {
                    Branch branch = mapper.map(branchSeedDto, Branch.class);
                    Optional<Town> townByName = townRepository
                            .findByName(branchSeedDto.getTown());
                    branch.setTown(townByName.get());
                    return branch;
                })
                .forEach(this.branchRepository::save);
        return sb.toString();
    }
}
