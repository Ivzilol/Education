package com.example.mvc_project.service;

import com.example.mvc_project.models.Project;
import com.example.mvc_project.models.dto.ImportProjectDTO;
import com.example.mvc_project.models.dto.ImportProjectRootDTO;
import com.example.mvc_project.util.MyValidator;
import org.modelmapper.ModelMapper;
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
import java.util.List;

@Service
public class ProjectService {

    private final Path xmlPath = Path.of("src/main/resources/files/xmls/projects.xml");
    private final MyValidator myValidator;
    private ModelMapper modelMapper;

    @Autowired
    public ProjectService(MyValidator myValidator, ModelMapper modelMapper) {
        this.myValidator = myValidator;
        this.modelMapper = modelMapper;
    }

    public boolean areImported() {
        return false;
    }

    public String getProjectsText() throws IOException {
        return Files.readString(xmlPath);
    }

    public String importProjects() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(ImportProjectRootDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportProjectRootDTO rootDTO = (ImportProjectRootDTO)
                unmarshaller.unmarshal
                        (new FileReader(xmlPath.toAbsolutePath().toString()));

        StringBuilder sb = new StringBuilder();

        List<ImportProjectDTO> projects = rootDTO.getProjects();
        for (ImportProjectDTO dto : projects){
            if (!myValidator.isValid(dto)){
                sb.append("Invalid Project\n");
                continue;
            }
            this.modelMapper.map(dto, Project.class);

        }
    }
}
