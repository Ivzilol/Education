package hiberspring.service.impl;

import hiberspring.models.dtos.EmployeeSeedRootDto;
import hiberspring.models.dtos.ExportEmployeeDto;
import hiberspring.models.entities.Branch;
import hiberspring.models.entities.Employee;
import hiberspring.models.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeCardRepository employeeCardRepository;

    private final BranchRepository branchRepository;
    private static final String EMPLOYEE_PATH = "src/main/resources/files/employees.xml";

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return Files
                .readString(Path.of(EMPLOYEE_PATH));
    }

    @Override
    public String importEmployees() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(EMPLOYEE_PATH, EmployeeSeedRootDto.class)
                .getEmployeeSeedDtoList()
                .stream()
                .filter(employeeSeedDto -> {
                    boolean isValid = validationUtil.isValid(employeeSeedDto);

                    Optional<EmployeeCard> byNumber = this.employeeCardRepository
                            .findByNumber(employeeSeedDto.getCard());
                    if (byNumber.isEmpty()) {
                        isValid = false;
                    }
                    Optional<Branch> branchByName = this.branchRepository
                            .findByName(employeeSeedDto.getBranch());
                    if (branchByName.isEmpty()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Employee %s %s",
                                    employeeSeedDto.getFirstName(), employeeSeedDto.getLastName())
                            :
                            "Error: Invalid data."
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(employeeSeedDto -> {
                    Employee employee = mapper.map(employeeSeedDto, Employee.class);
                    Optional<EmployeeCard> byNumber = this.employeeCardRepository
                            .findByNumber(employeeSeedDto.getCard());
                    Optional<Branch> branchByName = this.branchRepository
                            .findByName(employeeSeedDto.getBranch());
                    employee.setCard(byNumber.get());
                    employee.setBranch(branchByName.get());
                    return employee;
                })
                .forEach(this.employeeRepository::save);
        return sb.toString();
    }

    @Override
    public String exportProductiveEmployees() {
        StringBuilder sb = new StringBuilder();

        List<ExportEmployeeDto> employee = this.employeeRepository
                .findAllEmployeeWithProduct();


        employee.forEach(e -> {
            sb.append(String.format("Name: %s%n" +
                            "Position: %s%n" +
                            "Card Number: %s%n" +
                            "....................%n",
                    e.getFullName(),
                    e.getPosition(),
                    e.getNumber()
            ));
        });


        return sb.toString();
    }
}
