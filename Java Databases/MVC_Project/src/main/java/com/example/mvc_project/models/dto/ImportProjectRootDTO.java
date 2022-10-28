package com.example.mvc_project.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProjectRootDTO {

    @XmlElement(name = "project")
    List<ImportProjectDTO> projects;

    public ImportProjectRootDTO() {
    }

    public List<ImportProjectDTO> getProjects() {
        return projects;
    }
}
