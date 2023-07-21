package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.CustomerSeedDto;
import exam.model.entity.Customer;
import exam.model.entity.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final TownRepository townRepository;

    private static final String CUSTOMER_PATH = "src/main/resources/files/json/customers.json";

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files
                .readString(Path.of(CUSTOMER_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readCustomersFileContent(), CustomerSeedDto[].class))
                .filter(customerSeedDto -> {
                    boolean isValid = validationUtil.isValid(customerSeedDto);

                    Optional<Customer> byEmail = this.customerRepository.findByEmail(customerSeedDto.getEmail());

                    if (byEmail.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Customer %s %s - %s",
                                    customerSeedDto.getFirstName(), customerSeedDto.getLastName(),
                                    customerSeedDto.getEmail())
                            :
                            "Invalid Customer"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(customerSeedDto -> {
                    Customer customer = mapper.map(customerSeedDto, Customer.class);
                    Optional<Town> townByName = townRepository.findByName(customerSeedDto.getTown().getName());
                    customer.setTown(townByName.get());
                    return customer;
                })
                .forEach(this.customerRepository::save);
        return sb.toString();
    }
}
