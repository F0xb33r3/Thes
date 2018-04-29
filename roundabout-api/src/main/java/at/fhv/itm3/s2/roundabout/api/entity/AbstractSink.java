package at.fhv.itm3.s2.roundabout.api.entity;

import desmoj.core.simulator.Model;

import java.util.List;
import java.util.UUID;

/**
 * Created by Karin on 05.01.2018.
 */
public abstract class AbstractSink extends Street implements ICarCountable {

    public AbstractSink(String id, Model model, String string, boolean bln) {
        super(id, model, string, bln);
    }

    public AbstractSink(Model model, String string, boolean bln) {
        this(UUID.randomUUID().toString(), model, string, bln);
    }

    /**
     * Used for statistical values. Returns the mean time used for crossing
     * a roundabout.
     *
     * @return  the mean time of all cars used to pass a roundabout as model time units
     */
    public abstract double getMeanRoundaboutPassTimeForEnteredCars();

    /**
     * Used for statistical values. Returns the mean time the cars spent in the system.
     *
     * @return  the mean time of all cars spent in the system as model time units
     */
    public abstract double getMeanTimeSpentInSystemForEnteredCars();

    /**
     *  Used for statistical values. Returns the mean time spent waiting per stop.
     *
     * @return  the mean time of all cars spent waiting per stop as model time units
     */
    public abstract double getMeanWaitingTimePerStopForEnteredCars();

    /**
     * Used for statistical values. Returns the mean number of stops the cars had to make.
     *
     * @return  the mean number of stops the cars had to made as int
     */
    public abstract double getMeanStopCountForEnteredCars();

    /**
     * Used for statistical values. Returns the mean time used for passing an intersection.
     *
     * @return  the mean time the cars used for passing an intersection as model time units
     */
    public abstract double getMeanIntersectionPassTimeForEnteredCars();
}
