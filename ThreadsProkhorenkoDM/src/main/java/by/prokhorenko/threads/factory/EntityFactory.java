package by.prokhorenko.threads.factory;

import by.prokhorenko.threads.entity.CargoType;
import by.prokhorenko.threads.entity.LogisticBase;
import by.prokhorenko.threads.entity.Truck;
import by.prokhorenko.threads.entity.TruckCapacityType;

public class EntityFactory {
    private static class SingletonHolder {
        public static final EntityFactory INSTANCE = new EntityFactory();
    }

    private EntityFactory(){}

    public static EntityFactory getInstance()  {
        return EntityFactory.SingletonHolder.INSTANCE;
    }

    public Truck getTruck(TruckCapacityType capacityType, CargoType cargoType){
        return new Truck(capacityType,cargoType);
    }

    public Truck getTruck(TruckCapacityType capacityType, CargoType cargoType, int load){
        return new Truck(capacityType,cargoType,load);
    }
}
