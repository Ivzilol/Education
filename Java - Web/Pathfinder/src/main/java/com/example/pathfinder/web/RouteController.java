package com.example.pathfinder.web;

import com.example.pathfinder.model.views.RouteDetailsView;
import com.example.pathfinder.model.views.RouteIndexView;
import com.example.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String getAllRoutes(Model model) {
        List<RouteIndexView> routes = routeService.getAllRoutes();
        model.addAttribute("routes", routes);
        return "routes";
    }

    @GetMapping("/details/{id}")
    public String getRoute(@PathVariable("id") Long routeId, Model model) {
        RouteDetailsView route = routeService.getRoute(routeId);
        model.addAttribute("route", route);
        return "route-details";
    }

}
