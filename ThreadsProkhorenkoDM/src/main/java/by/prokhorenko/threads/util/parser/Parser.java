package by.prokhorenko.threads.util.parser;


import by.prokhorenko.threads.exception.UtilException;

public interface Parser<T> {
    T parse(String data) throws UtilException;
}
