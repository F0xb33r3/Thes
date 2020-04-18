package at.fhv.itm3.s2.roundabout.ui.pedestrianUi;

import at.fhv.itm3.s2.roundabout.api.PedestrianPoint;
import at.fhv.itm3.s2.roundabout.entity.PedestrianStreetSection;
import at.fhv.itm3.s2.roundabout.util.ConfigParser;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import java.awt.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PedestrianUIMain extends ScrollPane {
    private  int width;
    private int height;
    private int positionX;
    private int positionY;
    private double centerX;
    private double centerY;

    private ConfigParser configParser = null;

    private List<PedestrianStreetUI> streetUIList = new ArrayList<>();

    Pane canvas = new Pane();

    public PedestrianUIMain(int posX, int posY, int width, int height, ConfigParser configParser){
        super();
        this.width = width;
        this.height = height;
        this.positionX = posX;
        this.positionY = posY;
        this.configParser = configParser;

        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(Double.MAX_VALUE);

        Scale scale = new Scale();
        scale.setX(PedestrianUIUtils.SCALE_FACTOR);
        scale.setY(-PedestrianUIUtils.SCALE_FACTOR);

        canvas.setStyle("-fx-background-color: black");

        scale.pivotYProperty().bind(Bindings.createDoubleBinding(() ->
                        canvas.getBoundsInLocal().getMinY() + canvas.getBoundsInLocal().getHeight()/2,
                canvas.boundsInLocalProperty()));

        canvas.getTransforms().add(scale);

        setLayoutX(posX);
        setLayoutY(posY);
        setStyle("-fx-background-color: red;");
        pannableProperty().set(true);
        setPannable(true);
        traverseComponents(configParser);
        System.out.println(canvas.getBoundsInParent());

        centerCanvas(canvas);
        setContent(canvas);
    }

    private void traverseComponents(ConfigParser configParser){
        Map<String, Map<String, PedestrianStreetSection>> pedestrianStreetSection =  configParser.getPedestrianSectionRegistry();
        for (Map<String, PedestrianStreetSection> pedestrianStreetCompoenent : pedestrianStreetSection.values()){
            traverseStreets(pedestrianStreetCompoenent);
        }
    }


    private void traverseStreets(Map<String, PedestrianStreetSection> pedestrianStreetCompoenent){
        for (PedestrianStreetSection pedestrianStreetSection : pedestrianStreetCompoenent.values()){
            PedestrianPoint globalPedestrianStreetCoordinate =  pedestrianStreetSection.getGlobalCoordinateOfSectionOrigin();
            double pedestrianStreetWidth = pedestrianStreetSection.getLengthX();
            double pedestrianStreetHeight = pedestrianStreetSection.getLengthY();

            PedestrianStreetUI pedestrianStreetUI = new PedestrianStreetUI(
                    globalPedestrianStreetCoordinate.getX(), globalPedestrianStreetCoordinate.getY(), pedestrianStreetWidth, pedestrianStreetHeight);

            streetUIList.add(pedestrianStreetUI);
            canvas.getChildren().add(pedestrianStreetUI);

        }
    }


    private void centerCanvas(Pane nonCenteredCanvas){
        double w2 = nonCenteredCanvas.getBoundsInParent().getMaxX();
        double h2 = nonCenteredCanvas.getBoundsInParent().getMaxY();
        double x2 = ((PedestrianUIUtils.MAIN_WINDOW_WIDTH - w2) / 2);
        double y2 = ((PedestrianUIUtils.MAIN_WINDOW_HEIGHT - h2) / 2);
        nonCenteredCanvas.setTranslateX(x2);
        nonCenteredCanvas.setTranslateY(y2);
    }

    public void validate(){
        centerX = canvas.getPrefWidth()/2;
        centerY = canvas.getPrefHeight()/2;
        centerNodeInScrollPane(this, canvas);
    }

    private void centerNodeInScrollPane(ScrollPane scrollPane, Node node) {
        double h = scrollPane.getContent().getBoundsInLocal().getHeight();
        double y = (node.getBoundsInParent().getMaxY() + node.getBoundsInParent().getMinY()) / 2.0;
        double v = scrollPane.getViewportBounds().getHeight();
        centerY = scrollPane.getVmax() * ((y - 0.5 * v) / (h - v));
        scrollPane.setVvalue(centerY);

        double w = scrollPane.getContent().getBoundsInLocal().getWidth();
        double x =  (node.getBoundsInParent().getMaxX() + node.getBoundsInParent().getMinX()) / 2.0;
        double r = scrollPane.getViewportBounds().getWidth();
        centerX = scrollPane.getHmax() * ((y - 0.5 * v) / (h - v));
        scrollPane.setHvalue(centerX);
    }
}