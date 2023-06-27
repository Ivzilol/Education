package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastSeedRootDto {

    @XmlElement(name = "forecast")
    private List<ForecastSeedDto> forecastSeedDto;

    public List<ForecastSeedDto> getForecastRootDto() {
        return forecastSeedDto;
    }

    public void setForecastRootDto(List<ForecastSeedDto> forecastSeedDto) {
        this.forecastSeedDto = forecastSeedDto;
    }
}
