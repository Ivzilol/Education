package org.softuni.exam.structures;

import org.softuni.exam.entities.Airline;
import org.softuni.exam.entities.Flight;


public interface AirlinesManager {
    void addAirline(Airline airline);

    void addFlight(Airline airline, Flight flight);

    boolean contains(Airline airline);

    boolean contains(Flight flight);

    void deleteAirline(Airline airline) throws IllegalArgumentException;

    Iterable<Flight> getAllFlights();

    Flight performFlight(Airline airline, Flight flight) throws IllegalArgumentException;

    Iterable<Flight> getCompletedFlights();

    Iterable<Flight> getFlightsOrderedByNumberThenByCompletion();

    Iterable<Airline> getAirlinesOrderedByRatingThenByCountOfFlightsThenByName();

    Iterable<Airline> getAirlinesWithFlightsFromOriginToDestination(String origin, String destination);
}
