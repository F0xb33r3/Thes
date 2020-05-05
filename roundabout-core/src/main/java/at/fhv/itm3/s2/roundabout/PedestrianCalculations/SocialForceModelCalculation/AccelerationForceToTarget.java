package at.fhv.itm3.s2.roundabout.PedestrianCalculations.SocialForceModelCalculation;

import at.fhv.itm3.s2.roundabout.api.PedestrianPoint;
import at.fhv.itm3.s2.roundabout.entity.Pedestrian;
import at.fhv.itm3.s2.roundabout.entity.PedestrianStreetSection;
import at.fhv.itm3.s2.roundabout.model.RoundaboutSimulationModel;

import javax.vecmath.Vector2d;

public class AccelerationForceToTarget {
    SupportiveCalculations calculations = new SupportiveCalculations();

    public Vector2d getAccelerationForceToTarget(RoundaboutSimulationModel model, Pedestrian pedestrian){
        Vector2d currentSpeedVector = calculations.getUnitVector(pedestrian.getPreviousSFMVector());
        currentSpeedVector.scale(pedestrian.getCurrentSpeed());
        Vector2d currentPositionVector = new Vector2d(pedestrian.getCurrentGlobalPosition().getX(), pedestrian.getCurrentGlobalPosition().getY());


        if (! (pedestrian.getCurrentSection().getStreetSection() instanceof PedestrianStreetSection)) {
            throw new IllegalStateException("Section not instance of PedestrianStreetSection.");
        }
        PedestrianPoint subGoal = pedestrian.getNextSubGoal(); // global  coordinates without any obstacle etc. = exit-point of  section -> always calc new since real aim is afterwards change so is current position

        // e(t)
        Vector2d preferredSpeedVector = new Vector2d(subGoal.getX(), subGoal.getY());
        preferredSpeedVector.sub(currentPositionVector);
        Double preferredSpeedValue = preferredSpeedVector.length();
        if (preferredSpeedValue != 0) {
            preferredSpeedVector.scale(1 / preferredSpeedValue);
            // preferredSpeed * e(t)
            preferredSpeedVector.scale(pedestrian.calculatePreferredSpeed()); //v_alpha * e_alpha(t)
        } else  {
            preferredSpeedVector.scale(0);
        }

        // 1/tau (preferred speed - current speed)
        preferredSpeedVector.sub(currentSpeedVector);
        preferredSpeedVector.scale(1/model.getRandomPedestrianRelaxingTimeTauAlpha());

        if(Double.isNaN(preferredSpeedVector.getX()) || Double.isNaN(preferredSpeedVector.getY()) ){
            throw new IllegalStateException("Vector calculation  error: AccelerationForce.");
        }

        return preferredSpeedVector;
    }
}
