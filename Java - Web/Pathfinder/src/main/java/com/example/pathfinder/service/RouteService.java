package com.example.pathfinder.service;

import com.example.pathfinder.exeptions.RouteNotFoundException;
import com.example.pathfinder.model.Picture;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.views.RouteDetailsView;
import com.example.pathfinder.model.views.RouteIndexView;
import com.example.pathfinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getMostCommented() {
        return routeRepository.findMostComments();
    }

    public List<RouteIndexView> getAllRoutes() {
        return routeRepository.findAll()
                .stream()
                .map(route -> new RouteIndexView(
                        route.getId(),
                        route.getName(),
                        route.getDescription(),
                        route.getPictures()
                                .stream()
                                .findFirst()
                                .get()
                                .getUrl()
                )).collect(Collectors.toList());
    }

    public RouteDetailsView getRoute(Long id) {
        return routeRepository.findById(id)
                .map(route -> new RouteDetailsView(
                        route.getId(),
                        route.getGpxCoordinates(),
                        route.getLevel().name(),
                        route.getName(),
                        route.getDescription(),
                        route.getAuthor().getFullName(),
                        route.getVideoUrl(),
                        route.getPictures()
                                .stream().map(Picture::getUrl).collect(Collectors.toList())
                )).orElseThrow(RouteNotFoundException::new);
    }

}
