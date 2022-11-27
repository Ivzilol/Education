package softuni.exam.models.dto;

import javax.validation.constraints.Size;

public class ImportCountriesDTO {

    @Size(min = 2, max = 60)
    private String countryName;
    @Size(min = 2, max = 20)
    private String currency;

    public ImportCountriesDTO() {
    }

    public String getCountryName() {
        return countryName;
    }

    public ImportCountriesDTO setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public ImportCountriesDTO setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
