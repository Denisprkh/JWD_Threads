package by.prokhorenko.threads.util.parser.impl;

import by.prokhorenko.threads.entity.CargoType;
import by.prokhorenko.threads.entity.Truck;
import by.prokhorenko.threads.entity.TruckCapacityType;
import by.prokhorenko.threads.exception.UtilException;
import by.prokhorenko.threads.factory.EntityFactory;
import by.prokhorenko.threads.util.parser.Parser;
import by.prokhorenko.threads.validator.AbstractValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TruckParser implements Parser<Truck> {

    private static final Logger LOG = LogManager.getLogger();
    private static final int INDEX_OF_CAPACITY_TYPE = 0;
    private static final int INDEX_OF_CARGO_TYPE = 1;
    private static final int INDEX_OF_LOAD = 2;
    private static final int DATA_FOR_TRUCK_WITHOUT_LOAD_LENGTH = 2;

    @Override
    public Truck parse(String data) throws UtilException {
        AbstractValidator validator = new AbstractValidator();
        EntityFactory entityFactory = EntityFactory.getInstance();
        if(validator.isNull(data)){
            LOG.error("Data string for parsing is null");
            throw new UtilException("Data is null");
        }

        String[] dataForTruck = data.split("\\|");
        TruckCapacityType capacityType = parseCapacityType(dataForTruck[INDEX_OF_CAPACITY_TYPE]);
        CargoType cargoType = parseCargoType(dataForTruck[INDEX_OF_CARGO_TYPE]);
        Truck truck;
        if(dataForTruck.length == DATA_FOR_TRUCK_WITHOUT_LOAD_LENGTH){
            truck = entityFactory.getTruck(capacityType,cargoType);
        }else{
            int load = Integer.parseInt(dataForTruck[INDEX_OF_LOAD]);
            truck = entityFactory.getTruck(capacityType,cargoType,load);
        }
        return truck;
    }

    private TruckCapacityType parseCapacityType(String data) throws UtilException {
        AbstractValidator validator = new AbstractValidator();
        if(validator.isNull(data)){
            LOG.error("Data string for capacityType parsing is null");
            throw new UtilException("Data is null");
        }

        TruckCapacityType truckCapacityType;

        truckCapacityType = data.equalsIgnoreCase("small") ? TruckCapacityType.SMALL :
                (data.equalsIgnoreCase("middle") ? TruckCapacityType.MIDDLE :
                        TruckCapacityType.LARGE);

        return truckCapacityType;
    }

    private CargoType parseCargoType(String data) throws UtilException {
        AbstractValidator validator = new AbstractValidator();
        if(validator.isNull(data)){
            LOG.error("Data string for cargoType parsing is null");
            throw new UtilException("Data is null");
        }

        CargoType cargoType;
        cargoType = data.equalsIgnoreCase("perishable") ? CargoType.PERISHABLE :
                CargoType.NOT_PERISHABLE;

        return cargoType;
    }
}
