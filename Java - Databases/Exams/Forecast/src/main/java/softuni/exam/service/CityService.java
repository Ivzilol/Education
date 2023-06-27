package softuni.exam.service;

import softuni.exam.models.entity.City;

import java.io.IOException;

// TODO: Implement all methods
public interface CityService {

    boolean areImported();

    String readCitiesFileContent() throws IOException;
	
	String importCities() throws IOException;

    City findCityById(Long city);
}
