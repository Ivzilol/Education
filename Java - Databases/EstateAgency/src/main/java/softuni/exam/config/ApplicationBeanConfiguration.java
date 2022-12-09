package softuni.exam.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter((Converter<String, LocalDate>) mappingContext -> LocalDate
                .parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        modelMapper.addConverter((Converter<String, LocalDateTime>) mappingContext ->
                LocalDateTime.parse(mappingContext.getSource(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return modelMapper;
    }
}
