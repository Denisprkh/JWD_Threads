package by.prokhorenko.threads.entity;

import by.prokhorenko.threads.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.TimeUnit;


public class Terminal {
    private boolean isBusy;
    private int id;
    private static final int CARGO_TO_MOVE = 5;
    private static final Logger LOG = LogManager.getLogger();

    public Terminal(){
        this.id = IdGenerator.getIntId();
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public int getId() {
        return id;
    }

    public void using(Truck truck){
        if(truck.getLoad() > 0){
            while(truck.getLoad() > 0){
                truck.unload(CARGO_TO_MOVE);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    LOG.error(e);
                }
            }
            LOG.info("Truck " + truck + " was unloaded on terminal with id " + getId());
        }else{
            while (truck.getLoad() < truck.getTruckCapacityType().getCapacity()){
                truck.load(CARGO_TO_MOVE);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    LOG.error(e);
                }
            }
            LOG.info("Truck " + truck + " was loaded on terminal with id " + getId());
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terminal{");
        sb.append("isBusy=").append(isBusy);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terminal terminal = (Terminal) o;

        if (isBusy != terminal.isBusy) return false;
        return id == terminal.id;
    }

    @Override
    public int hashCode() {
        int result = (isBusy ? 1 : 0);
        result = 31 * result + id;
        return result;
    }
}
