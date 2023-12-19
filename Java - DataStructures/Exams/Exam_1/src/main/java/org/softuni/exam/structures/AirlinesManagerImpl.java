package org.softuni.exam.structures;

import org.softuni.exam.entities.Airline;
import org.softuni.exam.entities.Flight;

import java.util.*;
import java.util.stream.Collectors;

public class AirlinesManagerImpl implements AirlinesManager {

    private final Map<String, Airline> airlines = new LinkedHashMap<>();
    private final Map<String, Flight> flights = new LinkedHashMap<>();
    private final Map<String, List<Flight>> flightsByAirline = new LinkedHashMap<>();
    private final Map<String, Flight> completedFlights = new LinkedHashMap<>();


    @Override
    public void addAirline(Airline airline) {
        airlines.put(airline.getId(), airline);
        flightsByAirline.put(airline.getId(), new ArrayList<>());
    }

    @Override
    public void addFlight(Airline airline, Flight flight) {
        if (!contains(airline)) {
            throw new IllegalArgumentException();
        }

        flights.put(flight.getId(), flight);
        flightsByAirline.get(airline.getId()).add(flight);
        if (flight.isCompleted()) {
            completedFlights.put(flight.getId(), flight);
        }
    }

    @Override
    public boolean contains(Airline airline) {
        return airlines.containsKey(airline.getId());
    }

    @Override
    public boolean contains(Flight flight) {
        return flights.containsKey(flight.getId());
    }

    @Override
    public void deleteAirline(Airline airline) throws IllegalArgumentException {
        if (!contains(airline)) {
            throw new IllegalArgumentException();
        }
        airlines.remove(airline.getId());
        List<Flight> removeAirlineFlights = flightsByAirline.get(airline.getId());
        for (Flight removeAirlineFlight : removeAirlineFlights) {
            flights.remove(removeAirlineFlight.getId());
            completedFlights.remove(removeAirlineFlight.getId());
        }
    }

    @Override
    public Iterable<Flight> getAllFlights() {
        return flights.values();
    }

    @Override
    public Flight performFlight(Airline airline, Flight flight) throws IllegalArgumentException {
        if (!contains(airline) || !contains(flight)) {
            throw new IllegalArgumentException();
        }
        Flight completedFlight = flights.get(flight.getId());
        completedFlight.setCompleted(true);
        completedFlights.put(completedFlight.getId(), completedFlight);
        return completedFlight;
    }

    @Override
    public Iterable<Flight> getCompletedFlights() {
        return completedFlights.values();
    }

    @Override
    public Iterable<Flight> getFlightsOrderedByNumberThenByCompletion() {
        return flights.values()
                .stream()
                .sorted(Comparator.comparing(Flight::isCompleted)
                        .thenComparing(Flight::getNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesOrderedByRatingThenByCountOfFlightsThenByName() {
        return airlines.values()
                .stream()
                .sorted(Comparator.comparing(Airline::getRating)
                        .thenComparing(a -> flightsByAirline.get(a.getId()).size()).reversed()
                        .thenComparing(Airline::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesWithFlightsFromOriginToDestination(String origin, String destination) {
        return airlines.values()
                .stream()
                .filter(a  -> flightsByAirline.get(a.getId()).stream().anyMatch(
                        f -> !f.isCompleted()
                                && f.getOrigin().equals(origin)
                                && f.getDestination().equals(destination)))
                .collect(Collectors.toList());
    }
}
