package softuni.exam.service;

import softuni.exam.models.entity.Town;

import java.io.IOException;

// TODO: Implement all methods
public interface TownService {

    boolean areImported();

    String readTownsFileContent() throws IOException;
	
	String importTowns() throws IOException;

    Town findTownByName(String town);

}
