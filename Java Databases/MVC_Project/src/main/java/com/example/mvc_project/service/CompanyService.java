package com.example.mvc_project.service;

import com.example.mvc_project.models.Company;
import com.example.mvc_project.models.dto.ImportCompaniesRootDTO;
import com.example.mvc_project.models.dto.ImportCompanyDTO;
import com.example.mvc_project.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final Path xmlPath = Path.of("src/main/resources/files/xmls/companies.xml");
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    public String getCompaniesText() throws IOException {
        return Files.readString(xmlPath);
    }

    public String importCompanies() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(ImportCompaniesRootDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportCompaniesRootDTO rootDTO = (ImportCompaniesRootDTO)
                unmarshaller.unmarshal
                        (new FileReader(xmlPath.toAbsolutePath().toString()));
        return rootDTO
                .getCompanies()
                .stream()
                .map(ImportCompanyDTO::getName)
                .map(Company::new)
                .map(c -> {
                    Optional<Company> optCompany =
                            this.companyRepository.findByName(c.getName());

                    if (optCompany.isPresent()){
                        return "Invalid Company";
                    }

                    this.companyRepository.save(c);
                    return "Create Company - " + c.getName();
                })
                .collect(Collectors.joining("\n"));

    }
}
