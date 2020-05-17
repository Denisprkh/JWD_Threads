package by.prokhorenko.threads.entity;

import by.prokhorenko.threads.exception.ResourceGettingException;
import by.prokhorenko.threads.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Truck implements Runnable {
    private long id;
    private TruckCapacityType truckCapacityType;
    private CargoType cargoType;
    private int load;
    private static final Logger LOG = LogManager.getLogger();

    public Truck(TruckCapacityType truckCapacityType, CargoType cargoType){
        this.id = IdGenerator.getId();
        setTruckCapacityType(truckCapacityType);
        setCargoType(cargoType);

    }

    public Truck(TruckCapacityType truckCapacityType, CargoType cargoType, int load){
        this.id = IdGenerator.getId();
        setTruckCapacityType(truckCapacityType);
        setCargoType(cargoType);
        this.load = load;
    }

    public Truck(){

    }

    public long getId() {
        return id;
    }

    public TruckCapacityType getTruckCapacityType() {
        return truckCapacityType;
    }

    public void setTruckCapacityType(TruckCapacityType truckCapacityType) {
        this.truckCapacityType = truckCapacityType;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public int getLoad() {
        return load;
    }

    public void load(int load){
        this.load += load;
    }
    public void unload(int load){this.load -= load;}

    public boolean isLoaded(){
        return load == truckCapacityType.getCapacity();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Truck{");
        sb.append("id=").append(id);
        sb.append(", truckCapacityType=").append(truckCapacityType);
        sb.append(", cargoType=").append(cargoType);
        sb.append(", load=").append(load);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void run() {
        LogisticBase logisticBase = LogisticBase.getInstance();
        logisticBase.moveTruckNearTheTerr(this);

        try {
            Terminal terminal;
            terminal = logisticBase.getTerminal(this);
            terminal.using(this);
            logisticBase.releaseTerminal(this,terminal);
        } catch (ResourceGettingException e) {
            LOG.error(e);
        }

    }
}