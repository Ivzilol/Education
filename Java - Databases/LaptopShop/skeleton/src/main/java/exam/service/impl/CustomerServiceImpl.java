package exam.service.impl;

import com.google.gson.*;
import exam.model.dto.ImportCustomersDTO;
import exam.model.entity.Customer;
import exam.model.entity.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMERS_FILE_PATH = "src/main/resources/files/json/customers.json";
    private final CustomerRepository customerRepository;

    private final TownRepository townRepository;

    private final Gson gson;

    private final Validator validator;

    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               TownRepository townRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.modelMapper = modelMapper;
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
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.
                readString(Path.of(CUSTOMERS_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        String path = readCustomersFileContent();
        ImportCustomersDTO[] importCustomers = this.gson.fromJson(path, ImportCustomersDTO[].class);
        return Arrays.stream(importCustomers)
                .map(this::validateCustomersDto).collect(Collectors.joining("\n"));
    }

    private String validateCustomersDto(ImportCustomersDTO customersDTO) {
        Set<ConstraintViolation<ImportCustomersDTO>> validateException =
                this.validator.validate(customersDTO);
        if (validateException.isEmpty()) {
            return "Invalid Customer";
        }

        Optional<Customer> byCustomer = this.customerRepository
                .findByFirstNameAndLastName(customersDTO.getFirstName(), customersDTO.getLastName());

        if (byCustomer.isPresent()) {
            return "Invalid Customer";
        }

        Customer customer =this.modelMapper.map(customersDTO, Customer.class);
        Optional<Town> town = this.townRepository.findByName(customersDTO.getTown().getName());
        customer.setTown(town.get());
        this.customerRepository.save(customer);
        return customer.toString();

    }
}
