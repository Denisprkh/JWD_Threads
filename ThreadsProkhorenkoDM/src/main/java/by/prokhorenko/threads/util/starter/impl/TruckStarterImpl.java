package by.prokhorenko.threads.util.starter.impl;

import by.prokhorenko.threads.entity.Truck;
import by.prokhorenko.threads.util.starter.Starter;

import java.util.List;

public class TruckStarterImpl implements Starter<Truck> {

    @Override
    public void start(List<Truck> trucks) {
        for(Truck truck : trucks){
            new Thread(truck).start();
        }
    }
}
