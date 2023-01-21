package exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import exam.model.dto.ExportLaptopsDTO;
import exam.model.dto.ImportLaptopsDTO;
import exam.model.entity.Laptop;
import exam.model.entity.Shop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {

    private static final String LAPTOPS_FILE_PATH = "src/main/resources/files/json/laptops.json";
    private final LaptopRepository laptopRepository;

    private final ShopRepository shopRepository;
    private final Gson gson;
    private final Validator validator;

    private final ModelMapper modelMapper;

    public LaptopServiceImpl(LaptopRepository laptopRepository, Gson gson, Validator validator, ShopRepository shopRepository, ModelMapper modelMapper) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>)
                                (jsonElement, type, jsonDeserializationContext) ->
                                        LocalDate.parse(jsonElement.getAsString(),
                                                DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .create();
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files
                .readString(Path.of(LAPTOPS_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        String path = readLaptopsFileContent();
        ImportLaptopsDTO[] importLaptops = this.gson.fromJson(path, ImportLaptopsDTO[].class);
        return Arrays.stream(importLaptops)
                .map(this::validateLaptopsDto).collect(Collectors.joining("\n"));
    }

    private String validateLaptopsDto(ImportLaptopsDTO laptopsDTO) {
        Set<ConstraintViolation<ImportLaptopsDTO>> validateException
                = this.validator.validate(laptopsDTO);
        if (!validateException.isEmpty()) {
            return "Invalid Laptop";
        }

        Optional<Laptop> byLaptops = this.laptopRepository
                .findByMacAddressAndCpuSpeedAndRamAndStorage(
                        laptopsDTO.getMacAddress(),
                        laptopsDTO.getCpuSpeed(),
                        laptopsDTO.getRam(),
                        laptopsDTO.getStorage());

        if (byLaptops.isPresent()) {
            return "Invalid Laptop";
        }

        Laptop laptop = this.modelMapper.map(laptopsDTO, Laptop.class);
        Optional<Shop> shopName = this.shopRepository.findByName(laptopsDTO.getShop().getName());
        laptop.setShop(shopName.get());
        this.laptopRepository.save(laptop);
        return laptop.toString();

    }

    @Override
    public String exportBestLaptops() {
        List<ExportLaptopsDTO> exportLaptops = this.laptopRepository.findBestLaptops();

        StringBuilder sb = new StringBuilder();

        for (ExportLaptopsDTO laptop : exportLaptops) {
            sb
                    .append(String.format(
                            """
                                    Laptop - %s
                                    *Cpu speed - %.2f
                                    **Ram - %d
                                    ***Storage - %d
                                    ****Price - %.2f
                                    #Shop name - %s
                                    ##Town - %s
                                    """,
                            laptop.getMacAddress(),
                            laptop.getCpuSpeed(),
                            laptop.getRam(),
                            laptop.getStorage(),
                            laptop.getPrice(),
                            laptop.getShopName(),
                            laptop.getTownName()
                    )).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
