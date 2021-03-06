package at.fhv.itm3.s2.roundabout.util.dto;

import at.fhv.itm3.s2.roundabout.api.util.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;

public class Section implements IDTO {
    private String id;
    private Double length;
    private Double width;
    private Integer order;
    private Boolean isTrafficLightActive;
    private Long minGreenPhaseDuration;
    private Long greenPhaseDuration;
    private Long redPhaseDuration;

    //pedestrian
    private String pedestrianCrossingIDReference;
    private Boolean isPedestrianCrossing;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @XmlAttribute
    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    @XmlAttribute
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @XmlAttribute
    public Boolean getIsTrafficLightActive() { return isTrafficLightActive; }

    public void setIsTrafficLightActive(Boolean isTrafficLightActive) {
        this.isTrafficLightActive = isTrafficLightActive;
    }

    @XmlAttribute
    public  Long getGreenPhaseDuration() { return greenPhaseDuration; }

    private void setGreenPhaseDuration(Long greenPhaseDuration) {this.greenPhaseDuration = greenPhaseDuration; }

    @XmlAttribute
    public  Long getRedPhaseDuration() { return  redPhaseDuration; }

    public  void setRedPhaseDuration(Long redPhaseDuration) { this.redPhaseDuration = redPhaseDuration; }

    @XmlAttribute
    public Long getMinGreenPhaseDuration() {
        return minGreenPhaseDuration;
    }

    public void setMinGreenPhaseDuration(Long minGreenPhaseDuration) {
        this.minGreenPhaseDuration = minGreenPhaseDuration;
    }

    @XmlAttribute
    public String getPedestrianCrossingIDReference() {
        return pedestrianCrossingIDReference;
    }

    public void setPedestrianCrossingIDReference(String pedestrianCrossingIDReference) {
        this.pedestrianCrossingIDReference = pedestrianCrossingIDReference;
    }

    @XmlAttribute
    public Boolean getIsPedestrianCrossing() {
        return isPedestrianCrossing;
    }

    public void setIsPedestrianCrossing(Boolean isPedestrianCrossing) {
        this.isPedestrianCrossing = isPedestrianCrossing;
    }
}
