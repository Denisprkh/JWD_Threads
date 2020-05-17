package by.prokhorenko.threads.runner;

import by.prokhorenko.threads.entity.Truck;
import by.prokhorenko.threads.exception.ReaderException;
import by.prokhorenko.threads.exception.UtilException;
import by.prokhorenko.threads.reader.Reader;
import by.prokhorenko.threads.reader.impl.FileReader;
import by.prokhorenko.threads.util.parser.Parser;
import by.prokhorenko.threads.util.parser.impl.TruckParser;
import by.prokhorenko.threads.util.starter.Starter;
import by.prokhorenko.threads.util.starter.impl.TruckStarterImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ReaderException, UtilException {
        Reader reader = new FileReader();
        Starter starter = new TruckStarterImpl();
        List<String> data = reader.read("file/data");
        Parser parser = new TruckParser();
        List<Truck> trucks = new ArrayList<>();
        for(String dat : data){
            trucks.add((Truck)parser.parse(dat));
        }
        starter.start(trucks);

    }
}
