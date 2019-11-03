package at.fhv.itm3.s2.roundabout.util.dto;

import at.fhv.itm3.s2.roundabout.api.util.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

public class PedestrianConnector implements IDTO {
    private String id;
    List<PedestrianTrack> pedestrianTrackList;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PedestrianTrack> getPedestrianTrack() {
        return pedestrianTrackList;
    }

    public void setPedestrianTrack(List<PedestrianTrack> pedestrianTrackList) {
        this.pedestrianTrackList = pedestrianTrackList;
    }
}
