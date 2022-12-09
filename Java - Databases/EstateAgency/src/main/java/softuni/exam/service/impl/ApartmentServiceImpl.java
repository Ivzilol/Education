package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportApartmentDTO;
import softuni.exam.models.dto.ImportApartmentRootDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.TownService;
import softuni.exam.util.UnmarshallerImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private static final String APARTMENT_FILE_PATH = "src/main/resources/files/xml/apartments.xml";
    private final ApartmentRepository apartmentRepository;

    private final TownService townService;

    private final UnmarshallerImpl unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;


    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownService townService, UnmarshallerImpl unmarshaller) throws JAXBException {
        this.apartmentRepository = apartmentRepository;
        this.townService = townService;
        this.unmarshaller = unmarshaller;
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files
                .readString(Path.of(APARTMENT_FILE_PATH));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        ImportApartmentRootDTO impApartmentRootDTO =
                unmarshaller.fromFile(APARTMENT_FILE_PATH, ImportApartmentRootDTO.class);

        return impApartmentRootDTO
                .getApartments()
                .stream()
                .map(this::importApartment)
                .collect(Collectors.joining("\n"));
    }

    private String importApartment(ImportApartmentDTO dto) {
        Set<ConstraintViolation<ImportApartmentDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid apartment";
        } else {
            Optional<Apartment> optionalApartment =
                    this.apartmentRepository
                            .findApartmentByAreaAndTown(
                                    dto.getArea(),
                                    townService.findTownByName(dto.getTown()));

            if (optionalApartment.isPresent()) {
                return "Invalid apartment";
            }

            Apartment apartment = this.modelMapper.map(dto, Apartment.class);
            Town townByName = townService.findTownByName(dto.getTown());
            apartment.setTown(townByName);
            this.apartmentRepository.save(apartment);

            return String.format("Successfully imported apartment %s - %.2f",
                    apartment.getApartmentType(), apartment.getArea());
        }
    }
}
