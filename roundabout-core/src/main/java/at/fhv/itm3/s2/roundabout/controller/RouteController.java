package at.fhv.itm3.s2.roundabout.controller;

import at.fhv.itm3.s2.roundabout.api.entity.AbstractSource;
import at.fhv.itm3.s2.roundabout.api.entity.IRoute;
import at.fhv.itm3.s2.roundabout.api.entity.Street;
import at.fhv.itm3.s2.roundabout.model.RoundaboutSimulationModel;
import desmoj.core.simulator.Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RouteController {

    private final RoundaboutSimulationModel model;
    private static RouteController instance;

    private Map<AbstractSource, List<IRoute>> routes;
    private List<AbstractSource> sources;

    /**
     * Gets all available routes.
     * @return All routes.
     */
    public Map<AbstractSource, List<IRoute>> getRoutes() {
        return routes;
    }

    /**
     * Sets all possible routes.
     * @param routes The routes which should be available to choose from.
     */
    public void setRoutes(Map<AbstractSource, List<IRoute>> routes) {
        this.routes = routes;
    }

    /**
     * Gets all sources.
     * @return A list of sources a route can start from.
     */
    public List<AbstractSource> getSources() {
        return sources;
    }

    /**
     * Sets all possible sources.
     * @param sources Sets a list of sources, from where a route could start.
     */
    public void setSources(List<AbstractSource> sources) {
        this.sources = sources;
    }

    /**
     * Returns a singleton of {@link RouteController}.
     *
     * @param model the model the RouteController and its {@link Street}s are part of.
     * @return the singleton RouteController object.
     */
    public static RouteController getInstance(RoundaboutSimulationModel model) {
        if (instance == null) {
            instance = new RouteController(model);
        }
        return instance;
    }

    /**
     * Private constructor for {@link RouteController}. Use getInstance(...) instead.
     *
     * @param model the model the {@link RouteController} and its {@link Street}s are part of.
     * @throws IllegalArgumentException when the given model is not of type {@link RoundaboutSimulationModel}.
     */
    private RouteController(Model model)
            throws IllegalArgumentException {
        if (model != null && model instanceof RoundaboutSimulationModel) {
            this.model = (RoundaboutSimulationModel) model;
        } else {
            throw new IllegalArgumentException("No suitable model given over.");
        }

        this.routes = new HashMap<>();
        this.sources = new LinkedList<>();
    }

    public IRoute getRandomRoute(AbstractSource source) {
        if (this.routes.isEmpty()) {
            throw new IllegalStateException("Routes must not be empty.");
        }

        final List<IRoute> routes = this.routes.get(source);

        final double totalRatio = routes.stream().mapToDouble(IRoute::getRatio).sum();
        final double randomRatio = model.getRandomRouteRatioFactor() * totalRatio;

        double sumRatio = 0;
        for (IRoute route : routes) {
            if (route.getRatio() > 0) {
                sumRatio += route.getRatio();
            }

            if (sumRatio > randomRatio) {
                return route;
            }
        }

        throw new IllegalStateException("No route was chosen for source: " + source);
    }
}