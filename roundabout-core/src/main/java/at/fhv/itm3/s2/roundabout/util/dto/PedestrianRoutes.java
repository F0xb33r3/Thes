package at.fhv.itm3.s2.roundabout.util.dto;

import at.fhv.itm3.s2.roundabout.api.util.dto.IDTO;

import java.util.List;

public class PedestrianRoutes implements IDTO {
    private List<PedestrianRoute> routeList;

    public List<PedestrianRoute> getPedestrianRoute() {
        return routeList;
    }

    public void setPedestrianRoutes(List<PedestrianRoute> routeList) {
        this.routeList = routeList;
    }
}
