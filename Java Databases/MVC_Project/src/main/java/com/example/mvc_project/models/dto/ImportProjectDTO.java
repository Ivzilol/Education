package com.example.mvc_project.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlAccessorType
public class ImportProjectDTO implements Serializable {

    @XmlElement
    @Size(min = 14)
    private String name;

    @XmlElement
    private String description;

    @XmlElement(name = "start-date")
    private String startDate;

    @XmlElement(name = "is-finished")
    private boolean isFinished;

    @XmlElement
    @Positive
    private BigDecimal payment;

    @XmlElement
    private ImportCompanyDTO company;

    public ImportProjectDTO() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public BigDecimal getPayment() {
        return payment;
    }


}
