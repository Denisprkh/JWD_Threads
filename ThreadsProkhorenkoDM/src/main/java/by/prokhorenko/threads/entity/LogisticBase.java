package by.prokhorenko.threads.entity;

import by.prokhorenko.threads.entity.comparator.TruckCargoTypeComparator;
import by.prokhorenko.threads.exception.ResourceGettingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LogisticBase {

    private static final int TERMINALS_AMOUNT = 5;
    private Queue<Truck> trucksOutsideBase = new PriorityQueue<>(new TruckCargoTypeComparator());
    private static final Semaphore semaphore = new Semaphore(TERMINALS_AMOUNT,true);
    private List<Terminal> terminals = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Lock truckOutsideBaseLock = new ReentrantLock();
    private static final Logger LOG = LogManager.getLogger();

    private static class SingletonHolder {
        public static final LogisticBase INSTANCE = new LogisticBase();
    }

    private LogisticBase(){
        for(int i = 0; i < TERMINALS_AMOUNT; i ++){
            terminals.add(new Terminal());
        }
    }

    public static LogisticBase getInstance()  {
        return SingletonHolder.INSTANCE;
    }

    public Terminal getTerminal(Truck truck) throws ResourceGettingException {
        Terminal terminal;
        try {
            semaphore.acquire();
            lock.lock();
            Optional<Terminal> optional = terminals.stream().filter(o -> !o.isBusy()).findAny();
            terminal = optional.get();
            trucksOutsideBase.remove(truck);
            terminal.setBusy(true);
            LOG.info("Truck " + truck + " got terminal "+ terminal);
            return terminal;
        } catch (InterruptedException e) {
            LOG.error(e);
        }finally {
            lock.unlock();
        }
        throw new ResourceGettingException();
    }

    public void releaseTerminal(Truck truck, Terminal terminal) {
        try {
            lock.lock();
            terminal.setBusy(false);
            LOG.info("Truck " + truck + " left the terminal "+ terminal);
        } finally {
            lock.unlock();
        }
        semaphore.release();
    }

    public void moveTruckNearTheTerr(Truck truck){
        try {
            truckOutsideBaseLock.lock();
            trucksOutsideBase.add(truck);
            LOG.info("Truck " + truck + " stays in the queue");
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            LOG.error(e);
        }finally {
            truckOutsideBaseLock.unlock();
        }

    }

}